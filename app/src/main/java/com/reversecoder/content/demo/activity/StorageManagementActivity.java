package com.reversecoder.content.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.helper.model.AppInfo;
import com.reversecoder.content.helper.model.AudioInfo;
import com.reversecoder.content.helper.model.FileInfo;
import com.reversecoder.content.helper.model.ImageInfo;
import com.reversecoder.content.helper.model.VideoInfo;
import com.reversecoder.content.helper.util.AppUtil;
import com.reversecoder.content.media.audio.AudioLoader;
import com.reversecoder.content.media.image.ImageLoader;
import com.reversecoder.content.media.video.VideoLoader;
import com.reversecoder.content.nonmedia.application.ApplicationLoader;
import com.reversecoder.content.nonmedia.file.FileLoader;

import java.util.ArrayList;

import static com.reversecoder.content.demo.util.AllConstants.INTENT_KEY_APPLICATIONS;

/**
 * @author Md. Rashadul Alam
 */
public class StorageManagementActivity extends AppCompatActivity {

    public static ArrayList<ImageInfo> allImages = new ArrayList<ImageInfo>();
    public static ArrayList<AudioInfo> allMusics = new ArrayList<AudioInfo>();
    public static ArrayList<VideoInfo> allMovies = new ArrayList<VideoInfo>();
    public static ArrayList<AppInfo> allApplications = new ArrayList<AppInfo>();
    public static ArrayList<FileInfo> allOther = new ArrayList<FileInfo>();
    public static long totalImageSize=0,totalMusicSize=0,totalMovieSize=0,totalApplicationSize=0,totalOtherSize=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_management);
        initUI();
        initActions();
    }

    private void initUI() {
        ((LinearLayout) findViewById(R.id.ll_picture)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageManagementActivity.this, PictureActivity.class);
                startActivity(intent);
            }
        });

        ((LinearLayout) findViewById(R.id.ll_music)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageManagementActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        ((LinearLayout) findViewById(R.id.ll_movie)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageManagementActivity.this, MoviesActivity.class);
                startActivity(intent);
            }
        });

        ((LinearLayout) findViewById(R.id.ll_application)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageManagementActivity.this, ApplicationsActivity.class);
//                intent.putParcelableArrayListExtra(INTENT_KEY_APPLICATIONS,allApplications);
                startActivity(intent);
            }
        });

        ((LinearLayout) findViewById(R.id.ll_other)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageManagementActivity.this, OthersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initActions(){
        LoadImages loadImages = new LoadImages(StorageManagementActivity.this,(TextView)findViewById(R.id.tv_picture_size));
        loadImages.execute();

        LoadMusics loadMusics = new LoadMusics(StorageManagementActivity.this,(TextView)findViewById(R.id.tv_music_size));
        loadMusics.execute();

        LoadMovies loadMovies = new LoadMovies(StorageManagementActivity.this,(TextView)findViewById(R.id.tv_movies_size));
        loadMovies.execute();

        LoadApplications loadApplications = new LoadApplications(StorageManagementActivity.this,(TextView)findViewById(R.id.tv_applications_size));
        loadApplications.execute();

        LoadOthers loadOthers = new LoadOthers(StorageManagementActivity.this,(TextView)findViewById(R.id.tv_other_size));
        loadOthers.execute();
    }

    public class LoadImages extends AsyncTask<String, String, Long> {

        private TextView statusText;
        private Context mContext;

        public LoadImages(Context context, TextView textView) {
            statusText = textView;
            mContext =context;
        }

        @Override
        public Long doInBackground(String... params) {
            allImages=ImageLoader.getAllImages(mContext);
            long currentImageSize=0;
            for(int i=0;i<allImages.size();i++){
                currentImageSize=currentImageSize+allImages.get(i).getSize();
                publishProgress(AppUtil.getReadableFileSize((int)currentImageSize));
            }
            return currentImageSize;
        }

        @Override
        protected void onPostExecute(Long result) {
            totalImageSize=result;
        }

        @Override
        protected void onProgressUpdate(String... text) {
            statusText.setText(text[0]);
        }

        @Override
        protected void onPreExecute() {
        }
    }

    public class LoadMusics extends AsyncTask<String, String, Long> {

        private TextView statusText;
        private Context mContext;

        public LoadMusics(Context context, TextView textView) {
            statusText = textView;
            mContext =context;
        }

        @Override
        public Long doInBackground(String... params) {
            allMusics= AudioLoader.getAllAudios(mContext);
            long currentMusicSize=0;
            for(int i=0;i<allMusics.size();i++){
                currentMusicSize=currentMusicSize+allMusics.get(i).getSize();
                publishProgress(AppUtil.getReadableFileSize((int)currentMusicSize));
            }
            return currentMusicSize;
        }

        @Override
        protected void onPostExecute(Long result) {
            totalMusicSize=result;
        }

        @Override
        protected void onProgressUpdate(String... text) {
            statusText.setText(text[0]);
        }

        @Override
        protected void onPreExecute() {
        }
    }

    public class LoadMovies extends AsyncTask<String, String, Long> {

        private TextView statusText;
        private Context mContext;

        public LoadMovies(Context context, TextView textView) {
            statusText = textView;
            mContext =context;
        }

        @Override
        public Long doInBackground(String... params) {
            allMovies= VideoLoader.getAllVideos(mContext);
            long currentVideoSize=0;
            for(int i=0;i<allMovies.size();i++){
                currentVideoSize=currentVideoSize+allMovies.get(i).getSize();
                publishProgress(AppUtil.getReadableFileSize((int)currentVideoSize));
            }
            return currentVideoSize;
        }

        @Override
        protected void onPostExecute(Long result) {
            totalMovieSize=result;
        }

        @Override
        protected void onProgressUpdate(String... text) {
            statusText.setText(text[0]);
        }

        @Override
        protected void onPreExecute() {
        }
    }

    public class LoadApplications extends AsyncTask<String, String, Long> {

        private TextView statusText;
        private Context mContext;

        public LoadApplications(Context context, TextView textView) {
            statusText = textView;
            mContext =context;
        }

        @Override
        public Long doInBackground(String... params) {
            allApplications= ApplicationLoader.getAllInstalledWithUnusedApks(mContext,R.drawable.application_default);
            long currentApplicationSize=0;
            for(int i=0;i<allApplications.size();i++){
                currentApplicationSize=currentApplicationSize+allApplications.get(i).getApkSize();
                publishProgress(AppUtil.getReadableFileSize((int)currentApplicationSize));
            }
            return currentApplicationSize;
        }

        @Override
        protected void onPostExecute(Long result) {
            totalApplicationSize=result;
        }

        @Override
        protected void onProgressUpdate(String... text) {
            statusText.setText(text[0]);
        }

        @Override
        protected void onPreExecute() {
        }
    }

    public class LoadOthers extends AsyncTask<String, String, Long> {

        private TextView statusText;
        private Context mContext;

        public LoadOthers(Context context, TextView textView) {
            statusText = textView;
            mContext =context;
        }

        @Override
        public Long doInBackground(String... params) {
            allOther= FileLoader.getAllDocuments(mContext);
            long currentOtherSize=0;
            for(int i=0;i<allOther.size();i++){
                currentOtherSize=currentOtherSize+allOther.get(i).getSize();
                publishProgress(AppUtil.getReadableFileSize((int)currentOtherSize));
            }
            return currentOtherSize;
        }

        @Override
        protected void onPostExecute(Long result) {
            totalOtherSize=result;
        }

        @Override
        protected void onProgressUpdate(String... text) {
            statusText.setText(text[0]);
        }

        @Override
        protected void onPreExecute() {
        }
    }
}
