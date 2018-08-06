package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.adapter.RegulatoryInformationAdapter;

import butterknife.BindView;

/**
 * 监管信息
 */
public class RegulatoryInformationActivity extends BaseActivity {

    @BindView(R.id.rv_regulatory_information)
    RecyclerView mRecyclerView ;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RegulatoryInformationAdapter mAdapter = new RegulatoryInformationAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

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
