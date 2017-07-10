package com.reversecoder.media.music;

import android.net.Uri;

import com.reversecoder.media.contact.model.WrapperBase;

public class Music extends WrapperBase {

    public final long albumId;
    public final String albumName;
    public final long artistId;
    public final String artistName;
    public final int duration;
    public final long id;
    public final String title;
    public final int trackNumber;
    public final int size;
    public final Uri uri;

    public Music() {
        this.id = -1;
        this.albumId = -1;
        this.artistId = -1;
        this.title = "";
        this.albumName = "";
        this.artistName = "";
        this.duration = -1;
        this.trackNumber = -1;
        this.size = -1;
        this.uri = null;
    }

    public Music(long _id, long _albumId, long _artistId, String _title, String _albumName, String _artistName, int _duration, int _trackNumber, int size, Uri uri) {
        this.id = _id;
        this.albumId = _albumId;
        this.artistId = _artistId;
        this.title = _title;
        this.albumName = _albumName;
        this.artistName = _artistName;
        this.duration = _duration;
        this.trackNumber = _trackNumber;
        this.size = size;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Music{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", duration=" + duration +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", trackNumber=" + trackNumber +
                ", size=" + size +
                ", uri=" + uri +
                '}';
    }
}