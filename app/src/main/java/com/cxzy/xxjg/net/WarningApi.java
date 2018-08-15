package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.WarningBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/8/1.
 */

public class WarningApi {
    private WarningService mService ;
    private static WarningApi instance ;
    private WarningApi(WarningService mService){
        this.mService = mService ;
    }
    public static synchronized WarningApi getInstance(WarningService mService){
        if (instance == null){
            instance = new WarningApi(mService);
        }
        return instance ;
    }
    public Observable<BaseBean<WarningBean>> getWarningList(String level , String canteenId ,String createDateStart ,String createDateEnd ,int pageNum ,int pageSize){
        return mService.getWarningList(level , canteenId , createDateStart , createDateEnd , pageNum , pageSize);
    }

    public Observable<BaseBean<Object>> dealWarning(Map<String , Object> param){
        return mService.dealWarning(param);
    }
}
