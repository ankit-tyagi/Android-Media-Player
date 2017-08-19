package com.ankit.playaudio;

/**
 * Created by ankit_000 on 19-08-2017.
 */

public class SongInfo {
    public String songName, artistName, songurl;

    public SongInfo() {
    }

    public SongInfo(String songName, String artistName, String songurl) {
        this.songName = songName;
        this.artistName = artistName;
        this.songurl = songurl;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongurl() {
        return songurl;
    }
}
