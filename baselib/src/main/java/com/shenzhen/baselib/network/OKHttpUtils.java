package com.shenzhen.baselib.network;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.shenzhen.baselib.constant.Constant;
import com.shenzhen.baselib.utils.JsonUtil;
import com.shenzhen.baselib.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lllidan on 2017/11/9.
 */

public class OKHttpUtils {
    private static volatile OKHttpUtils mInstance = null;
    private static OkHttpClient mclient = new OkHttpClient();
    private static OKHttpUtils mOkHttpUtils;
    private OkHttpCallback mCallback;
    private static String METHOD="Get";
    public static final String GET="Get";
    public static final String POST="Post";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == Constant.NETWORK_SUCESS) {
                String result = (String) msg.obj;
                mCallback.onResponse(result);
            } else {
                Exception e = (Exception) msg.obj;
                mCallback.onError(e);
            }
        }
    };

    public static OKHttpUtils getInstance() {
        mclient = new OkHttpClient();
        mOkHttpUtils = new OKHttpUtils();
        return mOkHttpUtils;
    }
    private OKHttpUtils() {

    }
    public OKHttpUtils requestGet(String url, Map<String, String> map){
        Iterator iterator = map.entrySet().iterator();
        StringBuilder strBuilder= new StringBuilder(url);
        strBuilder.append("?");
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if(!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)){
            strBuilder.append(key+"="+value+"&");
            }
        }
        url = strBuilder.substring(0,strBuilder.length()-1);
        LogUtil.e(""+url);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        final Request request = requestBuilder.build();
        mclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.obj = e ;
                message.what = Constant.NETWORK_FAILED;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                message.what = Constant.NETWORK_SUCESS;
                handler.sendMessage(message);

            }
        });
        return mOkHttpUtils;
    }
    public OKHttpUtils requestGet(String url){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        final Request request = requestBuilder.build();
        mclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.obj = e ;
                message.what = Constant.NETWORK_FAILED;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                message.what = Constant.NETWORK_SUCESS;
                handler.sendMessage(message);

            }
        });
        return mOkHttpUtils;
    }




    public OKHttpUtils requestPost(String url,Map<String,String>map){
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for(String key:map.keySet()){
            formBodyBuilder.add(key,map.get(key));
        }
        FormBody formBody = formBodyBuilder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        mclient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.obj = e ;
                message.what = Constant.NETWORK_FAILED;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                message.what = Constant.NETWORK_SUCESS;
                handler.sendMessage(message);

            }
        });
        return mOkHttpUtils;
    }
    public void downLoadFile(String download_link, final String destFileDir, String fileName, final OkHttpFreshProgress progress) {
        if (TextUtils.isEmpty(download_link)) {
            return;
        }

        final File file = new File(destFileDir + fileName);

        final Request request = new Request.Builder().url(download_link).build();
        final Call call = mclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.i(JsonUtil.object2Json(e));
                if (progress != null) {
                    progress.failed(e);
                }
                LogUtil.i("下载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                LogUtil.i(JsonUtil.object2Json(response));

                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    if(total>0){
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
//                        LogUtil.i("current------>" + current);
                        if (progress!=null){
                            progress.freshProgress(total,current);
                        }
                    }
                    fos.flush();
                    if (progress != null) {
                        progress.success();
                        LogUtil.i( "下载成功!");
                    }
                    }else{
                        LogUtil.i("下载失败!");
                        progress.failed(new IOException("文件不存在"));
                    }
                } catch (IOException e) {
                    LogUtil.i(e.toString());
                    LogUtil.i("下载失败!");
                    if (progress!=null){
                        progress.failed(e);
                    }
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        LogUtil.i(e.toString());
                    }
                }
            }
        });
    }


    public OKHttpUtils setCallback(OkHttpCallback callback){
        this.mCallback = callback;
        return mOkHttpUtils;
    }
}
