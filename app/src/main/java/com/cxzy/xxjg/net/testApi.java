package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.TestBean;
import io.reactivex.Observable;

/**
 * Created by demo on 2018/6/26.
 */

public class testApi {

    private testService mService;
    public static testApi sInstance;

    private testApi(testService service) {
        this.mService = service;
    }

    public static testApi getInstance(testService service) {
        if (sInstance == null)
            sInstance = new testApi(service);
        return sInstance;
    }

    public Observable<TestBean> getTestDetail() {
        return mService.getTestDetail(1);
    }
}
