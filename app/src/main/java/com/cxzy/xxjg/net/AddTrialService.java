package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * 添加试吃
 * Created by demo on 2018/8/1.
 */

public interface AddTrialService {
    @Multipart
    @POST("api/foodEat/save")
    Observable<BaseBean<Object>> saveTrial(@PartMap Map<String , RequestBody> param );
}
