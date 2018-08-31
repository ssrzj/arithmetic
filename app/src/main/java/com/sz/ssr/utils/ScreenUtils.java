package com.sz.ssr.utils;

import android.util.DisplayMetrics;

import com.shenzhen.baselib.BaseLib;
import com.sz.ssr.app.App;

public class ScreenUtils {
    public static int getScreenWidth()
    {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight()
    {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.heightPixels;
    }
    public static DisplayMetrics getDisplayMetrics()
    {
        return App.getInstance().getResources().getDisplayMetrics();
    }
}
