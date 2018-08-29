package com.shenzhen.baselib.network;

/**
 * Created by lllidan on 2017/11/9.
 */

public interface OkHttpCallback {
    void onError(Exception e);

    void onResponse(String result);
}
