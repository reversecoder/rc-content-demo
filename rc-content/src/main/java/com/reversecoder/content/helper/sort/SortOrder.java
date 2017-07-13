package com.reversecoder.content.helper.sort;

import android.provider.MediaStore;

public final class SortOrder {

    /**
     * This class is never instantiated
     */
    public SortOrder() {
    }

    /**
     * AudioInfo sort order entries.
     */
    public interface AudioSortOrder {
        /* AudioInfo sort order A-Z */
        String AUDIO_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* AudioInfo sort order Z-A */
        String AUDIO_Z_A = AUDIO_A_Z + " DESC";

        /* AudioInfo sort order artist */
        String AUDIO_ARTIST = MediaStore.Audio.Media.ARTIST;

        /* AudioInfo sort order album */
        String AUDIO_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* AudioInfo sort order year */
        String AUDIO_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* AudioInfo sort order duration */
        String AUDIO_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* AudioInfo sort order date */
        String AUDIO_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* AudioInfo sort order filename */
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

        /* Album AudioInfo sort order year */
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
     * VideoInfo sort order entries.
     */
    public interface VideoSortOrder {
        /* VideoInfo sort order A-Z */
        String VIDEO_A_Z = MediaStore.Video.Media.DEFAULT_SORT_ORDER;

        /* VideoInfo sort order Z-A */
        String VIDEO_Z_A = VIDEO_A_Z + " DESC";

        /* VideoInfo sort order artist */
        String VIDEO_ARTIST = MediaStore.Video.Media.ARTIST;

        /* VideoInfo sort order album */
        String VIDEO_ALBUM = MediaStore.Video.Media.ALBUM;

        /* VideoInfo sort order duration */
        String VIDEO_DURATION = MediaStore.Video.Media.DURATION + " DESC";

        /* VideoInfo sort order date */
        String VIDEO_DATE = MediaStore.Video.Media.DATE_ADDED + " DESC";

        /* VideoInfo sort order filename */
        String VIDEO_FILENAME = MediaStore.Video.Media.DATA;
    }

    /**
     * FileInfo sort order entries.
     */
    public interface FileSortOrder {
        /* FileInfo sort order A-Z */
        String FILE_A_Z = MediaStore.Files.FileColumns.TITLE;

        /* FileInfo sort order Z-A */
        String FILE_Z_A = FILE_A_Z + " DESC";

        /* FileInfo sort order date */
        String FILE_DATE = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        /* FileInfo sort order filename */
        String FILE_FILENAME = MediaStore.Files.FileColumns.DATA;
    }

    /**
     * ImageInfo sort order entries.
     */
    public interface ImageSortOrder {
        /* ImageInfo sort order A-Z */
        String IMAGE_A_Z = MediaStore.Images.Media.DEFAULT_SORT_ORDER;

        /* ImageInfo sort order Z-A */
        String IMAGE_Z_A = IMAGE_A_Z + " DESC";

        /* ImageInfo sort order date */
        String IMAGE_DATE = MediaStore.Images.Media.DATE_ADDED + " DESC";

        /* ImageInfo sort order filename */
        String IMAGE_FILENAME = MediaStore.Images.Media.DATA;
    }
}