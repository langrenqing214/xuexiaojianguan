package com.cxzy.xxjg.ui.activitys;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.base.ScanResultBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.adapter.ScanResultAdapter;
import com.cxzy.xxjg.ui.test.contract.IScanResultContract;
import com.cxzy.xxjg.ui.test.presenter.ScanResultPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 扫描结果处理
 */
public class ScanResultActivity extends BaseActivity<ScanResultPresenterImpl> implements IScanResultContract.View {
    @BindView(R.id.lv_scan_result)
    ListView lvScanResutl ;

    private List<ScanResultBean> beanList = new ArrayList<>();
    private ScanResultAdapter mAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_scan_result;
    }

    @Override
    public void initInjector(AppComponent appComponent) {
        DaggerHttpComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        mAdapter = new ScanResultAdapter(this , beanList);
        lvScanResutl.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }
}
