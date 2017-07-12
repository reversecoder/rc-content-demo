package com.reversecoder.content.helper.model;

import android.net.Uri;

import com.reversecoder.content.helper.util.AppUtil;

public class File extends WrapperBase {

    private final long id;
    private final String mimeType;
    private String title;
    private String path="";
    private final int size;
    private final Uri uri;

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


    public long getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
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
        return "File{" +
                "id=" + id +
                ", mimeType='" + mimeType + '\'' +
                ", title='" + title + '\'' +
                ", size=" + getReadableSize() +
                ", uri=" + uri +
                '}';
    }
}