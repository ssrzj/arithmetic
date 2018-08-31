package com.sz.ssr.app;

import android.app.Application;
import android.content.Context;

import com.shenzhen.baselib.BaseLib;
import com.shenzhen.baselib.utils.JsonUtil;

public class App extends Application {
    private static Application mInstance;
    private Context mContext;
    public App() {

    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.mInstance = this;
        this.mContext = this;
        // 初始化外部lib
        BaseLib.getInstance().init(this); // BaseLib初始化
    }
    public static Application getInstance(){
        if(mInstance==null){
            mInstance = new App();
        }
        return mInstance;
    }

    public Context getContext() {
        if(mContext==null){
            mContext = new App().mContext;
        }
        return mContext;
    }
}
