package com.reversecoder.content.demo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.helper.util.RuntimePermissionManager;

import java.util.ArrayList;

import static com.reversecoder.content.helper.util.RuntimePermissionManager.REQUEST_CODE_PERMISSION;

/**
 * @author Md. Rashadul Alam
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkAndRequestPermissions()) {
            initUI();
        }
    }

    private void initUI() {

        ((LinearLayout) findViewById(R.id.ll_storage_management)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StorageManagementActivity.class);
                startActivity(intent);
            }
        });

//        sdCardContent=sdCardContent+"\n\n\n"+"Audios:\n\n\n";
//        ArrayList<Audio> audios = AudioLoader.getAllAudios(MainActivity.this);
//        for(int i = 0; i< audios.size(); i++){
//            sdCardContent = sdCardContent+(i+1)+". "+audios.get(i).toString()+"\n\n";
//            Log.d(TAG+"-audio: ", (i+1)+". "+audios.get(i).toString());
//        }
//
//        sdCardContent=sdCardContent+"\n\n\n"+"Videos:\n\n\n";
//        ArrayList<Video> videos = VideoLoader.getAllVideos(MainActivity.this);
//        for(int i = 0; i< videos.size(); i++){
//            sdCardContent = sdCardContent+(i+1)+". "+videos.get(i).toString()+"\n\n";
//            Log.d(TAG+"-video: ", (i+1)+". "+videos.get(i).toString());
//        }
//
//        sdCardContent=sdCardContent+"\n\n\n"+"Images:\n\n\n";
//        ArrayList<Image> images = ImageLoader.getAllImages(MainActivity.this);
//        for(int i = 0; i< images.size(); i++){
//            sdCardContent = sdCardContent+(i+1)+". "+images.get(i).toString()+"\n\n";
//            Log.d(TAG+"-image: ", (i+1)+". "+images.get(i).toString());
//        }
//
//        sdCardContent=sdCardContent+"\n\n\n"+"Files:\n\n\n";
//        ArrayList<File> files = FileLoader.getAllFiles(MainActivity.this);
//        for(int i = 0; i< files.size(); i++){
//            sdCardContent = sdCardContent+(i+1)+". "+files.get(i).toString()+"\n\n";
//            Log.d(TAG+"-file: ", (i+1)+". "+files.get(i).toString());
//        }
//
//        ((TextView)findViewById(R.id.tv_sdcard_content)).setText(sdCardContent);
    }

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!RuntimePermissionManager.isAllPermissionsGranted(MainActivity.this)) {
                ArrayList<String> permissionNeeded = RuntimePermissionManager.getAllUnGrantedPermissions(MainActivity.this);
                ActivityCompat.requestPermissions(this, permissionNeeded.toArray(new String[permissionNeeded.size()]), REQUEST_CODE_PERMISSION);
                return false;
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:

                if (RuntimePermissionManager.isAllPermissionsGranted(MainActivity.this, permissions)) {
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    initUI();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
