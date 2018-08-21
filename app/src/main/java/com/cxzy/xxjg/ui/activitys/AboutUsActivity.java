package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于我们
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.vserion)
    TextView tvVsersion ;
    @BindView(R.id.tv_app_name)
    TextView tvAppName ;
    @BindView(R.id.tv_app_sub_name)
    TextView tvAppSubName ;

    private String localVersion;
    private String appName;
    private String appSubName;

    @Override
    public int getContentLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext , R.color.main_style_color));
        localVersion = getIntent().getStringExtra("localVersion");
        appName = getIntent().getStringExtra("appName");
        appSubName = getIntent().getStringExtra("appSubName");
    }

    @Override
    public void initData() {
        tvVsersion.setText("v" + localVersion);
        tvAppName.setText(appName);
        tvAppSubName.setText("(" + appSubName + ")");
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
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
        }
    }
}
