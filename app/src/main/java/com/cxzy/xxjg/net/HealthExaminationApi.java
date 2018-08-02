package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.HealthExaminationBean;
import com.cxzy.xxjg.bean.PersonsBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/8/2.
 */

public class HealthExaminationApi {
    private HealthExaminationService mService ;
    private static HealthExaminationApi instance ;
    private HealthExaminationApi(HealthExaminationService mService){
        this.mService = mService ;
    }

    public static synchronized HealthExaminationApi getInstance(HealthExaminationService mService){
        if (instance == null){
            instance = new HealthExaminationApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<HealthExaminationBean>> getHealthCheck(String canteenId){
        return mService.getHealthCheck(canteenId);
    }

    public Observable<BaseBean<Object>> saveMorningCheck(List<PersonsBean> persons , List<File> files){
        return mService.saveMorningCheck(persons , files);
    }

    public Observable<BaseBean<Object>> saveEnvCheck( String canteenId ,String state){
        return mService.saveEnvCheck(canteenId , state);
    }
}