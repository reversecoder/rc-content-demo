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

        ((LinearLayout) findViewById(R.id.ll_cache_cleaner)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(MainActivity.this,getString(R.string.toast_feature_out_of_scope),Toast.LENGTH_SHORT).show();
            }
        });

        ((LinearLayout) findViewById(R.id.ll_history_cleaner)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,getString(R.string.toast_feature_out_of_scope),Toast.LENGTH_SHORT).show();
            }
        });

        ((LinearLayout) findViewById(R.id.ll_uninstall)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,getString(R.string.toast_feature_out_of_scope),Toast.LENGTH_SHORT).show();
            }
        });

        ((LinearLayout) findViewById(R.id.ll_move_to_sdcard)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,getString(R.string.toast_feature_out_of_scope),Toast.LENGTH_SHORT).show();
            }
        });

        ((LinearLayout) findViewById(R.id.ll_gps_refresh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,getString(R.string.toast_feature_out_of_scope),Toast.LENGTH_SHORT).show();
            }
        });
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
