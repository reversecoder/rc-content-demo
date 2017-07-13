package com.reversecoder.content.helper.model;

import android.net.Uri;

import com.reversecoder.content.helper.util.AppUtil;

public class ImageInfo extends WrapperBase {

    private final long id;
    private final String title;
    private final String dateTaken;
    private final String dateAdded;
    private final String dateModified;
    private final int size;
    private final Uri uri;

    public ImageInfo() {
        this.id = -1;
        this.title = "";
        this.dateTaken = "";
        this.dateAdded = "";
        this.dateModified = "";
        this.size = 0;
        uri = null;
    }

    public ImageInfo(long _id, String _title, String date_taken, String date_added, String date_modified, int size, Uri uri) {
        this.id = _id;
        this.title = _title;
        this.dateTaken = date_taken;
        this.dateAdded = date_added;
        this.dateModified = date_modified;
        this.size = size;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getDateModified() {
        return dateModified;
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
        return "ImageInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateTaken='" + dateTaken + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", size=" + getReadableSize() +
                ", uri=" + uri +
                '}';
    }
}