package com.cxzy.xxjg.ui.test.presenter;

import android.content.Intent;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.net.MainFragmentApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IMainFragmentContract;
import com.cxzy.xxjg.utils.ToastUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/16
 */
public class MainFragmentContractPresenterImpl extends BasePresenter<IMainFragmentContract.View> implements IMainFragmentContract.Presenter  {

    private MainFragmentApi api ;

    @Inject
    public MainFragmentContractPresenterImpl(MainFragmentApi api){
        this.api = api ;
    }

    @Override
    public String getZxingResult(int requestCode, int resultCode, Intent data) {
        String scanResult = "";
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                ToastUtil.showShort(MyApp.appComponent.getContext() , "内容为空");
            } else {
//                T.showShort(MyApp.appComponent.getContext() , "扫描成功");
                // ScanResult 为 获取到的字符串
                scanResult = intentResult.getContents();
            }
        }
        return scanResult;
    }

    @Override
    public void getUserInfo() {
        invoke(api.getUserInfo());
    }
}