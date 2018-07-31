package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by demo on 2018/7/31.
 */

public interface PurchaseService {
    @POST("api/foodPurchase/save")
    Observable<BaseBean<Object>> savePurchase(@Body Map<String , Object> param);
}
