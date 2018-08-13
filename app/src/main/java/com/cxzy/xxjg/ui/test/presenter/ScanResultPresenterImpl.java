package com.cxzy.xxjg.ui.test.presenter;

import android.app.Activity;
import android.content.Intent;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.net.ScanResultApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IScanResultContract;
import com.cxzy.xxjg.ui.test.model.ScanResultModelImpl;
import com.cxzy.xxjg.utils.ToastUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/8/6
 */
public class ScanResultPresenterImpl extends BasePresenter<IScanResultContract.View> implements IScanResultContract.Presenter {
    private ScanResultApi api ;

    @Inject
    ScanResultPresenterImpl (ScanResultApi api){
        this.api = api ;
    }

    @Override
    public void getScanResult(String url , String barCode) {
        invoke(api.getScanResult(url , barCode));
    }

    @Override
    public void dealOutStock(Map<String, Object> param) {
        invoke(api.dealOutStock(param));
    }

    @Override
    public void dealSave(Map<String, Object> param) {
        invoke(api.dealSave(param));
    }

    @Override
    public void dealSavedSave(Map<String, Object> param) {
        invoke(api.dealSavedSave(param));
    }

    @Override
    public String getZxingResult(int requestCode, int resultCode, Intent data , Activity activity) {
        String scanResult = "";
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                activity.finish();
//                ToastUtil.showShort(MyApp.appComponent.getContext() , "内容为空");
            } else {
//                T.showShort(MyApp.appComponent.getContext() , "扫描成功");
                // ScanResult 为 获取到的字符串
                scanResult = intentResult.getContents();
            }
        }
        return scanResult;
    }
}