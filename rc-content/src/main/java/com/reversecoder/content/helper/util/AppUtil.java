package com.reversecoder.content.helper.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;
import java.text.DecimalFormat;

public class AppUtil {

    /**
     * Get the file size in a human-readable string.
     *
     * @param size
     * @return
     * @author paulburke
     */
    public static String getReadableFileSize(int size) {
        final int BYTES_IN_KILOBYTES = 1024;
        final DecimalFormat dec = new DecimalFormat("###.#");
        final String KILOBYTES = " KB";
        final String MEGABYTES = " MB";
        final String GIGABYTES = " GB";
        float fileSize = 0;
        String suffix = KILOBYTES;

        if (size > BYTES_IN_KILOBYTES) {
            fileSize = size / BYTES_IN_KILOBYTES;
            if (fileSize > BYTES_IN_KILOBYTES) {
                fileSize = fileSize / BYTES_IN_KILOBYTES;
                if (fileSize > BYTES_IN_KILOBYTES) {
                    fileSize = fileSize / BYTES_IN_KILOBYTES;
                    suffix = GIGABYTES;
                } else {
                    suffix = MEGABYTES;
                }
            }
        }
        return String.valueOf(dec.format(fileSize) + suffix);
    }

    public static Uri getApplicationIconUri(ApplicationInfo appInfo) {
        Uri uri = null;
        if (appInfo.icon != 0) {
            uri = Uri.parse("android.resource://" + appInfo.packageName + "/" + appInfo.icon);
        }
        return uri;
    }

    public static String getApplicationLabel(Context context, ApplicationInfo info) {
        PackageManager packageManager = context.getPackageManager();
        String label = packageManager.getApplicationLabel(info).toString();
        return label;
    }

    public static void deleteFile(Context context, Uri uri) {
        try {
            File file = new File(RealPathUtils.getPath(context, uri));
            file.delete();
            if (file.exists()) {
                file.getCanonicalFile().delete();
                if (file.exists()) {
                    context.getApplicationContext().deleteFile(file.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteFile(Context context, String sdCardPath) {
        try {
            File file = new File(sdCardPath);
            file.delete();
            if (file.exists()) {
                file.getCanonicalFile().delete();
                if (file.exists()) {
                    context.getApplicationContext().deleteFile(file.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
