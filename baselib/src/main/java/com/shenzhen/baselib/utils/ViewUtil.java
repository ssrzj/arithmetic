package com.shenzhen.baselib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


import com.shenzhen.baselib.BaseLib;

import java.util.List;


public class ViewUtil {
    @SuppressLint("NewApi")
    public static void scrollToViewY(final ScrollView sv, final int y, int delay)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            HandlerUtil.runOnUiThreadDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    sv.scrollTo(0, y);
                }
            }, delay);
        }
    }

    // -------------------------layoutParams
    public static LinearLayout.LayoutParams getLayoutParamsLinearLayoutWW()
    {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public static LinearLayout.LayoutParams getLayoutParamsLinearLayoutMM()
    {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static LinearLayout.LayoutParams getLayoutParamsLinearLayoutMW()
    {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public static LinearLayout.LayoutParams getLayoutParamsLinearLayoutWM()
    {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static RelativeLayout.LayoutParams getLayoutParamsRelativeLayoutWW()
    {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    public static RelativeLayout.LayoutParams getLayoutParamsRelativeLayoutMM()
    {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    public static RelativeLayout.LayoutParams getLayoutParamsRelativeLayoutMW()
    {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    public static RelativeLayout.LayoutParams getLayoutParamsRelativeLayoutWM()
    {
        return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    public static FrameLayout.LayoutParams getLayoutParamsFrameLayoutWW()
    {
        return new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public static FrameLayout.LayoutParams getLayoutParamsFrameLayoutMM()
    {
        return new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    public static FrameLayout.LayoutParams getLayoutParamsFrameLayoutMW()
    {
        return new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    public static FrameLayout.LayoutParams getLayoutParamsFrameLayoutWM()
    {
        return new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    // ------------------------layoutInflater
    public static LayoutInflater getLayoutInflater()
    {
        return LayoutInflater.from(BaseLib.getInstance().getApplication());
    }

    public static View inflate(int resource, ViewGroup root)
    {
        return getLayoutInflater().inflate(resource, root);
    }

    public static View inflate(int resource, ViewGroup root, boolean attachToRoot)
    {
        return getLayoutInflater().inflate(resource, root, attachToRoot);
    }

    public static DisplayMetrics getDisplayMetrics()
    {
        return BaseLib.getInstance().getApplication().getResources().getDisplayMetrics();
    }

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

    public static float getDensity()
    {
        return BaseLib.getInstance().getApplication().getResources().getDisplayMetrics().density;
    }

    public static float getScaledDensity()
    {
        return BaseLib.getInstance().getApplication().getResources().getDisplayMetrics().scaledDensity;
    }

    public static int sp2px(float sp)
    {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    public static int dp2px(float dp)
    {
        final float scale = getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px)
    {
        final float scale = getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int getScaleHeight(int originalWidth, int originalHeight, int scaleWidth)
    {
        return originalHeight * scaleWidth / originalWidth;
    }

    public static int getScaleWidth(int originalWidth, int originalHeight, int scaleHeight)
    {
        return originalWidth * scaleHeight / originalHeight;
    }

    /**
     * 判断当前线程是否是UI线程.
     *
     * @return
     */
    public static boolean isUIThread()
    {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 隐藏输入法
     */
    public static void hideInputMethod()
    {
        InputMethodManager imm = (InputMethodManager) BaseLib.getInstance().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
        {
            imm.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示输入法
     *
     * @param view
     * @param requestFocus
     */
    public static void showInputMethod(View view, boolean requestFocus)
    {
        if (requestFocus)
        {
            view.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager)BaseLib.getInstance().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 重置listview高度，解决和scrollview嵌套问题
     *
     * @param listView
     */
    public static void resetListViewHeightBasedOnChildren(ListView listView)
    {
        int totalHeight = getListViewTotalHeight(listView);
        if (totalHeight > 0)
        {
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight;
            params.height += 5;
            listView.setLayoutParams(params);
        }
    }

    public static int getListViewTotalHeight(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem != null)
            {
                listItem.measure(0, 0);
                int height = listItem.getMeasuredHeight();
                int dividerHeight = listView.getDividerHeight() * (listAdapter.getCount() - 1);
                totalHeight += (height + dividerHeight);
            }
        }
        return totalHeight;
    }

    public static void measureView(View v)
    {
        if (v == null)
        {
            return;
        }
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
    }

    public static int getViewHeight(View view)
    {
        int height = 0;
        if (view != null)
        {
            height = view.getHeight();
            if (height <= 0)
            {
                measureView(view);
                height = view.getMeasuredHeight();
            }
        }
        return height;
    }

    public static int getViewWidth(View view)
    {
        int width = 0;
        if (view != null)
        {
            width = view.getWidth();
            if (width <= 0)
            {
                measureView(view);
                width = view.getMeasuredWidth();
            }
        }
        return width;
    }

    public static void toggleEmptyMsgByList(List<? extends Object> list, View emptyView)
    {
        if (emptyView != null)
        {
            if (list != null && list.size() > 0)
            {
                if (emptyView.getVisibility() != View.GONE)
                {
                    emptyView.setVisibility(View.GONE);
                }
            } else
            {
                if (emptyView.getVisibility() != View.VISIBLE)
                {
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public static void toggleViewByList(List<? extends Object> list, View view)
    {
        if (view != null)
        {
            if (list != null && list.size() > 0)
            {
                if (view.getVisibility() != View.VISIBLE)
                {
                    view.setVisibility(View.VISIBLE);
                }
            } else
            {
                if (view.getVisibility() != View.GONE)
                {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }


    public static View wrapTitle(int contentLayoutId, int titleLayoutId)
    {
        LayoutInflater inflater = LayoutInflater.from(BaseLib.getInstance().getApplication());
        View contentView = inflater.inflate(contentLayoutId, null);
        View titleView = inflater.inflate(titleLayoutId, null);
        return wrapTitle(contentView, titleView);
    }

    public static View wrapTitle(View contentView, View titleView)
    {
        LinearLayout linAll = new LinearLayout(BaseLib.getInstance().getApplication());
        linAll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams paramsTitle = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams paramsContent = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linAll.addView(titleView, paramsTitle);
        linAll.addView(contentView, paramsContent);
        return linAll;
    }

    public static boolean setViewHeight(View view, int height)
    {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null)
        {
            params.height = height;
            view.setLayoutParams(params);
            return true;
        }
        return false;
    }

    public static boolean setViewWidth(View view, int width)
    {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null)
        {
            params.width = width;
            view.setLayoutParams(params);
            return true;
        }
        return false;
    }
}
