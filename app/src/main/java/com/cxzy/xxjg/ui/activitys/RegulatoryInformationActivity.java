package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.adapter.RegulatoryInformationAdapter;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;

import butterknife.BindView;

/**
 * 监管信息
 */
public class RegulatoryInformationActivity extends BaseActivity {

    @BindView(R.id.wv_regulatory)
    WebView mWebView;

    @Override
    public int getContentLayout() {
        return R.layout.activity_regulatory_information;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
    }

    @Override
    public void initData() {
        String url = "http://47.95.252.122:8080/wisdom/#/app/notice" + "?Authorization=Bearer%" + SharedPreferencesUtils.getParam(MyApp.appComponent.getContext() , "app_token" , "");
//        String url = "https://www.baidu.com/";
// 设置WebView属性，能够执行Javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    public void refreshView(Object mData) {

    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }
}
