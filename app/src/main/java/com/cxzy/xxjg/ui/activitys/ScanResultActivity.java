package com.cxzy.xxjg.ui.activitys;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.base.ScanResultBean;
import com.cxzy.xxjg.bean.ResultItemBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.ScanResultDialog;
import com.cxzy.xxjg.ui.adapter.ScanResultAdapter;
import com.cxzy.xxjg.ui.test.contract.IScanResultContract;
import com.cxzy.xxjg.ui.test.presenter.ScanResultPresenterImpl;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 扫描结果处理
 */
public class ScanResultActivity extends BaseActivity<ScanResultPresenterImpl> implements IScanResultContract.View, ScanResultDialog.ScanResultListener {
    @BindView(R.id.lv_scan_result)
    ListView lvScanResutl;
    @BindView(R.id.main_title_id)
    TextView tvTitle;
    @BindView(R.id.btn_scan_result)
    Button btnScanResult;

    private String personName = "";
    private String num = "";
    private String outNum = "";

    private List<ResultItemBean> beanList = new ArrayList<>();
    private ScanResultAdapter mAdapter;
    private String url;
    private String barCode;
    private ScanResultBean bean;
    private int type = 0;//1:采购，2:留样，3:存放
    private int putType = 0 ;//0 进页面接口访问 1 不为进页面接口访问

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
        type = getIntent().getIntExtra("type", 0);

        if (type == 1) {//采购
            tvTitle.setText("采购");
            btnScanResult.setText("出库");
        } else if (type == 2) {//留样
            tvTitle.setText("留样");
            btnScanResult.setText("销样");
        } else if (type == 3) {//存放
            tvTitle.setText("存放");
            btnScanResult.setText("取出");
        }

        mPresenter.getScanResult(url, barCode);
        mAdapter = new ScanResultAdapter(this, beanList);
        lvScanResutl.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        try {
            if (mData != null) {
                bean = (ScanResultBean) mData;
                beanList.clear();
                beanList.addAll(bean.viewList);
                for (ResultItemBean info : beanList) {
                    if ("balance".equals(info.key)) {
                        outNum = info.value;
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
        }
        if (putType == 1) {
            ToastUtil.showShort(this, "提交成功");
            finish();
        }
    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.btn_scan_result})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.btn_scan_result://扫描处理
                putType = 1 ;
                Map<String, Object> param = new HashMap<>();
                for (ResultItemBean info : bean.hiddenList) {
                    param.put(info.key, info.value);
                }
                if (type == 1) {
                    if (TextUtils.isEmpty(num) || TextUtils.isEmpty(personName) || (Integer.valueOf(num) > Integer.valueOf(TextUtils.isEmpty(outNum) ? "0" : outNum))) {
                        ScanResultDialog dialog = new ScanResultDialog(this, 0, this);
                        dialog.show();
                        return;
                    }
                    param.put("outNum", num);
                    param.put("treasuryOutStaff", personName);
                    mPresenter.dealOutStock(param);
                } else if (type == 2) {
                    if (TextUtils.isEmpty(personName)) {
                        ScanResultDialog dialog = new ScanResultDialog(this, 1, this);
                        dialog.show();
                        return;
                    }
                    param.put("dealPerson", personName);
                    mPresenter.dealSave(param);
                } else if (type == 3) {
                    if (TextUtils.isEmpty(personName)) {
                        ScanResultDialog dialog = new ScanResultDialog(this, 2, this);
                        dialog.show();
                        return;
                    }
                    param.put("takeoutPerson", personName);
                    mPresenter.dealSavedSave(param);
                }
                break;
        }
    }

    @Override
    public void scanResult(String person, String num) {
        this.personName = person;
        this.num = num;
        if (type == 1) {
            if (TextUtils.isEmpty(num)) {
                ToastUtil.showShort(this, "请输入出库数量");
                return;
            }
            if (Integer.valueOf(num) > Integer.valueOf(TextUtils.isEmpty(outNum) ? "0" : outNum)) {
                ToastUtil.showShort(this, "输入出库数量大于库存量");
                return;
            }
        }

        if (TextUtils.isEmpty(person)) {
            ToastUtil.showShort(this, "请输入处理人");
            return;
        }

    }
}
