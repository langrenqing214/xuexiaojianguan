package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;
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
import com.google.zxing.integration.android.IntentIntegrator;

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
            new IntentIntegrator(this)
                    .setOrientationLocked(false)
                    .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是ScanActivity
                    .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                    .setPrompt("请对准二维码")// 设置提示语
                    .setCameraId(0)// 选择摄像头,可使用前置或者后置
                    .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                    .setBarcodeImageEnabled(false)// 扫完码之后生成二维码的图片
                    .initiateScan();// 初始化扫码
//            finish();
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = mPresenter.getZxingResult(requestCode, resultCode, data , this);
        String url = "" ;
        if (!TextUtils.isEmpty(result)) {
            String str = result.substring(0, 1);
            switch (str) {
                case "1"://采购
                    url = "api/scan/purchase";
                    Intent intent1 = new Intent(mContext , ScanResultActivity.class);
                    intent1.putExtra("url" , url);
                    intent1.putExtra("type" , 1);
                    intent1.putExtra("barCode" , result);
                    startActivity(intent1);
                    break;
                case "2"://留样
                    url = "api/scan/reserved";
                    Intent intent2 = new Intent(mContext , ScanResultActivity.class);
                    intent2.putExtra("url" , url);
                    intent2.putExtra("type" , 2);
                    intent2.putExtra("barCode" , result);
                    startActivity(intent2);
                    break;
                case "3"://存放
                    url = "api/scan/saved";
                    Intent intent3 = new Intent(mContext , ScanResultActivity.class);
                    intent3.putExtra("url" , url);
                    intent3.putExtra("type" , 3);
                    intent3.putExtra("barCode" , result);
                    startActivity(intent3);
                    break;
                default:
                    ToastUtil.showShort(mContext, "扫描条码有误");
                    break;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
