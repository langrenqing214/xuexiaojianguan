package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.http.ApiConstants;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户协议
 */
public class UserProtocolActivity extends BaseActivity {

    @BindView(R.id.wv_infomation)
    WebView  mWebView;

    @Override
    public int getContentLayout() {
        return R.layout.activity_user_protocol;
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
        String url = ApiConstants.getInstance().getsIFengApi() + "index.html#/app/notice" + "?Authorization=Bearer " + SharedPreferencesUtils.getParam(MyApp.appComponent.getContext(), "app_token", "");
        // 设置WebView属性，能够执行Javascript脚本
        mWebView.getSettings().setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
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
    public void refreshFaild(String faildCode) {
        if ("401".equals(faildCode)){
            finish();
        }
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
        }
    }
}
