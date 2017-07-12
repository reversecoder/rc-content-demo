package com.reversecoder.content.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.reversecoder.content.demo.R;

/**
 * @author Md. Rashadul Alam
 */
public class StorageManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_management);
        initUI();
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
}
