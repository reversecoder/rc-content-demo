package com.reversecoder.content.helper.sort;

import android.provider.MediaStore;

public final class SortOrder {

    /**
     * This class is never instantiated
     */
    public SortOrder() {
    }

    /**
     * Audio sort order entries.
     */
    public interface AudioSortOrder {
        /* Audio sort order A-Z */
        String AUDIO_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Audio sort order Z-A */
        String AUDIO_Z_A = AUDIO_A_Z + " DESC";

        /* Audio sort order artist */
        String AUDIO_ARTIST = MediaStore.Audio.Media.ARTIST;

        /* Audio sort order album */
        String AUDIO_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* Audio sort order year */
        String AUDIO_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Audio sort order duration */
        String AUDIO_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* Audio sort order date */
        String AUDIO_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* Audio sort order filename */
        String AUDIO_FILENAME = MediaStore.Audio.Media.DATA;
    }

    /**
     * Artist sort order entries.
     */
    public interface ArtistSortOrder {
        /* Artist sort order A-Z */
        String ARTIST_A_Z = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER;

        /* Artist sort order Z-A */
        String ARTIST_Z_A = ARTIST_A_Z + " DESC";

        /* Artist sort order number of audios */
        String ARTIST_NUMBER_OF_AUDIOS = MediaStore.Audio.Artists.NUMBER_OF_TRACKS
                + " DESC";

        /* Artist sort order number of albums */
        String ARTIST_NUMBER_OF_ALBUMS = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
                + " DESC";
    }

    /**
     * Album sort order entries.
     */
    public interface AlbumSortOrder {
        /* Album sort order A-Z */
        String ALBUM_A_Z = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;

        /* Album sort order Z-A */
        String ALBUM_Z_A = ALBUM_A_Z + " DESC";

        /* Album sort order audios */
        String ALBUM_NUMBER_OF_AUDIOS = MediaStore.Audio.Albums.NUMBER_OF_SONGS
                + " DESC";

        /* Album sort order artist */
        String ALBUM_ARTIST = MediaStore.Audio.Albums.ARTIST;

        /* Album sort order year */
        String ALBUM_YEAR = MediaStore.Audio.Albums.FIRST_YEAR + " DESC";

    }

    /**
     * Album audio sort order entries.
     */
    public interface AlbumAudioSortOrder {
        /* Album audio sort order A-Z */
        String AUDIO_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Album audio sort order Z-A */
        String AUDIO_Z_A = AUDIO_A_Z + " DESC";

        /* Album audio sort order track list */
        String AUDIO_TRACK_LIST = MediaStore.Audio.Media.TRACK + ", "
                + MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Album audio sort order duration */
        String AUDIO_DURATION = AudioSortOrder.AUDIO_DURATION;

        /* Album Audio sort order year */
        String AUDIO_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Album audio sort order filename */
        String AUDIO_FILENAME = AudioSortOrder.AUDIO_FILENAME;
    }

    /**
     * Artist audio sort order entries.
     */
    public interface ArtistAudioSortOrder {
        /* Artist audio sort order A-Z */
        String AUDIO_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Artist audio sort order Z-A */
        String AUDIO_Z_A = AUDIO_A_Z + " DESC";

        /* Artist audio sort order album */
        String AUDIO_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* Artist audio sort order year */
        String AUDIO_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Artist audio sort order duration */
        String AUDIO_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* Artist audio sort order date */
        String AUDIO_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* Artist audio sort order filename */
        String AUDIO_FILENAME = AudioSortOrder.AUDIO_FILENAME;
    }

    /**
     * Artist album sort order entries.
     */
    public interface ArtistAlbumSortOrder {
        /* Artist album sort order A-Z */
        String ALBUM_A_Z = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;

        /* Artist album sort order Z-A */
        String ALBUM_Z_A = ALBUM_A_Z + " DESC";

        /* Artist album sort order audios */
        String ALBUM_NUMBER_OF_AUDIOS = MediaStore.Audio.Artists.Albums.NUMBER_OF_SONGS
                + " DESC";

        /* Artist album sort order year */
        String ALBUM_YEAR = MediaStore.Audio.Artists.Albums.FIRST_YEAR
                + " DESC";
    }

    /**
     * Video sort order entries.
     */
    public interface VideoSortOrder {
        /* Video sort order A-Z */
        String VIDEO_A_Z = MediaStore.Video.Media.DEFAULT_SORT_ORDER;

        /* Video sort order Z-A */
        String VIDEO_Z_A = VIDEO_A_Z + " DESC";

        /* Video sort order artist */
        String VIDEO_ARTIST = MediaStore.Video.Media.ARTIST;

        /* Video sort order album */
        String VIDEO_ALBUM = MediaStore.Video.Media.ALBUM;

        /* Video sort order duration */
        String VIDEO_DURATION = MediaStore.Video.Media.DURATION + " DESC";

        /* Video sort order date */
        String VIDEO_DATE = MediaStore.Video.Media.DATE_ADDED + " DESC";

        /* Video sort order filename */
        String VIDEO_FILENAME = MediaStore.Video.Media.DATA;
    }

    /**
     * File sort order entries.
     */
    public interface FileSortOrder {
        /* File sort order A-Z */
        String FILE_A_Z = MediaStore.Files.FileColumns.TITLE;

        /* File sort order Z-A */
        String FILE_Z_A = FILE_A_Z + " DESC";

        /* File sort order date */
        String FILE_DATE = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        /* File sort order filename */
        String FILE_FILENAME = MediaStore.Files.FileColumns.DATA;
    }

    /**
     * Image sort order entries.
     */
    public interface ImageSortOrder {
        /* Image sort order A-Z */
        String IMAGE_A_Z = MediaStore.Images.Media.DEFAULT_SORT_ORDER;

        /* Image sort order Z-A */
        String IMAGE_Z_A = IMAGE_A_Z + " DESC";

        /* Image sort order date */
        String IMAGE_DATE = MediaStore.Images.Media.DATE_ADDED + " DESC";

        /* Image sort order filename */
        String IMAGE_FILENAME = MediaStore.Images.Media.DATA;
    }
}