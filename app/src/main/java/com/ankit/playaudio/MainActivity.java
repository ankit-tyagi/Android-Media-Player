package com.ankit.playaudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SongInfo> songs = new ArrayList<SongInfo>();
    RecyclerView recyclerView;
    SeekBar seekBar;
    SongAdapter songAdapter;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        SongInfo s = new SongInfo("Cheap Thrills", "Sia", "http://junglevibe35.net/track/e6ac0c980b6bac5e45003f66e823bffd/65828dfbaf46c8d7c8cfa84090206b21/New_Tribute_Kings_-Cheap_Thrills_Originally_Performed_By_Sia_.mp3?dl=2");
        songs.add(s);

        songAdapter = new SongAdapter(this,songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);

        songAdapter.setOnitemClickListener(new SongAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, SongInfo obj, int position) {
                try{

                    if(b.getText().toString().equals("Stop")){
                        b.setText("Play");
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    else{
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(obj.getSongurl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                b.setText("Stop");
                            }
                        });
                    }
                }catch (IOException e){}

            }
        });
        checkUserPermission();
    }

    /*private void CheckUserPermission(){
        if(Build.VERSION.SDK_INT >= 23) {
            //requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123 );
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123 );
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
        loadsongs();
    }*/
    private void checkUserPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
         loadSongs();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadSongs();
                }
                else {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                    checkUserPermission();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    private void loadSongs() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);

        if(cursor!=null) {
            if(cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    SongInfo s= new SongInfo(name,artist,url);
                    songs.add(s);

                }while (cursor.moveToNext());
            }
            cursor.close();
            songAdapter = new SongAdapter(this, songs);
        }
    }
}
