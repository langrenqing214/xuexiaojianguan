package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.HealthExaminationBean;
import com.cxzy.xxjg.bean.PersonsBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 卫生检查
 * Created by demo on 2018/8/2.
 */

public interface HealthExaminationService {

    @GET("api/check/getCheck")
    Observable<BaseBean<HealthExaminationBean>> getHealthCheck(@Query("canteenId") String canteenId);

    //提交晨检
    @Multipart
    @POST("api/check/saveMorningCheck")
    Observable<BaseBean<Object>> saveMorningCheck(@PartMap Map<String , RequestBody> param);

    //提交环境检查
    @POST("api/check/saveEnvCheck")
    Observable<BaseBean<Object>> saveEnvCheck(@Body Map<String , Object> param);

}
