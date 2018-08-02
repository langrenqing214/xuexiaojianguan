package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.HealthExaminationBean;
import com.cxzy.xxjg.bean.PersonsBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 卫生检查
 * Created by demo on 2018/8/2.
 */

public interface HealthExaminationService {

    @GET("api/check/getCheck")
    Observable<BaseBean<HealthExaminationBean>> getHealthCheck(@Query("canteenId") String canteenId);

    //提交晨检
    @FormUrlEncoded
    @GET("api/check/saveMorningCheck")
    Observable<BaseBean<Object>> saveMorningCheck(@Field("persons")List<PersonsBean> persons ,
                                                  @Field("files")List<File> files);

    //提交环境检查
    @GET("api/check/saveEnvCheck")
    Observable<BaseBean<Object>> saveEnvCheck(@Query("canteenId") String canteenId ,
                                              @Query("state") String state);

}