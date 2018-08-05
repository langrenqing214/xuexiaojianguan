package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.RetentionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 留样管理
 * Created by demo on 2018/8/1.
 */

public interface RetentionService {
    @GET("api/foodReserved/list")
    Observable<BaseBean<RetentionBean>> getRetentionList(@Query("canteenId") String canteenId ,
                                                         @Query("eatTimeStart") String eatTimeStart ,
                                                         @Query("eatTimeEnd") String eatTimeEnd ,
                                                         @Query("pageNumber") int pageNumber ,
                                                         @Query("pageSize") int pageSize);
}
