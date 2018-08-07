package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.VideoBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/8/7.
 */

public class VideoApi {
    private VideoService mService ;
    private static VideoApi instance ;

    private VideoApi(VideoService mService){
        this.mService = mService ;
    }

    public static synchronized VideoApi getInstance(VideoService mService){
        if (instance == null){
            instance = new VideoApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<List<VideoBean>>> getVideoList(String canteenId){
        return mService.getVideoList(canteenId );
    }
}
