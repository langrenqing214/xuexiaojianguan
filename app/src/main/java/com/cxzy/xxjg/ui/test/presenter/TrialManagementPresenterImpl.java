package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.TrialManagementApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.ITrialManagementContract;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/30
 */
public class TrialManagementPresenterImpl extends BasePresenter<ITrialManagementContract.View> implements ITrialManagementContract.Presenter {

    private TrialManagementApi api ;

    @Inject
    TrialManagementPresenterImpl(TrialManagementApi api){
        this.api = api ;
    }

    @Override
    public void getTrialList(int pageNumber , String canteenId , String eatTimeStart, String eatTimeEnd, int pageSize) {
        invoke(api.getTrialList(pageNumber , canteenId , eatTimeStart , eatTimeEnd , pageSize));
    }
}