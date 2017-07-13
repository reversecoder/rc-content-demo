package com.reversecoder.content.nonmedia.application;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.RemoteException;
import android.util.Log;

import com.reversecoder.content.helper.model.AppInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ApplicationLoader {

    private static final String TAG = ApplicationLoader.class.getSimpleName();

    public static ArrayList<AppInfo> getInstalledApplications(Context context) {
        final ArrayList<AppInfo> res = new ArrayList<AppInfo>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

            if (((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) != true) {

                PackageManager pm = context.getPackageManager();
                Method getPackageSizeInfo;

                final AppInfo appInfo = new AppInfo();
                appInfo.setAppName(p.applicationInfo.loadLabel(context.getPackageManager()).toString());
                appInfo.setPackageName(p.packageName);
                appInfo.setDataDirectory(p.applicationInfo.dataDir);
                appInfo.setVersionName(p.versionName);
                appInfo.setVersionCode(p.versionCode);
                appInfo.setIcon(p.applicationInfo.loadIcon(context.getPackageManager()));

                cachePackState cachePState = new cachePackState();
                cachePState.setPackageStatsCallback(new PackageStatsCallback() {
                    @Override
                    public void getPackageStats(PackageStats packageStats) {
                        if (packageStats != null) {
                            appInfo.setCacheSize(packageStats.cacheSize);
                            appInfo.setDataSize(packageStats.dataSize);
                            appInfo.setPackageSize(packageStats.dataSize + packageStats.cacheSize);
                            appInfo.setApkSize(packageStats.codeSize);
                        }

                        res.add(appInfo);
                    }
                });
                try {
                    getPackageSizeInfo = pm.getClass().getMethod(
                            "getPackageSizeInfo", String.class,
                            IPackageStatsObserver.class);
                    getPackageSizeInfo.invoke(pm, p.packageName, cachePState); //Call the inner class
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    /*
* Inner class which will get the data size for application
* */
    private static class cachePackState extends IPackageStatsObserver.Stub {

        PackageStatsCallback mPackageStatsCallback;

        @Override
        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                throws RemoteException {
//            Log.d(TAG + ": Package Name", pStats.packageName + "");
//            Log.d(TAG + ": Cache Size", pStats.cacheSize + "");
//            Log.d(TAG + ": Data Size", pStats.dataSize + "");
            long packageSize = pStats.dataSize + pStats.cacheSize;
//            Log.d(TAG + ": Total Package Size", " " + packageSize);
//            Log.d(TAG + ": APK Size", pStats.codeSize + "");
            if (mPackageStatsCallback != null) {
                mPackageStatsCallback.getPackageStats(pStats);
            }
        }

        public void setPackageStatsCallback(PackageStatsCallback packageStatsCallback) {
            mPackageStatsCallback = packageStatsCallback;
        }
    }

    public interface PackageStatsCallback {
        public void getPackageStats(PackageStats packageStats);
    }
}
