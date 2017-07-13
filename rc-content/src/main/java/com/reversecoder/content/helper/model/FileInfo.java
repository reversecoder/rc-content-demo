package com.reversecoder.content.helper.model;

import android.net.Uri;

import com.reversecoder.content.helper.util.AppUtil;

public class FileInfo extends WrapperBase {

    private long id = -1;
    private String mimeType = "";
    private String title = "";
    private String path = "";
    private int size = -1;
    private Uri uri = null;

    public FileInfo(long id, String mimeType, String title, int size, Uri uri) {
        this.id = id;
        this.mimeType = mimeType;
        this.title = title;
        this.size = size;
        this.uri = uri;
    }

    public FileInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getReadableSize() {
        return AppUtil.getReadableFileSize(size);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", mimeType='" + mimeType + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", size=" + getReadableSize() +
                ", uri=" + uri +
                '}';
    }
}