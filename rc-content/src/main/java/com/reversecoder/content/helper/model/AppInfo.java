package com.reversecoder.content.helper.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class AppInfo extends WrapperBase implements Parcelable {

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

    /**********************************
     * Parcelable implementation
     *********************************/
    private AppInfo(Parcel in) {
        appName = in.readString();
        packageName = in.readString();
        versionName = in.readString();
        versionCode = in.readInt();

        // Deserialize Parcelable and cast to Bitmap first:
        Bitmap bitmap = (Bitmap) in.readParcelable(getClass().getClassLoader());
        // Convert Bitmap to Drawable:
        icon = new BitmapDrawable(bitmap);

        dataDirectory = in.readString();
        cacheSize = in.readLong();
        dataSize = in.readLong();
        packageSize = in.readLong();
        apkSize = in.readLong();
        sdCardPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appName);
        dest.writeString(packageName);
        dest.writeString(versionName);
        dest.writeInt(versionCode);

        // Convert Drawable to Bitmap first:
        Bitmap bitmap = (Bitmap) ((BitmapDrawable) icon).getBitmap();
        // Serialize bitmap as Parcelable:
        dest.writeParcelable(bitmap, flags);

        dest.writeString(dataDirectory);
        dest.writeLong(cacheSize);
        dest.writeLong(dataSize);
        dest.writeLong(packageSize);
        dest.writeLong(apkSize);
        dest.writeString(sdCardPath);
    }

    public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() {
        public AppInfo createFromParcel(Parcel in) {
            return new AppInfo(in);
        }

        public AppInfo[] newArray(int size) {
            return new AppInfo[size];

        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}