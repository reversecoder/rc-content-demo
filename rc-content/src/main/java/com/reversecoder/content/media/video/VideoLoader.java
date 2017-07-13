package com.reversecoder.content.media.video;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.reversecoder.content.helper.model.VideoInfo;
import com.reversecoder.content.helper.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

public class VideoLoader {

    private static final long[] sEmptyList = new long[0];

    public static ArrayList<VideoInfo> getVideosForCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if ((cursor != null) && (cursor.moveToFirst()))
            do {
                long id = cursor.getLong(0);
                String title = cursor.getString(1);
                String artist = cursor.getString(2);
                String album = cursor.getString(3);
                int duration = cursor.getInt(4);
                // Convert the duration into seconds
                int durationInSecs = (int) duration / 1000;
                int size=cursor.getInt(5);
                Uri uri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, Long.toString(id));

                arrayList.add(new VideoInfo(id, title, artist, album, durationInSecs,size,uri));
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    public static VideoInfo getVideoForCursor(Cursor cursor) {
        VideoInfo videoInfo = new VideoInfo();
        if ((cursor != null) && (cursor.moveToFirst())) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String artist = cursor.getString(2);
            String album = cursor.getString(3);
            int duration = cursor.getInt(4);
            // Convert the duration into seconds
            int durationInSecs = (int) duration / 1000;
            int size=cursor.getInt(5);
            Uri uri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, Long.toString(id));


            videoInfo = new VideoInfo(id, title, album, artist, durationInSecs,size,uri);
        }

        if (cursor != null)
            cursor.close();
        return videoInfo;
    }

    public static final long[] getVideoListForCursor(Cursor cursor) {
        if (cursor == null) {
            return sEmptyList;
        }
        final int len = cursor.getCount();
        final long[] list = new long[len];
        cursor.moveToFirst();
        int columnIndex = -1;
        try {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.VIDEO_ID);
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

    public static VideoInfo getVideoFromPath(String videoPath, Context context) {
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Video.Media.DATA;
        String[] selectionArgs = {videoPath};
        String[] projection = new String[]{MediaStore.Video.VideoColumns._ID, MediaStore.Video.VideoColumns.TITLE, MediaStore.Video.VideoColumns.ARTIST,
                MediaStore.Video.VideoColumns.ALBUM, MediaStore.Video.VideoColumns.DURATION,MediaStore.Video.VideoColumns.SIZE};
        String sortOrder = MediaStore.Video.Media.TITLE + " ASC";

        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            VideoInfo videoInfo = getVideoForCursor(cursor);
            cursor.close();
            return videoInfo;
        } else return new VideoInfo();
    }

    public static ArrayList<VideoInfo> getAllVideos(Context context) {
        return getVideosForCursor(makeVideoCursor(context, null, null));
    }

    public static long[] getVideoListInFolder(Context context, String path) {
        String[] whereArgs = new String[]{path + "%"};
        return getVideoListForCursor(makeVideoCursor(context, MediaStore.Video.Media.DATA + " LIKE ?", whereArgs, null));
    }

    public static VideoInfo getVideoForID(Context context, long id) {
        return getVideoForCursor(makeVideoCursor(context, "_id=" + String.valueOf(id), null));
    }

    public static List<VideoInfo> searchVideos(Context context, String searchString, int limit) {
        ArrayList<VideoInfo> result = getVideosForCursor(makeVideoCursor(context, "title LIKE ?", new String[]{searchString + "%"}));
        if (result.size() < limit) {
            result.addAll(getVideosForCursor(makeVideoCursor(context, "title LIKE ?", new String[]{"%_" + searchString + "%"})));
        }
        return result.size() < limit ? result : result.subList(0, limit);
    }


    public static Cursor makeVideoCursor(Context context, String selection, String[] paramArrayOfString) {
        final String videoSortOrder = SortOrder.VideoSortOrder.VIDEO_A_Z;
        return makeVideoCursor(context, selection, paramArrayOfString, videoSortOrder);
    }

    private static Cursor makeVideoCursor(Context context, String selection, String[] paramArrayOfString, String sortOrder) {
        String selectionStatement = "title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }
        return context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Video.VideoColumns._ID, MediaStore.Video.VideoColumns.TITLE, MediaStore.Video.VideoColumns.ARTIST,
                MediaStore.Video.VideoColumns.ALBUM, MediaStore.Video.VideoColumns.DURATION,MediaStore.Video.VideoColumns.SIZE}, selectionStatement, paramArrayOfString, sortOrder);

    }

    public static VideoInfo videoFromFile(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        return new VideoInfo(
                -1,
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)),0,null
        );
    }

}