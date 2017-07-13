package com.reversecoder.content.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.reversecoder.content.demo.R;
import com.reversecoder.content.helper.model.AppInfo;
import com.reversecoder.content.helper.model.AudioInfo;
import com.reversecoder.content.helper.model.FileInfo;
import com.reversecoder.content.helper.model.ImageInfo;
import com.reversecoder.content.helper.model.VideoInfo;
import com.reversecoder.content.helper.model.WrapperBase;
import com.reversecoder.content.helper.util.AppUtil;

import java.util.ArrayList;

/**
 * @author Md. Rashadul Alam
 */
public class StorageAdapter<T> extends BaseAdapter {

    private Activity mActivity;
    private ArrayList<T> mData;
    private static LayoutInflater inflater = null;
    private ADAPTER_TYPE mAdapterType;

    public StorageAdapter(Activity activity, ADAPTER_TYPE adapterType) {
        mActivity = activity;
        mAdapterType = adapterType;
        mData = new ArrayList<T>();
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<T> getData() {
        return mData;
    }

    public void setData(ArrayList<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

//    public int getItemPosition(String name) {
//        for (int i = 0; i < mData.size(); i++) {
//            if (((SpinnerItem) mData.get(i)).getName().contains(name)) {
//                return i;
//            }
//        }
//        return -1;
//    }

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
            Glide
                    .with(mActivity)
                    .load(imageInfo.getUri())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                    .into(ivDefaultIcon);
        } else {

            if (convertView == null)
                vi = inflater.inflate(R.layout.list_row_storage, null);

            ImageView ivDefaultIcon = (ImageView) vi.findViewById(R.id.iv_default_icon);
            TextView tvTitle = (TextView) vi.findViewById(R.id.tv_title);
            TextView tvSubTitle = (TextView) vi.findViewById(R.id.tv_subitle);

            if (mAdapterType == ADAPTER_TYPE.MUSIC) {
                AudioInfo audioInfo = (AudioInfo) mItem;
                ivDefaultIcon.setBackgroundResource(R.drawable.music_default);
                tvTitle.setText(audioInfo.getTitle());
                tvSubTitle.setText(audioInfo.getReadableSize());
            } else if (mAdapterType == ADAPTER_TYPE.MOVIE) {
                VideoInfo videoInfo = (VideoInfo) mItem;
                ivDefaultIcon.setBackgroundResource(R.drawable.movie_default);
                tvTitle.setText(videoInfo.getTitle());
                tvSubTitle.setText(videoInfo.getReadableSize());
            } else if (mAdapterType == ADAPTER_TYPE.APPLICATION) {
                AppInfo appInfo = (AppInfo) mItem;
                ivDefaultIcon.setImageDrawable(appInfo.getIcon());
                tvTitle.setText(appInfo.getAppName());
                tvSubTitle.setText(AppUtil.getReadableFileSize((int) appInfo.getApkSize()));
            } else if (mAdapterType == ADAPTER_TYPE.OTHER) {
                FileInfo other = (FileInfo) mItem;
                ivDefaultIcon.setBackgroundResource(R.drawable.file_default);
                tvTitle.setText(other.getTitle());
                tvSubTitle.setText(AppUtil.getReadableFileSize(other.getSize()));
            }
        }


        return vi;
    }

    public enum ADAPTER_TYPE {MUSIC, MOVIE, APPLICATION, OTHER, PICTURE}
}