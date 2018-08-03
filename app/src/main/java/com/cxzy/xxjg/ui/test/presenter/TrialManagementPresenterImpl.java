package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.AddTrialApi;
import com.cxzy.xxjg.net.TrialManagementApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.ITrialManagementContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/30
 */
public class TrialManagementPresenterImpl extends BasePresenter<ITrialManagementContract.View> implements ITrialManagementContract.Presenter {

    private TrialManagementApi api ;
    private AddTrialApi trialApi ;

    @Inject
    TrialManagementPresenterImpl(TrialManagementApi api , AddTrialApi trialApi){
        this.api = api ;
        this.trialApi = trialApi ;
    }

    @Override
    public void getTrialList(int pageNumber , String canteenId , String eatTimeStart, String eatTimeEnd, int pageSize) {
        invoke(api.getTrialList(pageNumber , canteenId , eatTimeStart , eatTimeEnd , pageSize));
    }

    //跟进
    @Override
    public void dealTrialItem(Map<String, Object> param) {
        invoke(trialApi.saveTrial(param));
    }
}