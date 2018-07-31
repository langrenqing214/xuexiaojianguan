package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/7/31.
 */

public class PurchaseApi {
    private static PurchaseApi instance ;
    private PurchaseService mService ;
    private PurchaseApi(PurchaseService mService){
        this.mService = mService ;
    }

    public static synchronized PurchaseApi getInstance(PurchaseService mService){
        if (instance == null){
            instance = new PurchaseApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<Object>> savePurchase(Map<String , Object> param){
        return mService.savePurchase(param);
    }
}
