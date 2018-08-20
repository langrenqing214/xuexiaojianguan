package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.TrialBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by demo on 2018/7/30.
 */

public interface TrialManagementService {
    @GET("api/foodEat/list")
    Observable<BaseBean<TrialBean>> getTrialList(@Query("pageNum") int pageNum ,
                                                 @Query("canteenId") String canteenId ,
                                                 @Query("eatTimeStart") String eatTimeStart,
                                                 @Query("eatTimeEnd") String eatTimeEnd,
                                                 @Query("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("api/foodEat/save")
    Observable<BaseBean<Object>> saveTrial(@FieldMap Map<String , Object> param);
}
