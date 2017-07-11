package com.reversecoder.content.helper.model;

import android.net.Uri;

public class File extends WrapperBase {

    public final long id;
    public final String mimeType;
    public final String title;
    public final int size;
    public final Uri uri;

    public File() {
        this.id = -1;
        this.title = "";
        this.mimeType = "";
        this.size = -1;
        this.uri = null;
    }

    public File(long id, String mimeType, String title, int size, Uri uri) {
        this.id = id;
        this.mimeType = mimeType;
        this.title = title;
        this.size = size;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", mimeType='" + mimeType + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", uri=" + uri +
                '}';
    }
}