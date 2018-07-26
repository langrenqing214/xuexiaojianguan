package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.adapter.TrialManagementAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 试吃管理
 */
public class TrialManagementActivity extends BaseActivity {

    @BindView(R.id.rv_trial_management)
    RecyclerView rvTrial ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_trial_management;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        rvTrial.setLayoutManager(new LinearLayoutManager(this));
        TrialManagementAdapter mAdapter = new TrialManagementAdapter();
        rvTrial.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id , R.id.ll_add_trial})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
            case R.id.ll_add_retention ://添加试吃
                break;
        }
    }
}
