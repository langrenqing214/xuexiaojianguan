package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.PurchaseBean;

import java.util.List;
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

    //获取供应商列表
    public Observable<BaseBean<List<PurchaseBean>>> getSupplierList(){
        return mService.getSupplierList();
    }

    public Observable<BaseBean<Object>> savePurchase(Map<String , Object> param){
        return mService.savePurchase(param);
    }
}
