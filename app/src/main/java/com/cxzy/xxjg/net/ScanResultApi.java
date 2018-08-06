package com.cxzy.xxjg.net;

import com.cxzy.xxjg.base.ScanResultBean;
import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

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

    public Observable<BaseBean<ScanResultBean>> getScanResult(String url , String barCode){
        return mService.getScanResult(url , barCode);
    }

    public Observable<BaseBean<Object>> dealOutStock(Map<String , Object> param){
        return mService.dealOutStock(param);
    }

    public Observable<BaseBean<Object>> dealSave(Map<String , Object> param){
        return mService.dealSave(param);
    }

    public Observable<BaseBean<Object>> dealSavedSave(Map<String , Object> param){
        return mService.dealSavedSave(param);
    }
}
