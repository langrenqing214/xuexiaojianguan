package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.TrialBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by demo on 2018/7/30.
 */

public interface TrialManagementService {
    @GET("api/foodEat/list")
    Observable<BaseBean<TrialBean>> getTrialList(@Query("pageNumber") int pageNumber ,
                                                 @Query("canteenId") String canteenId ,
                                                 @Query("eatTimeStart") String eatTimeStart,
                                                 @Query("eatTimeEnd") String eatTimeEnd,
                                                 @Query("pageSize") int pageSize);
}
