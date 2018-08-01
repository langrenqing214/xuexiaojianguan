package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.WarningApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IWarningContract;
import com.cxzy.xxjg.ui.test.model.WarningModelImpl;

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
    public void getWarningList(String canteenId, String createDateStart, String createDateEnd, int pageNumber, int pageSize) {
        invoke(api.getWarningList(canteenId , createDateStart , createDateEnd , pageNumber , pageSize));
    }
}