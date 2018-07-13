package com.cxzy.xxjg.app;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerAppComponent;
import com.cxzy.xxjg.di.module.AppModule;
import com.cxzy.xxjg.di.module.HttpModule;
import com.cxzy.xxjg.utils.ContextUtils;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by demo on 2018/6/25.
 */

public class MyApp extends Application {
    private static MyApp instance;
    public static AppComponent appComponent;
    public static int width = 0;

    public static int height = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);//一个utils库的初始化 https://github.com/Blankj/AndroidUtilCode/blob/master/README-CN.md
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
        width = ContextUtils.getSreenWidth(MyApp.appComponent.getContext());
        height = ContextUtils.getSreenHeight(MyApp.appComponent.getContext());
        //初始化滑动返回
        BGASwipeBackManager.getInstance().init(this);
    }

    public static MyApp getInstance() {
        return instance;
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
