package com.reversecoder.content.demo.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SearchView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;
import com.reversecoder.content.demo.toolbar.ToolbarActionModeCallback;
import com.reversecoder.content.helper.model.AppInfo;
import com.reversecoder.content.helper.util.AppUtil;

import static com.reversecoder.content.demo.activity.StorageManagementActivity.allInstalledApplications;

public class InstalledAppFragment extends Fragment {

    private View parentView;
    ListView lvStorage;
    StorageAdapter storageListViewAdapter;

    //Action Mode for toolbar
    private ActionMode mActionMode;
    //SearchView
    private SearchView searchView;
    private MenuItem searchMenuItem;

    public static InstalledAppFragment newInstance() {
        return new InstalledAppFragment();
    }

    public InstalledAppFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstaceState) {
        parentView = (View) inflater.inflate(R.layout.fragment_storage_listview, parent, false);

        setHasOptionsMenu(true);

        initUI();
        initActions();

        return parentView;
    }

    private void initUI() {

        lvStorage = (ListView) parentView.findViewById(R.id.lv_storage);

        storageListViewAdapter = new StorageAdapter(getActivity(), StorageAdapter.ADAPTER_TYPE.APPLICATION);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(allInstalledApplications);
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
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(new ToolbarActionModeCallback(getActivity(), storageListViewAdapter, allInstalledApplications, true));
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
        AppInfo appInfo;
        for (int i = 0; i < selected.size(); i++) {
            appInfo = (AppInfo) storageListViewAdapter.getItem(selected.keyAt(i));
            freedSpace = freedSpace + appInfo.getApkSize();
        }

        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                storageListViewAdapter.removeItem(selected.keyAt(i));
            }
        }

//        Toast.makeText(MusicActivity.this, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), AppUtil.getReadableFileSize((int) freedSpace) + " deleted.", Toast.LENGTH_SHORT).show();
        mActionMode.finish();//Finish action mode after use
    }

    /**************************
     * Methods for option menu
     **************************/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);//Menu Resource, Menu

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
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

