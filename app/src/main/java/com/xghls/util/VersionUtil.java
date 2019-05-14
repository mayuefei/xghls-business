package com.xghls.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionUtil {
    /**
     * 返回当前版本号
     * @param context
     * @return
     */
    public static long getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return (long) info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 返回当前版本名称
     * @param cxt
     * @return
     */
    public static String getVersionName(Context cxt) {
        try {
            PackageManager manager = cxt.getPackageManager();
            PackageInfo info = manager.getPackageInfo(cxt.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
