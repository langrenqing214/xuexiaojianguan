package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.TestBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by demo on 2018/6/26.
 */

public interface testService {
    @GET("m_version.x")
    Observable<TestBean> getTestDetail(@Query("type") int type);
}
