package com.cxzy.xxjg.ui.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;

import butterknife.OnClick;

/**
 * 历史警告
 */
public class ListoricalWarningActivity extends BaseActivity {

    @Override
    public int getContentLayout() {
        return R.layout.activity_listorical_warning;
    }

    @Override
    public void initInjector(AppComponent appComponent) {
        /*DaggerHttpComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);*/
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext , R.color.main_style_color));
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.ll_canteen_select , R.id.ll_time_select})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.ll_canteen_select ://食堂选择
                break;
            case R.id.ll_time_select ://时间选择
                break;
        }
    }
}
