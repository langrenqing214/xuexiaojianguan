package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.PurchaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by demo on 2018/7/31.
 */

public interface PurchaseService {
    //获取供应商列表
    @POST("api/foodPurchase/supplier")
    Observable<BaseBean<List<PurchaseBean>>> getSupplierList();
    //入库 出库
    @Multipart
    @POST("api/foodPurchase/save")
    Observable<BaseBean<Object>> savePurchase(@PartMap Map<String , RequestBody> param);
}
