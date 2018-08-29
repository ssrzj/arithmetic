package com.shenzhen.baselib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;


import com.shenzhen.baselib.BaseLib;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class PackageUtil {
    public static void chmod(String permission, String path)
    {
        try
        {
            String command = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Boolean isPackageExist(String packageName)
    {
        PackageManager manager = BaseLib.getInstance().getApplication().getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (PackageInfo pi : pkgList)
        {
            if (pi.packageName.equalsIgnoreCase(packageName))
            {
                return true;
            }
        }
        return false;
    }

    public static PackageInfo getApkPackageInfo(String apkFilePath)
    {
        PackageManager pm = BaseLib.getInstance().getApplication().getPackageManager();
        PackageInfo apkInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA);
        return apkInfo;
    }

    public static PackageInfo getPackageInfo(String packageName)
    {
        PackageManager pm =BaseLib.getInstance().getApplication().getPackageManager();
        PackageInfo apkInfo = null;
        try
        {
            apkInfo = pm.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return apkInfo;
    }

    public static PackageInfo getCurrentPackageInfo()
    {
        return getPackageInfo(BaseLib.getInstance().getApplication().getPackageName());
    }

    public static Boolean installApkPackage(File apkFile)
    {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkFile.getPath()), "application/vnd.android.package-archive");
        BaseLib.getInstance().getApplication().startActivity(intent);
        return true;
    }
    public static Bundle getMetaData(Context context)
    {
        try
        {
            ApplicationInfo info = BaseLib.getInstance().getApplication().getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void startAPP(String appPackageName)
    {
        try
        {
            Intent intent = BaseLib.getInstance().getApplication().getPackageManager().getLaunchIntentForPackage(appPackageName);
            BaseLib.getInstance().getApplication().startActivity(intent);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void startCurrentApp()
    {
        startAPP(BaseLib.getInstance().getApplication().getPackageName());
    }
    public static String getVersionName(){
        return getCurrentPackageInfo().versionName;
    }
}
