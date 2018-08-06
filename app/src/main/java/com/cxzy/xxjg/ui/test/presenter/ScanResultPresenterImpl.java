package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.ScanResultApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IScanResultContract;
import com.cxzy.xxjg.ui.test.model.ScanResultModelImpl;

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
}