package com.cxzy.xxjg.app;

import android.app.Application;
import android.os.Environment;

import com.blankj.utilcode.utils.Utils;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerAppComponent;
import com.cxzy.xxjg.di.module.AppModule;
import com.cxzy.xxjg.di.module.HttpModule;
import com.cxzy.xxjg.utils.ContextUtils;

import java.io.File;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by demo on 2018/6/25.
 */

public class MyApp extends Application {
    private static MyApp instance;
    public static AppComponent appComponent;
    public static int width = 0;

    public static int height = 0;

    /**
     * 根目录
     */
    public static String rootDir;
    /**
     * 缓存目录
     */
    public static String cacheDir;
    /**
     * 临时文件目录
     */
    public static String tempDir;
    /**
     * 下载的图片目录
     */
    public static String imgDir;

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
        createSDCardDir();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }

    /**
     * 创建本地保存数据文件夹
     */
    public static void createSDCardDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            rootDir = sdcardDir.getPath() + File.separator + "xxjg";
            File file = new File(rootDir);
            if (!file.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                file.mkdirs();
            }
            cacheDir = rootDir + File.separator + "Cache";
            file = new File(cacheDir);
            if (!file.exists()) {
                // 创建缓存目录
                file.mkdirs();
            }
            tempDir = rootDir + File.separator + "Temp";
            file = new File(tempDir);
            if (!file.exists()) {
                // 创建下载目录
                file.mkdirs();
            }
            imgDir = rootDir + File.separator + "img";
            file = new File(imgDir);
            if (!file.exists()) {
                // 创建下载目录
                file.mkdirs();
            }
        } else {

            return;
        }
    }
}
