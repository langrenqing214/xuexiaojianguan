package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.MainFragmentBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by demo on 2018/7/27.
 */

public interface MainFragmentService {
//    @Headers({"Content-Type: application/json","Accept: application/json" , "Authrization:Bearer eyJhbGcziOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MjEzNzQ3MDAyOH0._TBR8FhNYw4wMkAMtE8r1OMoiyoxKx1Z1_vXKukccoIt6VP6kKGz-Ic990rJAVbwVYfnZYvwIVKY_EOm5tmE7w"})//需要添加头
    @POST("api/user/info")
    Observable<BaseBean<MainFragmentBean>> getUserInfo();
}
