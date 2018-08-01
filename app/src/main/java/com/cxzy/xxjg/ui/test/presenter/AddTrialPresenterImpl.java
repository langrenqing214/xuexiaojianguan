package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.AddTrialApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IAddTrialContract;
import com.cxzy.xxjg.ui.test.model.AddTrialModelImpl;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public class AddTrialPresenterImpl extends BasePresenter<IAddTrialContract.View> implements IAddTrialContract.Presenter {

    private AddTrialApi api ;

    @Inject
    AddTrialPresenterImpl(AddTrialApi api){
        this.api = api ;
    }

    @Override
    public void saveTrial(Map<String, Object> param) {
        invoke(api.saveTrial(param));
    }
}