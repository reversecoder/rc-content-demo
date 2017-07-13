package com.reversecoder.content.media.image;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.reversecoder.content.helper.model.ImageInfo;
import com.reversecoder.content.helper.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

public class ImageLoader {

    private static final long[] sEmptyList = new long[0];

    public static ArrayList<ImageInfo> getImagesForCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String dateTaken = cursor.getString(2);
                String dateAdded = cursor.getString(3);
                String dateModified = cursor.getString(4);
                int size=cursor.getInt(5);
                Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Long.toString(id));

                arrayList.add(new ImageInfo(id, title, dateTaken, dateAdded, dateModified,size,uri));
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    public static ImageInfo getImageForCursor(Cursor cursor) {
        ImageInfo imageInfo = new ImageInfo();
        if ((cursor != null) && (cursor.moveToFirst())) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String dateTaken = cursor.getString(2);
            String dateAdded = cursor.getString(3);
            String dateModified = cursor.getString(4);
            int size=cursor.getInt(5);
            Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Long.toString(id));

            imageInfo = new ImageInfo(id, title, dateTaken, dateAdded, dateModified,size,uri);
        }

        if (cursor != null)
            cursor.close();
        return imageInfo;
    }

    public static final long[] getImageListForCursor(Cursor cursor) {
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

    public static ImageInfo getImageFromPath(String imagePath, Context context) {
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Images.Media.DATA;
        String[] selectionArgs = {imagePath};
        String[] projection = new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.TITLE, MediaStore.Images.ImageColumns.DATE_TAKEN,MediaStore.Images.ImageColumns.DATE_ADDED,
                MediaStore.Images.ImageColumns.DATE_MODIFIED, MediaStore.Images.ImageColumns.SIZE};
        String sortOrder = MediaStore.Images.Media.TITLE + " ASC";

        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            ImageInfo imageInfo = getImageForCursor(cursor);
            cursor.close();
            return imageInfo;
        } else return new ImageInfo();
    }

    public static ArrayList<ImageInfo> getAllImages(Context context) {
        return getImagesForCursor(makeImageCursor(context, null, null));
    }

    public static long[] getImageListInFolder(Context context, String path) {
        String[] whereArgs = new String[]{path + "%"};
        return getImageListForCursor(makeImageCursor(context, MediaStore.Images.Media.DATA + " LIKE ?", whereArgs, null));
    }

    public static ImageInfo getImageForID(Context context, long id) {
        return getImageForCursor(makeImageCursor(context, "_id=" + String.valueOf(id), null));
    }

    public static List<ImageInfo> searchImages(Context context, String searchString, int limit) {
        ArrayList<ImageInfo> result = getImagesForCursor(makeImageCursor(context, "title LIKE ?", new String[]{searchString + "%"}));
        if (result.size() < limit) {
            result.addAll(getImagesForCursor(makeImageCursor(context, "title LIKE ?", new String[]{"%_" + searchString + "%"})));
        }
        return result.size() < limit ? result : result.subList(0, limit);
    }


    public static Cursor makeImageCursor(Context context, String selection, String[] paramArrayOfString) {
        final String imageSortOrder = SortOrder.ImageSortOrder.IMAGE_A_Z;
        return makeImageCursor(context, selection, paramArrayOfString, imageSortOrder);
    }

    private static Cursor makeImageCursor(Context context, String selection, String[] paramArrayOfString, String sortOrder) {
        String selectionStatement = "title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }
        return context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.TITLE, MediaStore.Images.ImageColumns.DATE_TAKEN,MediaStore.Images.ImageColumns.DATE_ADDED,
                MediaStore.Images.ImageColumns.DATE_MODIFIED, MediaStore.Images.ImageColumns.SIZE}, selectionStatement, paramArrayOfString, sortOrder);

    }

    public static ImageInfo imageFromFile(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        return new ImageInfo(
                -1,
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),"","","",0,null
        );
    }

}