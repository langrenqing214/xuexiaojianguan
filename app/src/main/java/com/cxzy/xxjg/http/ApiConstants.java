package com.cxzy.xxjg.http;

import android.text.TextUtils;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;

/**
 * Created by demo on 2018/6/27.
 */

public class ApiConstants {
    private static ApiConstants instance ;
//    public String url = (String) SharedPreferencesUtils.getParam(MyApp.appComponent.getContext() , "main_url" , "http://45.95.252.122:8080/wisdom/");
//    public static final String sIFengApi = "http://47.95.252.122:8080/wisdom/";
//    public String sIFengApi = TextUtils.isEmpty(url) ? "http://43.95.252.122:8080/wisdom/" : url;
//    public static final String sIFengApi = "http://xmh.s1.natapp.cc/wisdom/";
//    public static final String sIFengApi = "http://192.168.8.133:8080/wisdom/";

    public static ApiConstants getInstance(){
        if (instance == null){
            instance = new ApiConstants();
        }
        return  instance;
    }

    public String getsIFengApi(){
        String url = (String) SharedPreferencesUtils.getParam(MyApp.appComponent.getContext() , "main_url" , "http://47.95.252.122:8080/wisdom/");
        String sIFengApi = TextUtils.isEmpty(url) ? "http://47.95.252.122:8080/wisdom/" : url;
        return sIFengApi ;
    }
}
