package com.reversecoder.content.demo.toolbar;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.activity.MusicActivity;
import com.reversecoder.content.demo.adapter.StorageAdapter;

import java.util.ArrayList;

public class ToolbarActionModeCallback<T> implements ActionMode.Callback {

    private Context context;
    private StorageAdapter storageAdapter;
    private ArrayList<T> message_models;


    public ToolbarActionModeCallback(Context context, StorageAdapter listView_adapter, ArrayList<T> message_models) {
        this.context = context;
        this.storageAdapter = listView_adapter;
        this.message_models = message_models;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_action, menu);//Inflate the menu over action mode
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //So here show action menu according to SDK Levels
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_delete), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
//
//                //Check if current action mode is from ListView Fragment or RecyclerView Fragment
//                if (isListViewFragment) {
//                    Fragment listFragment = new MainActivity().getFragment(0);//Get list view Fragment
//                    if (listFragment != null)
//                        //If list fragment is not null
//                        ((ListView_Fragment) listFragment).deleteRows();//delete selected rows
//                } else {
//                    //If current fragment is recycler view fragment
//                    Fragment recyclerFragment = new MainActivity().getFragment(1);//Get recycler view fragment
//                    if (recyclerFragment != null)
//                        //If recycler fragment not null
//                        ((RecyclerView_Fragment) recyclerFragment).deleteRows();//delete selected rows
//                }
//                break;
            case R.id.action_search:

                break;
        }
        return false;
    }


    @Override
    public void onDestroyActionMode(ActionMode mode) {

        //When action mode destroyed remove selected selections and set action mode to null
        //First check current fragment action mode
            storageAdapter.removeSelection();  // remove selection
        if(storageAdapter.getAdapterType()== StorageAdapter.ADAPTER_TYPE.MUSIC){
            ((MusicActivity)context).setNullToActionMode();
        }

    }
}
