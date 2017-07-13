package com.reversecoder.content.demo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.helper.model.AppInfo;
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

        lvStorage = (ListView) findViewById(R.id.lv_storage);

        storageListViewAdapter = new StorageAdapter(ApplicationsActivity.this, StorageAdapter.ADAPTER_TYPE.APPLICATION);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(getAllApks());

    }

    private ArrayList<AppInfo> getAllApks() {
        ArrayList<AppInfo> allApks = new ArrayList<AppInfo>();
        ArrayList<AppInfo> installedApp = ApplicationLoader.getInstalledApplications(ApplicationsActivity.this);
        allApks.addAll(installedApp);

        AppInfo appInfo;
        ArrayList<File> unUsedApk = StorageManager.getInstance().getAllFilesFromExternalSdCard(Environment.getExternalStorageDirectory(), StorageManager.FileType.APK);
        for (int i = 0; i < unUsedApk.size(); i++) {
            appInfo = new AppInfo();
            appInfo.setAppName(unUsedApk.get(i).getName());
            appInfo.setApkSize(unUsedApk.get(i).length());
            appInfo.setIcon(ContextCompat.getDrawable(this, R.drawable.application_default));
            appInfo.setSdCardPath(unUsedApk.get(i).getAbsolutePath());
            allApks.add(appInfo);
        }

        return allApks;
    }
}
