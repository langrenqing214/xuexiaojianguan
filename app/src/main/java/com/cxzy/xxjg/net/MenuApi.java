package com.cxzy.xxjg.net;

import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.MenuBean;

import io.reactivex.Observable;

/**
 * Created by demo on 2018/7/31.
 */

public class MenuApi {
    private static MenuApi instance ;
    private MenuService mService ;

    private MenuApi(MenuService mService){
        this.mService = mService ;
    }

    public static synchronized MenuApi getInstance(MenuService mService){
        if (instance == null){
            instance = new MenuApi(mService);
        }
        return instance ;
    }

    public Observable<BaseBean<MenuBean>> getMenuList(String canteenId ,int pageNumber ,int pageSize){
        return mService.getMenuList(canteenId , pageNumber , pageSize);
    }
}
