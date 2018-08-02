package com.cxzy.xxjg.di.module;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.net.AddMenuApi;
import com.cxzy.xxjg.net.AddMenuService;
import com.cxzy.xxjg.net.AddRetentionApi;
import com.cxzy.xxjg.net.AddRetentionService;
import com.cxzy.xxjg.net.AddTrialApi;
import com.cxzy.xxjg.net.AddTrialService;
import com.cxzy.xxjg.net.ApiConstants;
import com.cxzy.xxjg.net.HealthExaminationApi;
import com.cxzy.xxjg.net.HealthExaminationService;
import com.cxzy.xxjg.net.LoginApi;
import com.cxzy.xxjg.net.LoginService;
import com.cxzy.xxjg.http.RetrofitConfig;
import com.cxzy.xxjg.net.MainFragmentApi;
import com.cxzy.xxjg.net.MainFragmentService;
import com.cxzy.xxjg.net.MenuApi;
import com.cxzy.xxjg.net.MenuService;
import com.cxzy.xxjg.net.PurchaseApi;
import com.cxzy.xxjg.net.PurchaseService;
import com.cxzy.xxjg.net.RetentionApi;
import com.cxzy.xxjg.net.RetentionService;
import com.cxzy.xxjg.net.TrialManagementApi;
import com.cxzy.xxjg.net.TrialManagementService;
import com.cxzy.xxjg.net.WarningApi;
import com.cxzy.xxjg.net.WarningService;
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

    //登录
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

    //主页
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

    //试吃管理
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

    //菜谱
    @Provides
    MenuApi provideNetMenuList(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return MenuApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(MenuService.class));
    }

    //添加菜谱
    @Provides
    AddMenuApi provideNetAddMenuList(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return AddMenuApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(AddMenuService.class));
    }

    //食材采购
    @Provides
    PurchaseApi provideNetPurchase(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return PurchaseApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(PurchaseService.class));
    }

    //添加留样
    @Provides
    AddRetentionApi provideNetAddRetention(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return AddRetentionApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(AddRetentionService.class));
    }

    //留样管理
    @Provides
    RetentionApi provideNetRetention(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return RetentionApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(RetentionService.class));
    }

    //历史告警
    @Provides
    WarningApi provideNetWarningList(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return WarningApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(WarningService.class));
    }

    //添加试吃
    @Provides
    AddTrialApi provideNetAddTrial(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return AddTrialApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(AddTrialService.class));
    }

    //卫生检查
    @Provides
    HealthExaminationApi provideNetHealthExamination(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return HealthExaminationApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(HealthExaminationService.class));
    }
}
