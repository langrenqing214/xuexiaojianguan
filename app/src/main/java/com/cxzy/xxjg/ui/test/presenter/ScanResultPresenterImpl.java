package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.ScanResultApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IScanResultContract;
import com.cxzy.xxjg.ui.test.model.ScanResultModelImpl;

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
    public void getScanResult(String url) {
        invoke(api.getScanResult(url));
    }
}