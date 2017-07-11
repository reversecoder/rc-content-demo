package com.reversecoder.content.helper.model;

import android.net.Uri;


public class Image extends WrapperBase {

    public final long id;
    public final String title;
    public final String dateTaken;
    public final String dateAdded;
    public final String dateModified;
    public final int size;
    public final Uri uri;

    public Image() {
        this.id = -1;
        this.title = "";
        this.dateTaken = "";
        this.dateAdded = "";
        this.dateModified = "";
        this.size = 0;
        uri = null;
    }

    public Image(long _id, String _title, String date_taken, String date_added, String date_modified, int size, Uri uri) {
        this.id = _id;
        this.title = _title;
        this.dateTaken = date_taken;
        this.dateAdded = date_added;
        this.dateModified = date_modified;
        this.size = size;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateTaken='" + dateTaken + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", size=" + size +
                ", uri=" + uri +
                '}';
    }
}