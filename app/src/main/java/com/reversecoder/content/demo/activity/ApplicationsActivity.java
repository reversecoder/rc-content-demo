package com.reversecoder.content.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;

import static com.reversecoder.content.demo.activity.StorageManagementActivity.allApplications;

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
        storageListViewAdapter.setData(allApplications);

    }
}
