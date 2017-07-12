package com.reversecoder.content.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.media.image.ImageLoader;

/**
 * @author Md. Rashadul Alam
 */
public class PictureActivity extends AppCompatActivity {


    GridView gvStorage;
    StorageAdapter storageGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_gridview);
        initUI();
    }

    private void initUI() {
        gvStorage= (GridView)findViewById(R.id.gv_storage);

        storageGridViewAdapter = new StorageAdapter(PictureActivity.this, StorageAdapter.ADAPTER_TYPE.PICTURE);
        gvStorage.setAdapter(storageGridViewAdapter);
        storageGridViewAdapter.setData(ImageLoader.getAllImages(PictureActivity.this));
    }
}
