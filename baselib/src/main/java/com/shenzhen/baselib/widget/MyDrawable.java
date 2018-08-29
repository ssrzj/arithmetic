package com.shenzhen.baselib.widget;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;


public class MyDrawable extends LayerDrawable {
    private static final int dA = Color.parseColor("#ffffff");
    private GradientDrawable dB;
    private GradientDrawable dC;
    private int dD;
    private int dE;
    private int dF;
    private int dG;
    private float dH;
    private float dI;
    private float dJ;
    private float dK;

    public MyDrawable() {
        this(new GradientDrawable[]{new GradientDrawable(), new GradientDrawable()});
    }

    public MyDrawable(Drawable[] layers) {
        super(layers);
//        layers = this;
//        this =( (MyDrawable)layers);
        this.dB = ((GradientDrawable) this.getDrawable(0));
        this.dC = ((GradientDrawable) this.getDrawable(1));
        this.dB.setShape(GradientDrawable.RECTANGLE);
        this.dC.setShape(GradientDrawable.RECTANGLE);
        this.color(dA);

    }

    public MyDrawable alpha(int alpha) {
        setAlpha(alpha);
        return this;
    }

    public MyDrawable color(int color) {
        this.dC.setColor(color);
        return this;
    }

    public MyDrawable strokeColor(int color) {
        this.dB.setColor(color);
        return this;
    }

    public MyDrawable corner(float topLeft, float topRight, float bottomLeft, float bottomRight) {
        this.dH = topLeft;
        this.dI = topRight;
        this.dJ = bottomLeft;
        this.dK = bottomRight;

        this.dB.setCornerRadii(new float[]{this.dH, this.dH, this.dI, this.dI, this.dK,
                this.dK, this.dJ, this.dJ});
        this.dC.setCornerRadii(new float[]{this.dH, this.dH, this.dI, this.dI, this.dK,
                this.dK, this.dJ, this.dJ});
        return this;
    }

    public MyDrawable cornerAll(float radius) {
        corner(radius, radius, radius, radius);
        return this;
    }

    public MyDrawable cornerTopLeft(float radius) {
        corner(radius, this.dI, this.dJ, this.dK);
        return this;
    }

    public MyDrawable cornerTopRight(float radius) {
        corner(this.dH, radius, this.dJ, this.dK);
        return this;
    }

    public MyDrawable cornerBottomLeft(float radius) {
        corner(this.dH, this.dI, radius, this.dK);
        return this;
    }

    public MyDrawable cornerBottomRight(float radius) {
        corner(this.dH, this.dI, this.dJ, radius);
        return this;
    }

    public MyDrawable strokeWidth(int left, int top, int right, int bottom) {
        this.dD = left;
        this.dE = top;
        this.dF = right;
        this.dG = bottom;
        setLayerInset(1, this.dD, this.dE, this.dF, this.dG);
        return this;
    }

    public MyDrawable strokeWidthAll(int width) {
        strokeWidth(width, width, width, width);
        return this;
    }

    public MyDrawable strokeWidthLeft(int width) {
        strokeWidth(width, this.dE, this.dF, this.dG);
        return this;
    }

    public MyDrawable strokeWidthTop(int width) {
        strokeWidth(this.dD, width, this.dF, this.dG);
        return this;
    }

    public MyDrawable strokeWidthRight(int width) {
        strokeWidth(this.dD, this.dE, width, this.dG);
        return this;
    }

    public MyDrawable strokeWidthBottom(int width) {
        strokeWidth(this.dD, this.dE, this.dF, width);
        return this;
    }

    public static StateListDrawable getStateListDrawable(Drawable none, Drawable focus, Drawable selected, Drawable pressed) {
        StateListDrawable drawable = new StateListDrawable();
        if (none != null) {
            drawable.addState(getStateNone(), none);
        }
        if (focus != null) {
            drawable.addState(getStateFocus(), focus);
        }
        if (selected != null) {
            drawable.addState(getStateSelected(), selected);
        }
        if (pressed != null) {
            drawable.addState(getStatePressed(), pressed);
        }
        return drawable;
    }

    public static int[] getStateNone() {
        return new int[]{-16842908, -16842913, -16842919};
    }

    public static int[] getStateFocus() {
        return new int[]{16842908, -16842913, -16842919};
    }

    public static int[] getStateSelected() {
        return new int[]{-16842908, 16842913, -16842919};
    }

    public static int[] getStatePressed() {
        return new int[]{-16842908, -16842913, 16842919};
    }
}
