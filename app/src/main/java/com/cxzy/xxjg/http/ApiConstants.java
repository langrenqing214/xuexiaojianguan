package com.cxzy.xxjg.http;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;

/**
 * Created by demo on 2018/6/27.
 */

public class ApiConstants {
//    public static final String sIFengApi = "http://47.95.252.122:8080/wisdom/";
    public static final String sIFengApi =  SharedPreferencesUtils.getParam(MyApp.appComponent.getContext() , "main_url" , "http://47.95.252.122:8080") + "/wisdom/";
//    public static final String sIFengApi = "http://xmh.s1.natapp.cc/wisdom/";
//    public static final String sIFengApi = "http://192.168.8.133:8080/wisdom/";
}
