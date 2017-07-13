package com.reversecoder.content.demo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.helper.model.FileInfo;
import com.reversecoder.content.helper.util.StorageManager;
import com.reversecoder.content.nonmedia.file.FileLoader;

import java.util.ArrayList;

/**
 * @author Md. Rashadul Alam
 */
public class OthersActivity extends AppCompatActivity {

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

        storageListViewAdapter = new StorageAdapter(OthersActivity.this, StorageAdapter.ADAPTER_TYPE.OTHER);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(getAllFiles());
    }

    private ArrayList<FileInfo> getAllFiles() {
        ArrayList<FileInfo> allFileInfos = new ArrayList<FileInfo>();
        ArrayList<FileInfo> allFileLoaderFileInfo = FileLoader.getAllFiles(OthersActivity.this);
        allFileInfos.addAll(allFileLoaderFileInfo);

        ArrayList<java.io.File> withoutFileLoaderFile = StorageManager.getInstance().getAllFilesFromExternalSdCard(Environment.getExternalStorageDirectory(), StorageManager.FileType.DOCUMENT);
        FileInfo fileInfo;
        for (int i = 0; i < withoutFileLoaderFile.size(); i++) {
            fileInfo = new FileInfo();
            fileInfo.setTitle(withoutFileLoaderFile.get(i).getName());
            fileInfo.setPath(withoutFileLoaderFile.get(i).getAbsolutePath());
            fileInfo.setSize((int) withoutFileLoaderFile.get(i).length());
            allFileInfos.add(fileInfo);
        }

        return allFileInfos;
    }
}
