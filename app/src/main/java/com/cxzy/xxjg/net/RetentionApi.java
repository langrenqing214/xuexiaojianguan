package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.RetentionBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/8/1.
 */

public class RetentionApi {
    private RetentionService mService;
    private static RetentionApi instance;

    private RetentionApi(RetentionService mService) {
        this.mService = mService;
    }

    public static synchronized RetentionApi getInstance(RetentionService mService) {
        if (instance == null) {
            instance = new RetentionApi(mService);
        }
        return instance;
    }

    public Observable<BaseBean<RetentionBean>> getRetentionList(String canteenId, String eatTimeStart , String eatTimeEnd , int pageNumber, int pageSize){
        return mService.getRetentionList(canteenId , eatTimeStart , eatTimeEnd , pageNumber , pageSize);
    }

    //处理
    public Observable<BaseBean<RetentionBean>> saveRetention(Map<String , Object> param){
        return mService.saveRetention(param);
    }
}
