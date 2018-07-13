package com.cxzy.xxjg.di.module;

import android.content.Context;

import com.cxzy.xxjg.app.MyApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by demo on 2018/6/25.
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    MyApp provideApplication() {
        return (MyApp) mContext.getApplicationContext();
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
