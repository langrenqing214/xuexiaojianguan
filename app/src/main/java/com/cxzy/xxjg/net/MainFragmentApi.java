package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.MainFragmentBean;

import io.reactivex.Observable;

import static com.cxzy.xxjg.net.testApi.sInstance;

/**
 * 首页
 * Created by demo on 2018/7/27.
 */

public class MainFragmentApi {
    private MainFragmentService mService ;
    public static MainFragmentApi sInstance ;

    public MainFragmentApi(MainFragmentService mService) {
        this.mService = mService ;
    }

    public static synchronized MainFragmentApi getInstance(MainFragmentService mService){
        if (sInstance == null)
            sInstance = new MainFragmentApi(mService);
        return sInstance;
    }

    public Observable<BaseBean<MainFragmentBean>> getUserInfo(){
        return mService.getUserInfo();
    }
}
