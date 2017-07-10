package com.reversecoder.media.music;

import android.provider.MediaStore;

public final class SortOrder {

    /**
     * This class is never instantiated
     */
    public SortOrder() {
    }

    /**
     * Artist sort order entries.
     */
    public interface ArtistSortOrder {
        /* Artist sort order A-Z */
        String ARTIST_A_Z = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER;

        /* Artist sort order Z-A */
        String ARTIST_Z_A = ARTIST_A_Z + " DESC";

        /* Artist sort order number of songs */
        String ARTIST_NUMBER_OF_SONGS = MediaStore.Audio.Artists.NUMBER_OF_TRACKS
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

        /* Album sort order songs */
        String ALBUM_NUMBER_OF_SONGS = MediaStore.Audio.Albums.NUMBER_OF_SONGS
                + " DESC";

        /* Album sort order artist */
        String ALBUM_ARTIST = MediaStore.Audio.Albums.ARTIST;

        /* Album sort order year */
        String ALBUM_YEAR = MediaStore.Audio.Albums.FIRST_YEAR + " DESC";

    }

    /**
     * Music sort order entries.
     */
    public interface MusicSortOrder {
        /* Music sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Music sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* Music sort order artist */
        String SONG_ARTIST = MediaStore.Audio.Media.ARTIST;

        /* Music sort order album */
        String SONG_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* Music sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Music sort order duration */
        String SONG_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* Music sort order date */
        String SONG_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* Music sort order filename */
        String SONG_FILENAME = MediaStore.Audio.Media.DATA;
    }

    /**
     * Movie sort order entries.
     */
    public interface MovieSortOrder {
        /* Movie sort order A-Z */
        String MOVIE_A_Z = MediaStore.Video.Media.DEFAULT_SORT_ORDER;

        /* Movie sort order Z-A */
        String MOVIE_Z_A = MOVIE_A_Z + " DESC";

        /* Movie sort order artist */
        String MOVIE_ARTIST = MediaStore.Video.Media.ARTIST;

        /* Movie sort order album */
        String MOVIE_ALBUM = MediaStore.Video.Media.ALBUM;

        /* Movie sort order duration */
        String MOVIE_DURATION = MediaStore.Video.Media.DURATION + " DESC";

        /* Movie sort order date */
        String MOVIE_DATE = MediaStore.Video.Media.DATE_ADDED + " DESC";

        /* Movie sort order filename */
        String MOVIE_FILENAME = MediaStore.Video.Media.DATA;
    }

    /**
     * Album song sort order entries.
     */
    public interface AlbumSongSortOrder {
        /* Album song sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Album song sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* Album song sort order track list */
        String SONG_TRACK_LIST = MediaStore.Audio.Media.TRACK + ", "
                + MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Album song sort order duration */
        String SONG_DURATION = MusicSortOrder.SONG_DURATION;

        /* Album Music sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Album song sort order filename */
        String SONG_FILENAME = MusicSortOrder.SONG_FILENAME;
    }

    /**
     * Artist song sort order entries.
     */
    public interface ArtistSongSortOrder {
        /* Artist song sort order A-Z */
        String SONG_A_Z = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

        /* Artist song sort order Z-A */
        String SONG_Z_A = SONG_A_Z + " DESC";

        /* Artist song sort order album */
        String SONG_ALBUM = MediaStore.Audio.Media.ALBUM;

        /* Artist song sort order year */
        String SONG_YEAR = MediaStore.Audio.Media.YEAR + " DESC";

        /* Artist song sort order duration */
        String SONG_DURATION = MediaStore.Audio.Media.DURATION + " DESC";

        /* Artist song sort order date */
        String SONG_DATE = MediaStore.Audio.Media.DATE_ADDED + " DESC";

        /* Artist song sort order filename */
        String SONG_FILENAME = MusicSortOrder.SONG_FILENAME;
    }

    /**
     * Artist album sort order entries.
     */
    public interface ArtistAlbumSortOrder {
        /* Artist album sort order A-Z */
        String ALBUM_A_Z = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;

        /* Artist album sort order Z-A */
        String ALBUM_Z_A = ALBUM_A_Z + " DESC";

        /* Artist album sort order songs */
        String ALBUM_NUMBER_OF_SONGS = MediaStore.Audio.Artists.Albums.NUMBER_OF_SONGS
                + " DESC";

        /* Artist album sort order year */
        String ALBUM_YEAR = MediaStore.Audio.Artists.Albums.FIRST_YEAR
                + " DESC";
    }

}