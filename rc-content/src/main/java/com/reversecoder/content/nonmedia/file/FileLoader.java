package com.reversecoder.content.nonmedia.file;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.reversecoder.content.helper.model.FileInfo;
import com.reversecoder.content.helper.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private static final long[] sEmptyList = new long[0];
    private static final String CONTENT_URI_EXTERNAL = "internal";

    public static ArrayList<FileInfo> getFilesForCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                long id = cursor.getLong(0);
                String mimeType = cursor.getString(1);
                String title = cursor.getString(2);
                int size = cursor.getInt(3);
                Uri uri = Uri.withAppendedPath(MediaStore.Files.getContentUri(CONTENT_URI_EXTERNAL), Long.toString(id));

                arrayList.add(new FileInfo(id, mimeType, title, size, uri));
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    public static FileInfo getFileForCursor(Cursor cursor) {
        FileInfo fileInfo = new FileInfo();
        if ((cursor != null) && (cursor.moveToFirst())) {
            long id = cursor.getLong(0);
            String mimeType = cursor.getString(1);
            String title = cursor.getString(2);
            int size = cursor.getInt(3);
            Uri uri = Uri.withAppendedPath(MediaStore.Files.getContentUri(CONTENT_URI_EXTERNAL), Long.toString(id));

            fileInfo = new FileInfo(id, mimeType, title, size, uri);
        }

        if (cursor != null)
            cursor.close();
        return fileInfo;
    }

    public static final long[] getFileListForCursor(Cursor cursor) {
        if (cursor == null) {
            return sEmptyList;
        }
        final int len = cursor.getCount();
        final long[] list = new long[len];
        cursor.moveToFirst();
        int columnIndex = -1;
        try {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.IMAGE_ID);
        } catch (final IllegalArgumentException notaplaylist) {
            columnIndex = cursor.getColumnIndexOrThrow(BaseColumns._ID);
        }
        for (int i = 0; i < len; i++) {
            list[i] = cursor.getLong(columnIndex);
            cursor.moveToNext();
        }
        cursor.close();
        cursor = null;
        return list;
    }

    public static FileInfo getFileFromPath(String filePath, Context context) {
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Files.getContentUri(CONTENT_URI_EXTERNAL);
        // exclude media files, they would be here also.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_NONE;
        String[] selectionArgs = null; // there is no ? in selection so null here
        // every column, although that is huge waste, you probably need
        // BaseColumns.DATA (the path) only.
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.MIME_TYPE, MediaStore.Files.FileColumns.TITLE, MediaStore.Files.FileColumns.SIZE};
        String sortOrder = MediaStore.Files.FileColumns.TITLE + " ASC";

        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            FileInfo fileInfo = getFileForCursor(cursor);
            cursor.close();
            return fileInfo;
        } else return new FileInfo();
    }

    public static ArrayList<FileInfo> getAllFiles(Context context) {
        return getFilesForCursor(makeFileCursor(context, null, null));
    }

    public static long[] getFileListInFolder(Context context, String path) {
        // exclude media files, they would be here also.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_NONE;
        String[] whereArgs = new String[]{path + "%"};
        return getFileListForCursor(makeFileCursor(context, selection + " LIKE ?", whereArgs, null));
    }

    public static FileInfo getFileForID(Context context, long id) {
        return getFileForCursor(makeFileCursor(context, "_id=" + String.valueOf(id), null));
    }

    public static List<FileInfo> searchFiles(Context context, String searchString, int limit) {
        ArrayList<FileInfo> result = getFilesForCursor(makeFileCursor(context, "title LIKE ?", new String[]{searchString + "%"}));
        if (result.size() < limit) {
            result.addAll(getFilesForCursor(makeFileCursor(context, "title LIKE ?", new String[]{"%_" + searchString + "%"})));
        }
        return result.size() < limit ? result : result.subList(0, limit);
    }


    public static Cursor makeFileCursor(Context context, String selection, String[] paramArrayOfString) {
        final String fileSortOrder = SortOrder.FileSortOrder.FILE_A_Z;
        return makeFileCursor(context, selection, paramArrayOfString, fileSortOrder);
    }

    private static Cursor makeFileCursor(Context context, String selection, String[] paramArrayOfString, String sortOrder) {
        String selectionStatement = "is_music=0 AND " +
                "is_ringtone=0 AND " +
                "is_podcast=0 AND " +
                "is_alarm=0 AND " +
                "is_notification=0 AND " +
                "title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }
        return context.getContentResolver().query(MediaStore.Files.getContentUri(CONTENT_URI_EXTERNAL), new String[]{MediaStore.Files.FileColumns._ID, MediaStore.Files.FileColumns.MIME_TYPE, MediaStore.Files.FileColumns.TITLE, MediaStore.Files.FileColumns.SIZE}, selectionStatement, paramArrayOfString, sortOrder);

    }

    public static FileInfo fileFromFile(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        return new FileInfo(
                -1,
                "",
                "", 0, null
        );
    }

}