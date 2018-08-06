package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 扫描结果
 * Created by demo on 2018/8/6.
 */

public interface ScanResultService {
    @GET
    Observable<BaseBean<Object>> getScanResult(@Url String url);
}
