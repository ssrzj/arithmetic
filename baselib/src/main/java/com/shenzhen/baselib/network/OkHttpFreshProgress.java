package com.shenzhen.baselib.network;

import java.io.IOException;

/**
 * Created by lllidan on 2017/11/10.
 */

public interface OkHttpFreshProgress {
    void start();
    void freshProgress(long total, long current);
    void success();
    void failed(IOException e);

}
