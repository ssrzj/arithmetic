package com.shenzhen.baselib.widget;

import android.graphics.Color;

import com.shenzhen.baselib.R;
import com.shenzhen.baselib.utils.ResourcesUtil;
import com.shenzhen.baselib.utils.ViewUtil;


public class LibraryConfig {
    private int mMainColor;
    private int mMainColorResId;

    private int mMainColorPress;
    private int mMainColorPressResId;

    private int mGrayPressColor;
    private int mGrayPressColorResId;

    private int mStrokeColor;
    private int mStrokeColorResId;

    private int mStrokeWidth;
    private int mStrokeWidthResId;

    private int mCornerRadius;
    private int mCornerRadiusResId;

    private int mTitleHeight;
    private int mTitleHeightResId;

    private int mTitleColor;
    private int mTitleColorResId;

    private int mTitleColorPressed;
    private int mTitleColorPressedResId;

    public LibraryConfig()
    {
        setmStrokeWidth(1);
        setmCornerRadius(ViewUtil.dp2px(5));
        setmGrayPressColor(Color.parseColor("#E5E5E5"));
        setmStrokeColor(Color.parseColor("#E5E5E5"));
        setmMainColor(ResourcesUtil.getColor(R.color.main_color));
        setmTitleHeight(ViewUtil.dp2px(50));
        setmTitleColor(ResourcesUtil.getColor(R.color.main_color));
    }

    public int getmTitleColorPressed()
    {
        return mTitleColorPressed;
    }

    public void setmTitleColorPressed(int mTitleColorPressed)
    {
        this.mTitleColorPressed = mTitleColorPressed;
    }

    public int getmTitleColorPressedResId()
    {
        return mTitleColorPressedResId;
    }

    // 需要转换
    public void setmTitleColorPressedResId(int mTitleColorPressedResId)
    {
        this.mTitleColorPressedResId = mTitleColorPressedResId;
        setmTitleColorPressed(ResourcesUtil.getColor(mTitleColorPressedResId));
    }

    public int getmTitleColor()
    {
        return mTitleColor;
    }

    public void setmTitleColor(int mTitleColor)
    {
        this.mTitleColor = mTitleColor;
    }

    public int getmTitleColorResId()
    {
        return mTitleColorResId;
    }

    // 需要转换
    public void setmTitleColorResId(int mTitleColorResId)
    {
        this.mTitleColorResId = mTitleColorResId;
        setmTitleColor(ResourcesUtil.getColor(mTitleColorResId));
    }

    public int getmTitleHeight()
    {
        return mTitleHeight;
    }

    public void setmTitleHeight(int mTitleHeight)
    {
        this.mTitleHeight = mTitleHeight;
    }

    public int getmTitleHeightResId()
    {
        return mTitleHeightResId;
    }

    // 需要转换
    public void setmTitleHeightResId(int mTitleHeightResId)
    {
        this.mTitleHeightResId = mTitleHeightResId;
        setmTitleHeight(ResourcesUtil.getDimensionPixelSize(mTitleHeightResId));
    }

    public int getmCornerRadius()
    {
        return mCornerRadius;
    }

    public void setmCornerRadius(int mCornerRadius)
    {
        this.mCornerRadius = mCornerRadius;
    }

    public int getmCornerRadiusResId()
    {
        return mCornerRadiusResId;
    }

    // 需要转换
    public void setmCornerRadiusResId(int mCornerRadiusResId)
    {
        this.mCornerRadiusResId = mCornerRadiusResId;
        setmCornerRadius(ResourcesUtil.getDimensionPixelSize(mCornerRadiusResId));
    }

    public int getmStrokeColor()
    {
        return mStrokeColor;
    }

    public void setmStrokeColor(int mStrokeColor)
    {
        this.mStrokeColor = mStrokeColor;
    }

    public int getmStrokeColorResId()
    {
        return mStrokeColorResId;
    }

    // 需要转换
    public void setmStrokeColorResId(int mStrokeColorResId)
    {
        this.mStrokeColorResId = mStrokeColorResId;
        setmStrokeColor(ResourcesUtil.getColor(mStrokeColorResId));
    }

    public int getmGrayPressColor()
    {
        return mGrayPressColor;
    }

    public void setmGrayPressColor(int mGrayPressColor)
    {
        this.mGrayPressColor = mGrayPressColor;
    }

    public int getmGrayPressColorResId()
    {
        return mGrayPressColorResId;
    }

    // 需要转换
    public void setmGrayPressColorResId(int mGrayPressColorResId)
    {
        this.mGrayPressColorResId = mGrayPressColorResId;
        setmGrayPressColor(ResourcesUtil.getColor(mGrayPressColorResId));
    }

    public int getmStrokeWidth()
    {
        return mStrokeWidth;
    }

    public void setmStrokeWidth(int mStrokeWidth)
    {
        this.mStrokeWidth = mStrokeWidth;
    }

    public int getmStrokeWidthResId()
    {
        return mStrokeWidthResId;
    }

    // 需要转换
    public void setmStrokeWidthResId(int mStrokeWidthResId)
    {
        this.mStrokeWidthResId = mStrokeWidthResId;
        setmStrokeWidth(ResourcesUtil.getDimensionPixelSize(mStrokeWidthResId));
    }

    public int getmMainColorPress()
    {
        return mMainColorPress;
    }

    public void setmMainColorPress(int mMainColorPress)
    {
        this.mMainColorPress = mMainColorPress;
    }

    public int getmMainColorPressResId()
    {
        return mMainColorPressResId;
    }

    // 需要转换
    public void setmMainColorPressResId(int mMainColorPressResId)
    {
        this.mMainColorPressResId = mMainColorPressResId;
        setmMainColorPress(ResourcesUtil.getColor(mMainColorPressResId));
    }

    public int getmMainColor()
    {
        return mMainColor;
    }

    public void setmMainColor(int mMainColor)
    {
        this.mMainColor = mMainColor;
    }

    public int getmMainColorResId()
    {
        return mMainColorResId;
    }

    // 需要转换
    public void setmMainColorResId(int mMainColorResId)
    {
        this.mMainColorResId = mMainColorResId;
        setmMainColor(ResourcesUtil.getColor(mMainColorResId));
    }
}
