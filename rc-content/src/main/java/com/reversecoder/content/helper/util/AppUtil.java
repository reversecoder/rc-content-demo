package com.reversecoder.content.helper.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.reversecoder.content.helper.mediascanner.MediaScanner;
import com.reversecoder.content.helper.mediascanner.ScannerListener;

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
    public static String getReadableFileSize(long size) {
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
            if(file.delete()){
                updateMediaStore(context, uri);
            }
            if (file.exists()) {
                file.getCanonicalFile().delete();
                if (file.exists()) {
                    if (context.getApplicationContext().deleteFile(file.getName())) {
                        updateMediaStore(context, uri);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteFile(Context context, String sdCardPath) {
        try {
            File file = new File(sdCardPath);
            if(file.delete()){
                updateMediaStore(context, sdCardPath);
            }
            if (file.exists()) {
                file.getCanonicalFile().delete();
                if (file.exists()) {
                    if (context.getApplicationContext().deleteFile(file.getName())) {
                        updateMediaStore(context, sdCardPath);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void updateMediaStore(Context context, Uri fileUri) {
        try {
            MediaScanner mediaScanner = new MediaScanner(context, new ScannerListener() {
                @Override
                public void oneComplete(String path, Uri uri) {
                }

                @Override
                public void allComplete(String[] filePaths) {
                    Log.d("MediaScanner:", "Scan completed");
                }
            });
            mediaScanner.scan(RealPathUtils.getPath(context, fileUri));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void updateMediaStore(Context context, String filePath) {
        try {
            MediaScanner mediaScanner = new MediaScanner(context, new ScannerListener() {
                @Override
                public void oneComplete(String path, Uri uri) {
                }

                @Override
                public void allComplete(String[] filePaths) {
                    Log.d("MediaScanner", "Scan completed");
                }
            });
            mediaScanner.scan(filePath);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
