package com.cxzy.xxjg.net;

import com.cxzy.xxjg.base.ScanResultBean;
import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 扫描结果
 * Created by demo on 2018/8/6.
 */

public interface ScanResultService {
    @GET
    Observable<BaseBean<ScanResultBean>> getScanResult(@Url String url , @Query("barCode") String barCode);

    //出库 1
    @POST("api/foodPurchase/outStock")
    Observable<BaseBean<Object>> dealOutStock(@Body Map<String , Object> param);

    //销样 2
    @POST("api/foodReserved/save")
    Observable<BaseBean<Object>> dealSave(@Body Map<String , Object> param);

    //取出 3
    @POST("api/saved/save")
    Observable<BaseBean<Object>> dealSavedSave(@Body Map<String , Object> param);



}
