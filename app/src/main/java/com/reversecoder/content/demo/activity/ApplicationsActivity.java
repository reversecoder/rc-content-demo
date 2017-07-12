package com.reversecoder.content.demo.activity;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.helper.model.Application;
import com.reversecoder.content.helper.util.StorageManager;
import com.reversecoder.content.nonmedia.application.ApplicationLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Md. Rashadul Alam
 */
public class ApplicationsActivity extends AppCompatActivity {

    ListView lvStorage;
    StorageAdapter storageListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_listview);
        initUI();
    }

    private void initUI() {

        lvStorage= (ListView)findViewById(R.id.lv_storage);

        storageListViewAdapter = new StorageAdapter(ApplicationsActivity.this, StorageAdapter.ADAPTER_TYPE.APPLICATION);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(getAllApks());

    }

    private  ArrayList<Application> getAllApks() {
        ArrayList<Application> allApks = new ArrayList<Application>();
        ArrayList<PackageInfo> installedApp = ApplicationLoader.getInstalledApplications(ApplicationsActivity.this);
        Application application;

        for(int i=0;i<installedApp.size();i++){
            application = new Application(installedApp.get(i).applicationInfo.loadLabel(getPackageManager()).toString());
            allApks.add(application);
        }

        ArrayList<File> unUsedApk= StorageManager.getInstance().getAllFilesFromExternalSdCard(Environment.getExternalStorageDirectory(), StorageManager.FileType.APK);
        for(int i=0;i<unUsedApk.size();i++){
            application = new Application(unUsedApk.get(i).getName());
            allApks.add(application);
        }

        return allApks;
    }
}
