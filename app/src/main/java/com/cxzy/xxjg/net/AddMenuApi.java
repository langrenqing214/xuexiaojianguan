package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 *
 *  添加菜单
 * Created by demo on 2018/7/31.
 */

public class AddMenuApi {
    private static AddMenuApi instance ;
    private AddMenuService mService ;

    private AddMenuApi(AddMenuService mService){
        this.mService = mService ;
    }

    public static synchronized AddMenuApi getInstance(AddMenuService mService){
        if (instance == null){
            instance = new AddMenuApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<Object>> saveMenu(Map<String , Object> param){
        return mService.saveMenu(param);
    }
}
