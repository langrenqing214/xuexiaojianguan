package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/8/7.
 */

public class ChangePwdApi {
    private ChangePwdService mService ;
    private static ChangePwdApi instance ;

    private ChangePwdApi(ChangePwdService mService){
        this.mService = mService ;
    }

    public static synchronized ChangePwdApi getInstance(ChangePwdService mService){
        if (instance == null){
            instance = new ChangePwdApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<Object>> changePwd(Map<String  , Object> param){
        return mService.changePwd(param);
    }
}
