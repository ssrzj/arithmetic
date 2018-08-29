package com.shenzhen.baselib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhen.baselib.BaseLib;


public class ToastUtil {

    private static GradientDrawable gd;
    private static int TextColor= Color.WHITE;
    private static LinearLayout li;
    private static LinearLayout.LayoutParams params;
    private static TextView tv;
    private static Drawable drawable;
    private static Toast toast;
    private static Context context;
    private static int gravity;
    private static int xOffset;
    private static int yOffset;
    private int width;
    private int height;

    private static ToastUtil instance=null;

    private ToastUtil(Context context){
        ToastUtil.context =context;
        initGd(context);
    }
    private static ToastUtil getInstance(Context context){
        if(instance==null){
            instance=new ToastUtil(context);
        }
        return instance;
    }

    private void initGd(Context context){
        gd = new GradientDrawable();
        tv =new TextView(context);
        li=new LinearLayout(context);
        li.setOrientation(LinearLayout.VERTICAL);
        li.setGravity(Gravity.CENTER_HORIZONTAL);
        li.setPadding(40, 20, 40, 20);
        setAlpha(0x88);
        setRadius(50);
        setBackgroud(Color.BLACK);
        setTextColor(Color.WHITE);
        setGravity(Gravity.CLIP_HORIZONTAL, 0, 500);
    }

    public ToastUtil setTextSize(int textSize){
        tv.setTextSize(textSize);
        return this;
    }
    public ToastUtil setSize(int w, int h){
        this.width=w;
        this.height=h;
        if(params==null){
            params = new LinearLayout.LayoutParams(w, h);
        }else{
            params.width=w;
            params.height=h;
        }


        return this;
    }
    public ToastUtil setRadius(float radius){
        gd.setCornerRadius(radius);
        return this;
    }
    public ToastUtil setBackgroud(int backgound){
        gd.setColor(backgound);
        return this;
    }
    public ToastUtil setAlpha(int alpha){
        gd.setAlpha(alpha);
        return this;
    }

    public ToastUtil setTextColor(int TextColor){
        tv.setTextColor(TextColor);
        return this;
    }
    public ToastUtil setGravity(int gravity, int xOffset, int yOffset){
        ToastUtil.gravity =gravity;
        ToastUtil.xOffset =xOffset;
        ToastUtil.yOffset =yOffset;
        return this;
    }
    public ToastUtil setIcon(Drawable drawable){
        ToastUtil.drawable =drawable;
        return this;
    }
//    public static void showToast(final Context context,final String text) {
//        // TODO Auto-generated method stub
//        getInstance(context);
//        showAction(text);
//    }
//    public void showToast(final String text) {
//        // TODO Auto-generated method stub
//        getInstance(context);
//        showAction(text);
//    }
//    private static void showAction(final String text){
//        ((Activity)context).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                li.removeAllViews();
//                if (tv == null) {
//                    tv = new TextView(context);
//                }
//                if (toast != null) {
//                    toast.cancel();
//                }
//                toast = new Toast(context);
//                tv.setText(text);
//                tv.setTextColor(TextColor);
//                if(params==null){
//                    params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//                }
//                li.setLayoutParams(params);
//                if(drawable!=null){
//                    ImageView iv= new ImageView(context);
//                    iv.setImageDrawable(drawable);
//                    LinearLayout.LayoutParams  para=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//                    para.setMargins(0, 0, 0, 30);
//                    iv.setLayoutParams(para);
//                    li.addView(iv);
//                }
//                li.addView(tv);
//                li.setBackgroundDrawable(gd);
//                toast.setDuration(Toast.LENGTH_SHORT);
//                toast.setView(li);
//                toast.setGravity(gravity, xOffset, yOffset);
//                toast.show();
//            }
//        });
//    }

    private static Toast toast2;

    public static Handler mHandler = new Handler(Looper.getMainLooper());


    public static void showToast(final String text)
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            showWithoutContext(text);
        } else
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    showWithoutContext(text);
                }
            });
        }
    }

    private static void showWithoutContext(String text)
    {
        if (toast2 != null)
        {
            toast2.cancel();
        }
        toast2 = Toast.makeText(BaseLib.getInstance().getApplication(), text, Toast.LENGTH_SHORT);
        toast2.show();
    }

}
