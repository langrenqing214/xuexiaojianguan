package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.adapter.RetentionManageAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 留样管理
 */
public class RetentionManageActivity extends BaseActivity {

    @BindView(R.id.rv_retention)
    RecyclerView rvRetention ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_retention_manage;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        rvRetention.setLayoutManager(new LinearLayoutManager(this));
        RetentionManageAdapter mAdapter = new RetentionManageAdapter();
        rvRetention.setAdapter(mAdapter);
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

    @OnClick({R.id.back_btn_id , R.id.ll_add_retention})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
            case R.id.ll_add_retention ://添加留样
                startActivity(new Intent(mContext , AddRetentionActivity.class));
                break;
        }
    }

}
