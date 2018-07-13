package com.cxzy.xxjg.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    public int getContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageView(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA, getStateView());
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.iv_back :
                finish();
                break;
        }
    }
}
