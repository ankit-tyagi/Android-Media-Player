package com.ankit.playaudio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ankit_000 on 19-08-2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongInfo> songs;
    Context context;

    OnitemClickListener onitemClickListener;

    SongAdapter(Context context, ArrayList<SongInfo> songs){
        this.context = context;
        this.songs = songs;
    }

    public interface OnitemClickListener {
        void onItemClick(Button b ,View v , SongInfo obj, int position);
    }

    public void setOnitemClickListener(OnitemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(context).inflate(R.layout.row_song,parent,false);
        return new SongHolder(myview);
    }

    @Override
    public void onBindViewHolder(final SongHolder holder, final int position) {
        final SongInfo c = songs.get(position);
        holder.songName.setText(c.songName);
        holder.artistName.setText(c.artistName);
        holder.btnAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(onitemClickListener != null){
                    onitemClickListener.onItemClick(holder.btnAction, v, c, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        TextView songName, artistName;
        Button btnAction;
        public SongHolder(View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.textViewSongName);
            artistName = (TextView) itemView.findViewById(R.id.textViewArtistName);
            btnAction = (Button) itemView.findViewById(R.id.buttonAction);
        }
    }
}
