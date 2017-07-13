package com.reversecoder.content.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.demo.toolbar.ToolbarActionModeCallback;

import static com.reversecoder.content.demo.activity.StorageManagementActivity.allMusics;

/**
 * @author Md. Rashadul Alam
 */
public class MusicActivity extends AppCompatActivity {

    ListView lvStorage;
    StorageAdapter storageListViewAdapter;

    //Action Mode for toolbar
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_listview);

        initUI();
        initActions();
    }

    private void initUI() {
        lvStorage = (ListView) findViewById(R.id.lv_storage);

        storageListViewAdapter = new StorageAdapter(MusicActivity.this, StorageAdapter.ADAPTER_TYPE.MUSIC);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(allMusics);
    }

    private void initActions() {
        lvStorage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //If ActionMode not null select item
                if (mActionMode != null)
                    onListItemSelect(position);
            }
        });

        lvStorage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Select item on long click
                onListItemSelect(position);
                return true;
            }
        });
    }

    //List item select method
    private void onListItemSelect(int position) {
        storageListViewAdapter.toggleSelection(position);//Toggle the selection

        boolean hasCheckedItems = storageListViewAdapter.getSelectedCount() > 0;//Check if any items are already selected or not

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = startSupportActionMode(new ToolbarActionModeCallback(MusicActivity.this, storageListViewAdapter, allMusics));
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();

        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(storageListViewAdapter
                    .getSelectedCount()) + " selected");


    }

    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }


    //Delete selected rows
    public void deleteRows() {
        SparseBooleanArray selected = storageListViewAdapter
                .getSelectedIds();//Get selected ids

        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                /************************
                 * Need to delete from adapter
                 ************************/
//                item_models.remove(selected.keyAt(i));
//                storageListViewAdapter.notifyDataSetChanged();//notify adapter

            }
        }
        Toast.makeText(MusicActivity.this, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
        mActionMode.finish();//Finish action mode after use

    }
}
