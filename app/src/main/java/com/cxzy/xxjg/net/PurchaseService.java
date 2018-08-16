package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.PurchaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by demo on 2018/7/31.
 */

public interface PurchaseService {
    //获取供应商列表
    @POST("api/foodPurchase/supplier")
    Observable<BaseBean<List<PurchaseBean>>> getSupplierList();
    //入库 出库
    @FormUrlEncoded
    @POST("api/foodPurchase/save")
    Observable<BaseBean<Object>> savePurchase(@FieldMap Map<String , Object> param);
}
