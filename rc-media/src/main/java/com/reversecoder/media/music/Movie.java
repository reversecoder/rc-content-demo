package com.reversecoder.media.music;

import android.net.Uri;
import android.provider.MediaStore;

import com.reversecoder.media.contact.model.WrapperBase;

public class Movie extends WrapperBase {

    public final String albumName;
    public final String artistName;
    public final int duration;
    public final long id;
    public final String title;
    public final int size;
    public final Uri uri;
//    public final int trackNumber;
//    public final long albumId;
//    public final long artistId;

    public Movie() {
        this.id = -1;
        this.title = "";
        this.albumName = "";
        this.artistName = "";
        this.duration = -1;
        this.size = 0;
        uri = null;
//        this.trackNumber = -1;
//        this.albumId = -1;
//        this.artistId = -1;
    }

    public Movie(long _id, String _title, String _albumName, String _artistName, int _duration, int size, Uri uri
//            , int _trackNumber, long _albumId, long _artistId
    ) {
        this.id = _id;
        this.title = _title;
        this.albumName = _albumName;
        this.artistName = _artistName;
        this.duration = _duration;
        this.size = size;
        this.uri = uri;
//        this.trackNumber = _trackNumber;
//        this.albumId = _albumId;
//        this.artistId = _artistId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", duration=" + duration +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", uri=" + uri +
                '}';
    }
}