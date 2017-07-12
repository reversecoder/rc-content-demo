package com.reversecoder.content.nonmedia.application;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

public class ApplicationLoader {

    public static ArrayList<PackageInfo> getInstalledApplications(Context context) {
        ArrayList<PackageInfo> installedApps = new ArrayList<PackageInfo>();
        List<PackageInfo> PackList = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < PackList.size(); i++) {
            PackageInfo PackInfo = PackList.get(i);
            if (((PackInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) != true) {
                installedApps.add(PackInfo);
//                String AppName = PackInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
//                Log.e("DeviceApp" + Integer.toString(i), AppName);
            }
        }
        return installedApps;
    }
}
