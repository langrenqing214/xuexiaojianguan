package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.WarningBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 历史告警
 * Created by demo on 2018/8/1.
 */

public interface WarningService {
    @GET("api/alarm/list")
    Observable<BaseBean<WarningBean>> getWarningList(@Query("level") String level,
                                                     @Query("canteenId") String canteenId,
                                                     @Query("createDateStart") String createDateStart,
                                                     @Query("createDateEnd") String createDateEnd,
                                                     @Query("pageNum") int pageNumber,
                                                     @Query("pageSize") int pageSize);
    @POST("api/alarm/deal")
    Observable<BaseBean<Object>> dealWarning(@Body Map<String , Object> param);
}
