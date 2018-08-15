package com.cxzy.xxjg.net;


import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.TrialBean;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/7/30.
 */

public class TrialManagementApi {
    private TrialManagementService mService ;
    private static TrialManagementApi intance ;

    private TrialManagementApi(TrialManagementService mService){
        this.mService = mService ;
    }

    public static synchronized TrialManagementApi getIntance(TrialManagementService mService){
        if (intance == null){
            intance = new TrialManagementApi(mService);
        }
        return intance ;
    }

    public Observable<BaseBean<TrialBean>> getTrialList(int pageNum , String canteenId , String eatTimeStart, String eatTimeEnd, int pageSize){
        return mService.getTrialList(pageNum , canteenId , eatTimeStart , eatTimeEnd , pageSize);
    }

}
