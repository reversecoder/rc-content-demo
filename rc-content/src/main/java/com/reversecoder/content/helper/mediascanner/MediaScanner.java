package com.reversecoder.content.helper.mediascanner;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import java.util.List;

public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mMediaScanConn;
    private ScannerListener mScannerListener;
    private String[] filePaths;
    private int scanCount = 0;

    /**
     * Create scanner.
     *
     * @param context context.
     */
    public MediaScanner(Context context) {
        this(context, null);
    }

    /**
     * Create scanner.
     *
     * @param context         context.
     * @param scannerListener {@link ScannerListener}.
     */
    public MediaScanner(Context context, ScannerListener scannerListener) {
        this.mMediaScanConn = new MediaScannerConnection(context.getApplicationContext(), this);
        this.mScannerListener = scannerListener;
    }

    /**
     * Scanner is running.
     *
     * @return true, other wise false.
     */
    public boolean isRunning() {
        return mMediaScanConn.isConnected();
    }

    /**
     * Scan file.
     *
     * @param filePath file absolute path.
     */
    public void scan(String filePath) {
        scan(new String[]{filePath});
    }

    /**
     * Scan file list.
     *
     * @param filePaths file absolute path list.
     */
    public void scan(List<String> filePaths) {
        scan(filePaths.toArray(new String[filePaths.size()]));
    }

    /**
     * Scan file array.
     *
     * @param filePaths file absolute path array.
     */
    public void scan(String[] filePaths) {
        if (isRunning()) throw new RuntimeException("The scanner is running.");
        this.filePaths = filePaths;
        mMediaScanConn.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        if (filePaths != null && filePaths.length > 0) for (String filePath : filePaths) {
            String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            mMediaScanConn.scanFile(filePath, mimeType);
        }
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        if (mScannerListener != null) mScannerListener.oneComplete(path, uri);
        scanCount++;
        if (scanCount == filePaths.length) {
            mMediaScanConn.disconnect();
            scanCount = 0;
            if (mScannerListener != null) mScannerListener.allComplete(filePaths);
        }
    }
}