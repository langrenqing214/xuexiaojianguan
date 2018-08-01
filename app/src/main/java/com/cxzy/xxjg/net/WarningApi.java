package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.WarningBean;

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
    public Observable<BaseBean<WarningBean>> getWarningList(String canteenId ,String createDateStart ,String createDateEnd ,int pageNumber ,int pageSize){
        return mService.getWarningList(canteenId , createDateStart , createDateEnd , pageNumber , pageSize);
    }
}
