package com.ankit.playaudio;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ankit_000 on 19-08-2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SongHolder extends RecyclerView.ViewHolder {
        public SongHolder(View itemView) {
            super(itemView);
        }
    }
}
