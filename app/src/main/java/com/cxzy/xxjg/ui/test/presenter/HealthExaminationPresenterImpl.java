package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.bean.PersonsBean;
import com.cxzy.xxjg.net.HealthExaminationApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.activitys.HealthExaminationActivity;
import com.cxzy.xxjg.ui.test.contract.IHealthExaminationContract;
import com.cxzy.xxjg.ui.test.model.HealthExaminationModelImpl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/8/2
 */
public class HealthExaminationPresenterImpl extends BasePresenter<IHealthExaminationContract.View> implements IHealthExaminationContract.Presenter {
    private HealthExaminationApi api ;
    @Inject
    HealthExaminationPresenterImpl(HealthExaminationApi api){
        this.api = api ;
    }

    @Override
    public void getHealthCheck(String canteenId) {
        invoke(api.getHealthCheck(canteenId));
    }

    @Override
    public void saveMorningCheck(Map<String , Object> param) {
        invoke(api.saveMorningCheck(param));
    }

    @Override
    public void saveEnvCheck(String canteenId, String state) {
        invoke(api.saveEnvCheck(canteenId , state));
    }
}