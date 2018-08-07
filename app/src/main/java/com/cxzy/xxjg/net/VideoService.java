package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.VideoBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 视频
 * Created by demo on 2018/8/7.
 */

public interface VideoService {
    @GET("api/video/list")
    Observable<BaseBean<List<VideoBean>>> getVideoList(@Query("canteenId") String canteenId );
}
