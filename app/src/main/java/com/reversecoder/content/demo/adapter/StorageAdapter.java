package com.reversecoder.content.demo.adapter;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.reversecoder.content.demo.R;
import com.reversecoder.content.demo.activity.PictureActivity;
import com.reversecoder.content.helper.model.AppInfo;
import com.reversecoder.content.helper.model.AudioInfo;
import com.reversecoder.content.helper.model.FileInfo;
import com.reversecoder.content.helper.model.ImageInfo;
import com.reversecoder.content.helper.model.VideoInfo;
import com.reversecoder.content.helper.model.WrapperBase;
import com.reversecoder.content.helper.util.AppUtil;

import java.util.ArrayList;

import static com.reversecoder.content.demo.util.AllConstants.REQUEST_CODE_UNINSTALL_APP;
import static com.reversecoder.content.demo.util.AllConstants.UNINSTALL_PACKAGE_POSITION;

/**
 * @author Md. Rashadul Alam
 */
public class StorageAdapter<T> extends BaseAdapter implements Filterable {

    private Activity mActivity;
    private ArrayList<T> mData;
    private ArrayList<T> mFilterData;
    private static LayoutInflater inflater = null;
    private ADAPTER_TYPE mAdapterType;
    private SparseBooleanArray mSelectedItemsIds;
    private FriendFilter friendFilter;
    private boolean isChecBoxMode = false;

    public StorageAdapter(Activity activity, ADAPTER_TYPE adapterType) {
        mActivity = activity;
        mAdapterType = adapterType;
        mData = new ArrayList<T>();
        mFilterData = new ArrayList<T>();
        mSelectedItemsIds = new SparseBooleanArray();
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<T> getData() {
        return mData;
    }

    public void setData(ArrayList<T> data) {
        mData = data;
        mFilterData = data;
        notifyDataSetChanged();
    }

    public int getItemPosition(String name) {
        for (int i = 0; i < mData.size(); i++) {
            if (mAdapterType == ADAPTER_TYPE.PICTURE) {
                ImageInfo imageInfo = (ImageInfo) mData.get(i);
                if (imageInfo.getTitle().contains(name)) {
                    return i;
                }
            } else if (mAdapterType == ADAPTER_TYPE.MUSIC) {
                AudioInfo audioInfo = (AudioInfo) mData.get(i);
                if (audioInfo.getTitle().contains(name)) {
                    return i;
                }
            } else if (mAdapterType == ADAPTER_TYPE.MOVIE) {
                VideoInfo videoInfo = (VideoInfo) mData.get(i);
                if (videoInfo.getTitle().contains(name)) {
                    return i;
                }
            } else if (mAdapterType == ADAPTER_TYPE.APPLICATION_INSTALLED) {
                AppInfo appInfo = (AppInfo) mData.get(i);
                if (appInfo.getAppName().contains(name)) {
                    return i;
                }
            } else if (mAdapterType == ADAPTER_TYPE.APPLICATION_UNUSED) {
                AppInfo appInfo = (AppInfo) mData.get(i);
                if (appInfo.getAppName().contains(name)) {
                    return i;
                }
            } else if (mAdapterType == ADAPTER_TYPE.OTHER) {
                FileInfo fileInfo = (FileInfo) mData.get(i);
                if (fileInfo.getTitle().contains(name)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    public void showCheckBox(boolean show) {
        isChecBoxMode = show;
        notifyDataSetChanged();
    }

    public ADAPTER_TYPE getAdapterType() {
        return mAdapterType;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View vi = convertView;

        WrapperBase mItem = (WrapperBase) getItem(position);

        if (mAdapterType == ADAPTER_TYPE.PICTURE) {
            if (convertView == null)
                vi = inflater.inflate(R.layout.grid_row_storage, null);

            ImageInfo imageInfo = (ImageInfo) mItem;
            ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
            CheckBox cbSelectedItem = (CheckBox) vi.findViewById(R.id.cb_grid_item);
            Glide
                    .with(mActivity)
                    .load(imageInfo.getUri())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .apply(new RequestOptions().placeholder(R.drawable.picture_default))
                    .into(ivDefaultIcon);

            if (mSelectedItemsIds.get(position)) {
                cbSelectedItem.setChecked(true);
            } else {
                cbSelectedItem.setChecked(false);
            }

            if (isChecBoxMode) {
                cbSelectedItem.setVisibility(View.VISIBLE);
            } else {
                cbSelectedItem.setVisibility(View.GONE);
            }

            cbSelectedItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((PictureActivity) mActivity).mActionMode != null)
                        ((PictureActivity) mActivity).onListItemSelect(position);
                }
            });

        } else {

            if (mAdapterType == ADAPTER_TYPE.MUSIC) {

                if (convertView == null)
                    vi = inflater.inflate(R.layout.list_row_storage, null);

                ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
                TextView tvTitle = (TextView) vi.findViewById(R.id.tv_title);
                TextView tvSubTitle = (TextView) vi.findViewById(R.id.tv_subitle);

                AudioInfo audioInfo = (AudioInfo) mItem;
                Glide
                        .with(mActivity)
                        .load(ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), audioInfo.getAlbumId()))
                        .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                        .apply(new RequestOptions().placeholder(R.drawable.music_default))
                        .into(ivDefaultIcon);
                tvTitle.setText(audioInfo.getTitle());
                tvSubTitle.setText(audioInfo.getReadableSize());
            } else if (mAdapterType == ADAPTER_TYPE.MOVIE) {

                if (convertView == null)
                    vi = inflater.inflate(R.layout.list_row_storage, null);

                ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
                TextView tvTitle = (TextView) vi.findViewById(R.id.tv_title);
                TextView tvSubTitle = (TextView) vi.findViewById(R.id.tv_subitle);

                VideoInfo videoInfo = (VideoInfo) mItem;
                Glide
                        .with(mActivity)
                        .load(videoInfo.getUri())
                        .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                        .apply(new RequestOptions().placeholder(R.drawable.movie_default))
                        .into(ivDefaultIcon);
                tvTitle.setText(videoInfo.getTitle());
                tvSubTitle.setText(videoInfo.getReadableSize());
            } else if (mAdapterType == ADAPTER_TYPE.APPLICATION_INSTALLED) {

                if (convertView == null)
                    vi = inflater.inflate(R.layout.list_row_installed_app, null);

                ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
                TextView tvTitle = (TextView) vi.findViewById(R.id.tv_title);
                TextView tvSubTitle = (TextView) vi.findViewById(R.id.tv_subitle);
                Button btnAction = (Button) vi.findViewById(R.id.btn_action);

                final AppInfo appInfo = (AppInfo) mItem;
                ivDefaultIcon.setImageDrawable(appInfo.getIcon());
                tvTitle.setText(appInfo.getAppName());
                tvSubTitle.setText(AppUtil.getReadableFileSize((int) appInfo.getApkSize()));

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UNINSTALL_PACKAGE_POSITION = position;
                        Intent intent;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                            intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                        } else {
                            intent = new Intent(Intent.ACTION_DELETE);
                        }
                        intent.setData(Uri.fromParts("package", appInfo.getPackageName(), null));
                        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                        mActivity.startActivityForResult(intent, REQUEST_CODE_UNINSTALL_APP);
                    }
                });

            } else if (mAdapterType == ADAPTER_TYPE.APPLICATION_UNUSED) {

                if (convertView == null)
                    vi = inflater.inflate(R.layout.list_row_storage, null);

                ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
                TextView tvTitle = (TextView) vi.findViewById(R.id.tv_title);
                TextView tvSubTitle = (TextView) vi.findViewById(R.id.tv_subitle);

                AppInfo appInfo = (AppInfo) mItem;
                ivDefaultIcon.setImageDrawable(appInfo.getIcon());
                tvTitle.setText(appInfo.getAppName());
                tvSubTitle.setText(AppUtil.getReadableFileSize((int) appInfo.getApkSize()));

            } else if (mAdapterType == ADAPTER_TYPE.OTHER) {

                if (convertView == null)
                    vi = inflater.inflate(R.layout.list_row_storage, null);

                ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
                TextView tvTitle = (TextView) vi.findViewById(R.id.tv_title);
                TextView tvSubTitle = (TextView) vi.findViewById(R.id.tv_subitle);

                FileInfo other = (FileInfo) mItem;
                ivDefaultIcon.setBackgroundResource(R.drawable.file_default);
                tvTitle.setText(other.getTitle());
                tvSubTitle.setText(AppUtil.getReadableFileSize(other.getSize()));
            }

            /**************************************************************
             * Change background color of the selected items in list view *
             **************************************************************/
            vi.setBackgroundColor(mSelectedItemsIds.get(position) ? mActivity.getResources().getColor(R.color.colorLightBlue)
                    : Color.TRANSPARENT);
        }

        return vi;
    }

    public enum ADAPTER_TYPE {MUSIC, MOVIE, APPLICATION_INSTALLED, APPLICATION_UNUSED, OTHER, PICTURE}

    /**************************************************************
     * Methods required for do selections, remove selections, etc.*
     **************************************************************/

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    /***********************
     * Search view methods *
     ***********************/
    /**
     * Get custom filter
     *
     * @return filter
     */
    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new FriendFilter();
        }

        return friendFilter;
    }

    /**
     * Custom filter for list
     * Filter content in list according to the search text
     */
    private class FriendFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<T> tempList = new ArrayList<T>();

                // search content in list
                for (T item : mFilterData) {

                    if (mAdapterType == ADAPTER_TYPE.PICTURE) {
                        ImageInfo imageInfo = (ImageInfo) item;
                        if (imageInfo.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(item);
                        }
                    } else if (mAdapterType == ADAPTER_TYPE.MUSIC) {
                        AudioInfo audioInfo = (AudioInfo) item;
                        if (audioInfo.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(item);
                        }
                    } else if (mAdapterType == ADAPTER_TYPE.MOVIE) {
                        VideoInfo videoInfo = (VideoInfo) item;
                        if (videoInfo.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(item);
                        }
                    } else if (mAdapterType == ADAPTER_TYPE.APPLICATION_INSTALLED) {
                        AppInfo appInfo = (AppInfo) item;
                        if (appInfo.getAppName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(item);
                        }
                    } else if (mAdapterType == ADAPTER_TYPE.APPLICATION_UNUSED) {
                        AppInfo appInfo = (AppInfo) item;
                        if (appInfo.getAppName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(item);
                        }
                    } else if (mAdapterType == ADAPTER_TYPE.OTHER) {
                        FileInfo fileInfo = (FileInfo) item;
                        if (fileInfo.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(item);
                        }
                    }

                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = mFilterData.size();
                filterResults.values = mFilterData;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<T>) results.values;
            notifyDataSetChanged();
        }
    }

}