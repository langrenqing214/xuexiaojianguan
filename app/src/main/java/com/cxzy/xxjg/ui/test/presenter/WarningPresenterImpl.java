package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.WarningApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IWarningContract;
import com.cxzy.xxjg.ui.test.model.WarningModelImpl;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public class WarningPresenterImpl extends BasePresenter<IWarningContract.View> implements IWarningContract.Presenter {

    private WarningApi api ;

    @Inject
    WarningPresenterImpl(WarningApi api){
        this.api = api ;
    }

    @Override
    public void getWarningList(String level , String canteenId, String createDateStart, String createDateEnd, int pageNum, int pageSize) {
        invoke(api.getWarningList(level , canteenId , createDateStart , createDateEnd , pageNum , pageSize));
    }

    @Override
    public void dealWarning(Map<String, Object> param) {
        invoke(api.dealWarning(param));
    }
}