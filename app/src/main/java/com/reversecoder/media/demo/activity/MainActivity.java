package com.reversecoder.media.demo.activity;

import com.reversecoder.media.demo.R;
import com.reversecoder.media.music.Movie;
import com.reversecoder.media.music.MovieLoader;
import com.reversecoder.media.music.Music;
import com.reversecoder.media.music.MusicLoader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author Md. Rashadul Alam
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSplashUI();
    }

    private void initSplashUI() {
//        SongLoaderNew songLoader= new SongLoaderNew(MainActivity.this);
//        Log.d("Music: ",songLoader.getSongList().toString());
        ArrayList<Music> musics = MusicLoader.getAllSongs(MainActivity.this);
        for(int i = 0; i< musics.size(); i++){
            Log.d("Music: ", musics.get(i).toString());
        }


        ArrayList<Movie> movies = MovieLoader.getAllMovies(MainActivity.this);
        for(int i = 0; i< movies.size(); i++){
            Log.d("Movie: ", movies.get(i).toString());
        }
    }
}
