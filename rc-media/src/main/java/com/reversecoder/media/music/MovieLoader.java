package com.reversecoder.media.music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieLoader {

    private static final long[] sEmptyList = new long[0];

    public static ArrayList<Movie> getMoviesForCursor(Cursor cursor) {
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

                arrayList.add(new Movie(id, title, artist, album, durationInSecs,size,uri));
            }
            while (cursor.moveToNext());
        if (cursor != null)
            cursor.close();
        return arrayList;
    }

    public static Movie getMovieForCursor(Cursor cursor) {
        Movie movie = new Movie();
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


            movie = new Movie(id, title, album, artist, durationInSecs,size,uri);
        }

        if (cursor != null)
            cursor.close();
        return movie;
    }

    public static final long[] getMovieListForCursor(Cursor cursor) {
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

    public static Movie getMovieFromPath(String songPath, Context context) {
        ContentResolver cr = context.getContentResolver();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Video.Media.DATA;
        String[] selectionArgs = {songPath};
        String[] projection = new String[]{MediaStore.Video.VideoColumns._ID, MediaStore.Video.VideoColumns.TITLE, MediaStore.Video.VideoColumns.ARTIST,
                MediaStore.Video.VideoColumns.ALBUM, MediaStore.Video.VideoColumns.DURATION,MediaStore.Video.VideoColumns.SIZE
//                , MediaStore.Video.VideoColumns.TRACK, MediaStore.Video.VideoColumns.ARTIST_ID, MediaStore.Video.VideoColumns.ALBUM_ID
        };
        String sortOrder = MediaStore.Video.Media.TITLE + " ASC";

        Cursor cursor = cr.query(uri, projection, selection + "=?", selectionArgs, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            Movie movie = getMovieForCursor(cursor);
            cursor.close();
            return movie;
        } else return new Movie();
    }

    public static ArrayList<Movie> getAllMovies(Context context) {
        return getMoviesForCursor(makeMovieCursor(context, null, null));
    }

    public static long[] getMovieListInFolder(Context context, String path) {
        String[] whereArgs = new String[]{path + "%"};
        return getMovieListForCursor(makeMovieCursor(context, MediaStore.Video.Media.DATA + " LIKE ?", whereArgs, null));
    }

    public static Movie getSongForID(Context context, long id) {
        return getMovieForCursor(makeMovieCursor(context, "_id=" + String.valueOf(id), null));
    }

    public static List<Movie> searchSongs(Context context, String searchString, int limit) {
        ArrayList<Movie> result = getMoviesForCursor(makeMovieCursor(context, "title LIKE ?", new String[]{searchString + "%"}));
        if (result.size() < limit) {
            result.addAll(getMoviesForCursor(makeMovieCursor(context, "title LIKE ?", new String[]{"%_" + searchString + "%"})));
        }
        return result.size() < limit ? result : result.subList(0, limit);
    }


    public static Cursor makeMovieCursor(Context context, String selection, String[] paramArrayOfString) {
        final String movieSortOrder = SortOrder.MovieSortOrder.MOVIE_A_Z;
        return makeMovieCursor(context, selection, paramArrayOfString, movieSortOrder);
    }

    private static Cursor makeMovieCursor(Context context, String selection, String[] paramArrayOfString, String sortOrder) {
        String selectionStatement = "title != ''";

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = selectionStatement + " AND " + selection;
        }
        return context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Video.VideoColumns._ID, MediaStore.Video.VideoColumns.TITLE, MediaStore.Video.VideoColumns.ARTIST,
                MediaStore.Video.VideoColumns.ALBUM, MediaStore.Video.VideoColumns.DURATION,MediaStore.Video.VideoColumns.SIZE}, selectionStatement, paramArrayOfString, sortOrder);

    }

    public static Movie movieFromFile(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(filePath);
        return new Movie(
                -1,
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST),
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)),0,null
        );
    }

}