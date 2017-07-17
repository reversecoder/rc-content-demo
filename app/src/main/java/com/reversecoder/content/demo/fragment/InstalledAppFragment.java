package com.reversecoder.content.demo.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.adapter.StorageAdapter;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;
import static com.reversecoder.content.demo.activity.StorageManagementActivity.allInstalledApplications;
import static com.reversecoder.content.demo.util.AllConstants.REQUEST_CODE_UNINSTALL_APP;
import static com.reversecoder.content.demo.util.AllConstants.UNINSTALL_PACKAGE_POSITION;

public class InstalledAppFragment extends Fragment {

    private View parentView;
    ListView lvStorage;
    StorageAdapter storageListViewAdapter;

    //    //Action Mode for toolbar
//    private ActionMode mActionMode;
    //SearchView
    private SearchView searchView;
    private MenuItem searchMenuItem;
    private String TAG = InstalledAppFragment.class.getSimpleName();

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
//        initActions();

        return parentView;
    }

    private void initUI() {

        lvStorage = (ListView) parentView.findViewById(R.id.lv_storage);

        storageListViewAdapter = new StorageAdapter(getActivity(), StorageAdapter.ADAPTER_TYPE.APPLICATION_INSTALLED);
        lvStorage.setAdapter(storageListViewAdapter);
        storageListViewAdapter.setData(allInstalledApplications);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_UNINSTALL_APP) {
            if (resultCode == RESULT_OK) {
                if (storageListViewAdapter != null && UNINSTALL_PACKAGE_POSITION != -1) {
                    storageListViewAdapter.removeItem(UNINSTALL_PACKAGE_POSITION);
                }
                Log.d(TAG, "onActivityResult: user accepted the (un)install");
                Toast.makeText(getActivity(), "Uninstalled", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: user canceled the (un)install");
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_FIRST_USER) {
                Log.d(TAG, "onActivityResult: failed to (un)install");
                Toast.makeText(getActivity(), "Failed to uninstalled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

