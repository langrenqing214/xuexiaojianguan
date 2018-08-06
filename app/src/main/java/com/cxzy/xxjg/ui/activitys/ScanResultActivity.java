package com.cxzy.xxjg.ui.activitys;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.base.ScanResultBean;
import com.cxzy.xxjg.bean.ResultItemBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.adapter.ScanResultAdapter;
import com.cxzy.xxjg.ui.test.contract.IScanResultContract;
import com.cxzy.xxjg.ui.test.presenter.ScanResultPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 扫描结果处理
 */
public class ScanResultActivity extends BaseActivity<ScanResultPresenterImpl> implements IScanResultContract.View {
    @BindView(R.id.lv_scan_result)
    ListView lvScanResutl ;

    private List<ResultItemBean> beanList = new ArrayList<>();
    private ScanResultAdapter mAdapter;
    private String url;
    private String barCode;
    private ScanResultBean bean;
    private int type = 0;//1:采购，2:留样，3:存放

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
        url = getIntent().getStringExtra("url");
        barCode = getIntent().getStringExtra("barCode");
        type = getIntent().getIntExtra("type" , 0);
        mPresenter.getScanResult(url , barCode);
        mAdapter = new ScanResultAdapter(this , beanList);
        lvScanResutl.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        if (mData != null) {
            bean = (ScanResultBean) mData;
            beanList.clear();
            beanList.addAll(bean.viewList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id , R.id.btn_scan_result})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
            case R.id.btn_scan_result ://扫描处理
                Map<String , Object> param = new HashMap<>();
                for (ResultItemBean info : bean.hiddenList) {
                    param.put(info.key , info.value);
                }
                if (type == 1){
                    param.put("outNum" , "1");
                    param.put("treasuryOutStaff" , "小龙");
                    mPresenter.dealOutStock(param);
                }else if (type == 2){
                    param.put("dealPerson" , "小龙");
                    mPresenter.dealSave(param);
                }else if (type == 3){
                    param.put("takeoutPerson" , "小龙");
                    mPresenter.dealSavedSave(param);
                }
                break;
        }
    }
}
