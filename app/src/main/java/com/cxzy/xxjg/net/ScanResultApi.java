package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/8/6.
 */

public class ScanResultApi {
    private ScanResultService mService ;
    private static ScanResultApi instance ;
    private ScanResultApi(ScanResultService mService){
        this.mService = mService ;
    }

    public static synchronized ScanResultApi getInstance(ScanResultService mService){
        if (instance == null){
            instance = new ScanResultApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<Object>> getScanResult(String url){
        return mService.getScanResult(url);
    }
}
