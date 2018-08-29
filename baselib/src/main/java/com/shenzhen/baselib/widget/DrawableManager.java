package com.shenzhen.baselib.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.shenzhen.baselib.BaseLib;


public class DrawableManager {
    private int dL;
    private int dM;
    private int dN;
    private int dO;
    private float dP;
    private int Y;

    public DrawableManager()
    {
        LibraryConfig config;
        if ((
                config = BaseLib.getInstance().getLibraryConfig()) != null)
        {
            setmColorMain(config.getmMainColor());
            setmColorMainPress(config.getmMainColorPress());
            setmColorStroke(config.getmStrokeColor());
            setmColorGrayPress(config.getmGrayPressColor());
            setmCorner(config.getmCornerRadius());
            setmStrokeWidth(config.getmStrokeWidth());
        }
    }

    public Drawable getLayerMainColor(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = new MyDrawable())
                .color(getmColorMain()).strokeColor(getmColorMain()).strokeWidthAll(getmStrokeWidth());
        if (corner)
        {
            drawable.cornerAll(getmCorner());
        }
        return drawable;
    }

    public Drawable getLayerWhiteStrokeItemSingle(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = new MyDrawable())
                .strokeColor(getmColorStroke()).strokeWidthAll(getmStrokeWidth());
        if (corner)
        {
            drawable.cornerAll(getmCorner());
        }
        return drawable;
    }

    public Drawable getLayerWhiteStrokeItemTop(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = new MyDrawable())
                .strokeColor(getmColorStroke()).strokeWidth(getmStrokeWidth(), getmStrokeWidth(), getmStrokeWidth(), 0);
        if (corner)
        {
            drawable.corner(getmCorner(), getmCorner(), 0.0F, 0.0F);
        }
        return drawable;
    }

    public Drawable getLayerWhiteStrokeItemMiddle()
    {
        MyDrawable drawable;
        (
                drawable = new MyDrawable())
                .strokeColor(getmColorStroke()).strokeWidth(getmStrokeWidth(), getmStrokeWidth(), getmStrokeWidth(), 0);
        return drawable;
    }

    public Drawable getLayerWhiteStrokeItemBottom(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = new MyDrawable())
                .strokeColor(getmColorStroke()).strokeWidthAll(getmStrokeWidth());
        if (corner)
        {
            drawable.corner(0.0F, 0.0F, getmCorner(), getmCorner());
        }
        return drawable;
    }

    public Drawable getLayerMainColorPress(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = new MyDrawable())
                .color(getmColorMainPress()).strokeColor(getmColorMainPress()).strokeWidthAll(getmStrokeWidth());
        if (corner)
        {
            drawable.cornerAll(getmCorner());
        }
        return drawable;
    }

    public Drawable getLayerGrayStrokeItemSingle(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = (MyDrawable)getLayerWhiteStrokeItemSingle(corner))
                .color(getmColorGrayPress());
        return drawable;
    }

    public Drawable getLayerGrayStrokeItemTop(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = (MyDrawable)getLayerWhiteStrokeItemTop(corner))
                .color(getmColorGrayPress());
        return drawable;
    }

    public Drawable getLayerGrayStrokeItemMiddle()
    {
        MyDrawable drawable;
        (
                drawable = (MyDrawable)getLayerWhiteStrokeItemMiddle())
                .color(getmColorGrayPress());
        return drawable;
    }

    public Drawable getLayerGrayStrokeItemBottom(boolean corner)
    {
        MyDrawable drawable;
        (
                drawable = (MyDrawable)getLayerWhiteStrokeItemBottom(corner))
                .color(getmColorGrayPress());
        return drawable;
    }

    public Drawable getSelectorWhiteGray(boolean corner)
    {
        MyDrawable white = new MyDrawable();
        if (corner)
        {
            white.cornerAll(getmCorner());
        }
        MyDrawable gray;
        (
                gray = new MyDrawable())
                .color(getmColorGrayPress());
        if (corner)
        {
            gray.cornerAll(getmCorner());
        }

        return MyDrawable.getStateListDrawable(white, null, null, gray);
    }

    public Drawable getSelectorMainColor(boolean corner)
    {
        return MyDrawable.getStateListDrawable(getLayerMainColor(corner), null, null, getLayerMainColorPress(corner));
    }

    public Drawable getSelectorWhiteGrayStrokeItemSingle(boolean corner)
    {
        return MyDrawable.getStateListDrawable(getLayerWhiteStrokeItemSingle(corner), null, null,
                getLayerGrayStrokeItemSingle(corner));
    }

    public Drawable getSelectorWhiteGrayStrokeItemTop(boolean corner)
    {
        return MyDrawable.getStateListDrawable(getLayerWhiteStrokeItemTop(corner), null, null,
                getLayerGrayStrokeItemTop(corner));
    }

    public Drawable getSelectorWhiteGrayStrokeItemMiddle()
    {
        StateListDrawable localStateListDrawable;
        return localStateListDrawable = MyDrawable.getStateListDrawable(getLayerWhiteStrokeItemMiddle(), null, null, getLayerGrayStrokeItemMiddle());
    }

    public Drawable getSelectorWhiteGrayStrokeItemBottom(boolean corner)
    {
        return MyDrawable.getStateListDrawable(getLayerWhiteStrokeItemBottom(corner), null, null,
                getLayerGrayStrokeItemBottom(corner));
    }

    public int getmColorMain()
    {
        return this.dL;
    }

    public int getmStrokeWidth()
    {
        return this.Y;
    }

    public void setmStrokeWidth(int mStrokeWidth)
    {
        this.Y = mStrokeWidth;
    }

    public float getmCorner()
    {
        return this.dP;
    }

    public void setmCorner(float mCorner)
    {
        this.dP = mCorner;
    }

    public void setmColorMain(int mColorMain)
    {
        this.dL = mColorMain;
    }

    public int getmColorStroke()
    {
        return this.dM;
    }

    public void setmColorStroke(int mColorStroke)
    {
        this.dM = mColorStroke;
    }

    public int getmColorMainPress()
    {
        return this.dN;
    }

    public void setmColorMainPress(int mColorMainPress)
    {
        this.dN = mColorMainPress;
    }

    public int getmColorGrayPress()
    {
        return this.dO;
    }

    public void setmColorGrayPress(int mColorGrayPress)
    {
        this.dO = mColorGrayPress;
    }
}
