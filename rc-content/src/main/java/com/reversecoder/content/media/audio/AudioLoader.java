package com.reversecoder.content.media.audio;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.reversecoder.content.helper.model.AudioInfo;
import com.reversecoder.content.helper.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

public class AudioLoader {

    private static final long[] sEmptyList = new long[0];

    public static ArrayList<AudioInfo> getAudiosForCursor(Cursor cursor) {
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
                int trackNumber = cursor.getInt(5);
                long artistId = cursor.getInt(6);
                long albumId = cursor.getLong(7);
                int size = cursor.getInt(8);
                Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.toString(id));

                arrayList.add(new AudioInfo(id, albumId, artistId, title, artist, album, durationInSecs, trackNumber, size,uri));
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    public static AudioInfo getAudioForCursor(Cursor cursor) {
        AudioInfo audioInfo = new AudioInfo();
        if ((cursor != null) && (cursor.moveToFirst())) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String artist = cursor.getString(2);
            String album = cursor.getString(3);
            int duration = cursor.getInt(4);
            // Convert the duration into seconds
            int durationInSecs = (int) duration / 1000;
            int trackNumber = cursor.getInt(5);
            long artistId = cursor.getInt(6);
            long albumId = cursor.getLong(7);
            int size = cursor.getInt(8);
            Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.toString(id));

            audioInfo = new AudioInfo(id, albumId, artistId, title, album, artist, durationInSecs, trackNumber, size,uri);
        }

        if (cursor != null)
            cursor.close();
        return audioInfo;
    }

    public static final long[] getAudioListForCursor(Cursor cursor) {
        if (cursor == null) {
            return sEmptyList;
        }
        final int len = cursor.getCount();
        final long[] list = new long[len];
        cursor.moveToFirst();
        int columnIndex = -1;
        try {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members.AUDIO_ID);
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

    public static AudioInfo getAudioFromPath(String audioPath, Context context) {
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.DATA;
        String[] selectionArgs = {audioPath};
        String[] projection = new String[]{MediaStore.Audio.AudioColumns._ID, MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.DURATION, MediaStore.Audio.AudioColumns.TRACK, MediaStore.Audio.AudioColumns.ARTIST_ID, MediaStore.Audio.AudioColumns.ALBUM_ID, MediaStore.Video.VideoColumns.SIZE};
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            AudioInfo audioInfo = getAudioForCursor(cursor);
            cursor.close();
            return audioInfo;
        } else return new AudioInfo();
    }

    public static ArrayList<AudioInfo> getAllAudios(Context context) {
        return getAudiosForCursor(makeAudioCursor(context, null, null));
    }

    public static long[] getAudioListInFolder(Context context, String path) {
        String[] whereArgs = new String[]{path + "%"};
        return getAudioListForCursor(makeAudioCursor(context, MediaStore.Audio.Media.DATA + " LIKE ?", whereArgs, null));
    }

    public static AudioInfo getAudioForID(Context context, long id) {
        return getAudioForCursor(makeAudioCursor(context, "_id=" + String.valueOf(id), null));
    }

    public static List<AudioInfo> searchAudios(Context context, String searchString, int limit) {
        ArrayList<AudioInfo> result = getAudiosForCursor(makeAudioCursor(context, "title LIKE ?", new String[]{searchString + "%"}));
        if (result.size() < limit) {
            result.addAll(getAudiosForCursor(makeAudioCursor(context, "title LIKE ?", new String[]{"%_" + searchString + "%"})));
        }
        return result.size() < limit ? result : result.subList(0, limit);
    }


    public static Cursor makeAudioCursor(Context context, String selection, String[] paramArrayOfString) {
        final String audioSortOrder = SortOrder.AudioSortOrder.AUDIO_A_Z;
        return makeAudioCursor(context, selection, paramArrayOfString, audioSortOrder);
    }

    private static Cursor makeAudioCursor(Context context, String selection, String[] paramArrayOfString, String sortOrder) {
        String selectionStatement = "is_music=1 AND title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }
        return context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Audio.AudioColumns._ID, MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.DURATION, MediaStore.Audio.AudioColumns.TRACK, MediaStore.Audio.AudioColumns.ARTIST_ID, MediaStore.Audio.AudioColumns.ALBUM_ID, MediaStore.Video.VideoColumns.SIZE}, selectionStatement, paramArrayOfString, sortOrder);

    }

    public static AudioInfo audioFromFile(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        return new AudioInfo(
                -1,
                -1,
                -1,
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)),
                0, 0,null
        );
    }

}