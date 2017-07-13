package com.reversecoder.content.helper.model;

import android.graphics.drawable.Drawable;

public class AppInfo extends WrapperBase {

    private String appName = "";
    private String packageName = "";
    private String versionName = "";
    private int versionCode = 0;
    private Drawable icon;
    private String dataDirectory = "";
    private long cacheSize = 0;
    private long dataSize = 0;
    private long packageSize = 0;
    private long apkSize = 0;
    private String sdCardPath = "";

    public AppInfo(String appName, String packageName, String versionName, int versionCode, Drawable icon, String dataDirectory, long cacheSize, long dataSize, long packageSize, long apkSize, String sdCardPath) {
        this.appName = appName;
        this.packageName = packageName;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.icon = icon;
        this.dataDirectory = dataDirectory;
        this.cacheSize = cacheSize;
        this.dataSize = dataSize;
        this.packageSize = packageSize;
        this.apkSize = apkSize;
        this.sdCardPath = sdCardPath;
    }

    public AppInfo() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getDataDirectory() {
        return dataDirectory;
    }

    public void setDataDirectory(String dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public long getDataSize() {
        return dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public long getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(long packageSize) {
        this.packageSize = packageSize;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public String getSdCardPath() {
        return sdCardPath;
    }

    public void setSdCardPath(String sdCardPath) {
        this.sdCardPath = sdCardPath;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", dataDirectory='" + dataDirectory + '\'' +
                ", cacheSize=" + cacheSize +
                ", dataSize=" + dataSize +
                ", packageSize=" + packageSize +
                ", apkSize=" + apkSize +
                ", sdCardPath='" + sdCardPath + '\'' +
                '}';
    }
}