package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.LoginBean;


import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import io.reactivex.Observable;

/**
 * 登录
 * Created by demo on 2018/7/26.
 */

public interface LoginService {
    @POST("auth/token")
    Observable<BaseBean<LoginBean>> login(@Body Map<String , String> params) ;
}
