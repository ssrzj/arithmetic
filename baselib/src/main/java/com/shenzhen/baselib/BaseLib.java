package com.shenzhen.baselib;

import android.app.Application;
import android.content.Context;

import com.shenzhen.baselib.widget.LibraryConfig;

/**
 * Created by lllidan on 2017/11/9.
 */

public class BaseLib {

    private Application mApp;
    private LibraryConfig mLibraryConfig;
    private static BaseLib mInstance;
    private Context mContext;
    public static BaseLib getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new BaseLib();
        }
        return mInstance;
    }

    public void init(Application application)
    {
        this.mApp = application;
        mContext = application;
    }
    public Context getContext(){
        return mContext;
    }

    public Application getApplication(){
        return this.mApp;
    }
    public LibraryConfig getLibraryConfig(){
        if(mLibraryConfig==null){
            mLibraryConfig = new LibraryConfig();
        }
        return mLibraryConfig;
    }
    public void setLibraryConfig(LibraryConfig libraryConfig){
        this.mLibraryConfig = libraryConfig;
    }
}
