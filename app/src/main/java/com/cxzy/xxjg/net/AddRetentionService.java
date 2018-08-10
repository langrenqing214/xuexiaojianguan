package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.RetentionBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 添加留样
 * Created by demo on 2018/7/31.
 */

public interface AddRetentionService {
    @POST("api/foodReserved/save")
    Observable<BaseBean<RetentionBean>> saveRetention(@Body Map<String , Object> param);
}
