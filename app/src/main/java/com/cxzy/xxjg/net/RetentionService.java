package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.RetentionBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 留样管理
 * Created by demo on 2018/8/1.
 */

public interface RetentionService {
    @GET("api/foodReserved/list")
    Observable<BaseBean<RetentionBean>> getRetentionList(@Query("canteenId") String canteenId ,
                                                         @Query("reservedTimeStart") String eatTimeStart ,
                                                         @Query("reservedTimeEnd") String eatTimeEnd ,
                                                         @Query("pageNum") int pageNum ,
                                                         @Query("pageSize") int pageSize);

    //处理
    @POST("api/foodReserved/save")
    Observable<BaseBean<RetentionBean>> saveRetention(@Body Map<String , Object> param);
}
