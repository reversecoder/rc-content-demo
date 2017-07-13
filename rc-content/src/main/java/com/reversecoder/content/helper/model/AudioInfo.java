package com.reversecoder.content.helper.model;

import android.net.Uri;

import com.reversecoder.content.helper.util.AppUtil;

public class AudioInfo extends WrapperBase {

    private final long albumId;
    private final String albumName;
    private final long artistId;
    private final String artistName;
    private final int duration;
    private final long id;
    private final String title;
    private final int trackNumber;
    private final int size;
    private final Uri uri;

    public AudioInfo() {
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

    public AudioInfo(long _id, long _albumId, long _artistId, String _title, String _albumName, String _artistName, int _duration, int _trackNumber, int size, Uri uri) {
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

    public long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public long getArtistId() {
        return artistId;
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

    public int getTrackNumber() {
        return trackNumber;
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
        return "AudioInfo{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", duration=" + duration +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", trackNumber=" + trackNumber +
                ", size=" + getReadableSize() +
                ", uri=" + uri +
                '}';
    }
}