package com.ankit.playaudio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button clk1, clk2, clk3;
    private MediaPlayer mdk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clk1 = (Button) findViewById(R.id.playid);
        clk2 = (Button) findViewById(R.id.pauseid);
        clk3 = (Button) findViewById(R.id.stopid);

     
        mdk = MediaPlayer.create(MainActivity.this,R.raw.hanuman);
    }

    public void clkplay(View v)
    {
        mdk.start();
    }

    public void clkpause(View v)
    {
        mdk.pause();
    }

    public  void clkstop(View v)
    {
        mdk.stop();
        mdk = MediaPlayer.create(MainActivity.this,R.raw.hanuman);
    }
}
