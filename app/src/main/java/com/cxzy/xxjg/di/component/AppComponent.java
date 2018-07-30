package com.cxzy.xxjg.di.component;


import android.content.Context;

import com.cxzy.xxjg.di.module.AppModule;
import com.cxzy.xxjg.di.module.HttpModule;
import com.cxzy.xxjg.net.LoginApi;
import com.cxzy.xxjg.net.MainFragmentApi;
import com.cxzy.xxjg.net.TrialManagementApi;
import com.cxzy.xxjg.net.testApi;

import dagger.Component;

/**
 * Created by demo on 2018/6/25.
 */
@Component(modules = {AppModule.class,HttpModule.class})
public interface AppComponent {
    Context getContext();  // 提供App的Context
//    MyApp getApplication();
    testApi getNetTestApi();
    LoginApi getNetLoginApi();
    MainFragmentApi getUserInfoApi();
    TrialManagementApi getTrialApi();
}

