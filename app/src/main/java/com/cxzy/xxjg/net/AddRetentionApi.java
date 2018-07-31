package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/7/31.
 */

public class AddRetentionApi {
    private AddRetentionService mService ;
    private static AddRetentionApi instance ;
    private AddRetentionApi(AddRetentionService mService){
        this.mService = mService ;
    }

    public static synchronized AddRetentionApi getInstance(AddRetentionService mService){
        if (instance == null){
            instance = new AddRetentionApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<Object>> saveRetention(Map<String , Object> param){
        return mService.saveRetention(param);
    }

}
