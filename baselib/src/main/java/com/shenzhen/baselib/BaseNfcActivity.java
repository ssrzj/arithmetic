package com.shenzhen.baselib;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.shenzhen.baselib.utils.LogUtil;

/**
 * Created by lllidan on 2017/11/9.
 */

public class BaseNfcActivity extends FragmentActivity{
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNfcConfig();
    }

    private void initNfcConfig() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    //检测工作,判断设备的NFC支持情况
    public Boolean isNFCEnable() {
        if (mNfcAdapter == null) {
            //提示一次
            LogUtil.i("该设备不支持NFC功能");
            return false;
        }
        if (mNfcAdapter != null && !mNfcAdapter.isEnabled()) {
//            if (!nfcTipFlag){
//                nfcTips();
//            }
            return false;
        }
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()){
            LogUtil.i("NFC功能正常使用");
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null != mNfcAdapter){
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(null != mNfcAdapter){
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }
}
