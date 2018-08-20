package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by demo on 2018/8/1.
 */

public class AddTrialApi {
    private AddTrialService mService ;
    private static AddTrialApi instance ;
    private AddTrialApi(AddTrialService mService){
        this.mService = mService ;
    }
    public static synchronized AddTrialApi getInstance(AddTrialService mService){
        if (instance == null){
            instance = new AddTrialApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<Object>> saveTrial(Map<String , RequestBody> param ){
        return mService.saveTrial(param);
    }

}
