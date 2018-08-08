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

    private String localVersion;

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
    }

    @Override
    public void initData() {
        tvVsersion.setText("v" + localVersion);
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

    @OnClick
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
        }
    }
}
