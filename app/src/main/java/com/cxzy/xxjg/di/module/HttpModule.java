package com.cxzy.xxjg.di.module;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.net.ApiConstants;
import com.cxzy.xxjg.net.LoginApi;
import com.cxzy.xxjg.net.LoginService;
import com.cxzy.xxjg.http.RetrofitConfig;
import com.cxzy.xxjg.net.MainFragmentApi;
import com.cxzy.xxjg.net.MainFragmentService;
import com.cxzy.xxjg.net.TrialManagementApi;
import com.cxzy.xxjg.net.TrialManagementService;
import com.cxzy.xxjg.net.testApi;
import com.cxzy.xxjg.net.testService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by demo on 2018/6/27.
 */
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClient() {
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApp.appComponent.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        return new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
//                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);
    }

    /*@Provides
    Retrofit.Builder provideBuilder(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);
    }*/

    @Provides
    testApi provideNetTests(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return testApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(testService.class));
    }

    @Provides
    LoginApi provideNetLogin(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return LoginApi.getIntance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(LoginService.class));
    }

    @Provides
    MainFragmentApi provideNetUserInfo(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return MainFragmentApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(MainFragmentService.class));
    }

    @Provides
    TrialManagementApi provideNetTrialList(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return TrialManagementApi.getIntance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(TrialManagementService.class));
    }
}
