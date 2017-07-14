package com.reversecoder.content.demo.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.demo.toolbar.ToolbarActionModeCallback;
import com.reversecoder.content.helper.model.VideoInfo;
import com.reversecoder.content.helper.util.AppUtil;

import static com.reversecoder.content.demo.activity.StorageManagementActivity.allMovies;

/**
 * @author Md. Rashadul Alam
 */
public class MoviesActivity extends AppCompatActivity {

    ListView lvStorage;
    StorageAdapter storageListViewAdapter;

    //Action Mode for toolbar
    private ActionMode mActionMode;
    //SearchView
    private SearchView searchView;
    private MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_listview);

        initUI();
        initActions();
    }

    private void initUI() {
        lvStorage = (ListView) findViewById(R.id.lv_storage);

        storageListViewAdapter = new StorageAdapter(MoviesActivity.this, StorageAdapter.ADAPTER_TYPE.MOVIE);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(allMovies);
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

    /**********************************
     * Methods for toolbar action mode
     **********************************/
    //List item select method
    private void onListItemSelect(int position) {
        storageListViewAdapter.toggleSelection(position);//Toggle the selection

        boolean hasCheckedItems = storageListViewAdapter.getSelectedCount() > 0;//Check if any items are already selected or not

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = startSupportActionMode(new ToolbarActionModeCallback(MoviesActivity.this, storageListViewAdapter, allMovies));
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();

        if (mActionMode != null)
            //set action mode title on item selection
            mActionMode.setTitle(String.valueOf(storageListViewAdapter.getSelectedCount()) + " selected");
    }

    //Set action mode null after use
    public void setNullToActionMode() {
        if (mActionMode != null)
            mActionMode = null;
    }

    //Delete selected rows
    public void deleteRows() {
        SparseBooleanArray selected = storageListViewAdapter.getSelectedIds();//Get selected ids

        long freedSpace = 0;
        VideoInfo videoInfo;
        for (int i = 0; i < selected.size(); i++) {
            videoInfo = (VideoInfo) storageListViewAdapter.getItem(selected.keyAt(i));
            freedSpace = freedSpace + videoInfo.getSize();
        }

        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //delete from sdcard
                AppUtil.deleteFile(MoviesActivity.this, ((VideoInfo) storageListViewAdapter.getItem(selected.keyAt(i))).getUri());
                //If current id is selected remove the item via key
                storageListViewAdapter.removeItem(selected.keyAt(i));
            }
        }

//        Toast.makeText(MusicActivity.this, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();
        Toast.makeText(MoviesActivity.this, AppUtil.getReadableFileSize((int) freedSpace) + " deleted.", Toast.LENGTH_SHORT).show();
        mActionMode.finish();//Finish action mode after use
    }

    /**************************
     * Methods for option menu
     **************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                storageListViewAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
