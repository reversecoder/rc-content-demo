package com.reversecoder.content.helper.model;

import android.net.Uri;

import com.reversecoder.content.helper.util.AppUtil;

public class VideoInfo extends WrapperBase {

    private final String albumName;
    private final String artistName;
    private final int duration;
    private final long id;
    private final String title;
    private final int size;
    private final Uri uri;

    public VideoInfo() {
        this.id = -1;
        this.title = "";
        this.albumName = "";
        this.artistName = "";
        this.duration = -1;
        this.size = 0;
        uri = null;
    }

    public VideoInfo(long _id, String _title, String _albumName, String _artistName, int _duration, int size, Uri uri) {
        this.id = _id;
        this.title = _title;
        this.albumName = _albumName;
        this.artistName = _artistName;
        this.duration = _duration;
        this.size = size;
        this.uri = uri;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getDuration() {
        return duration;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public String getReadableSize() {
        return AppUtil.getReadableFileSize(size);
    }

    public Uri getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", duration=" + duration +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", size=" + getReadableSize() +
                ", uri=" + uri +
                '}';
    }
}