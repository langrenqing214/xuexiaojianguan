package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.AddRetentionApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IAddRetentionContract;
import com.cxzy.xxjg.ui.test.model.AddRetentionModelImpl;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/31
 */
public class AddRetentionPresenterImpl extends BasePresenter<IAddRetentionContract.View> implements IAddRetentionContract.Presenter {

    private AddRetentionApi api ;

    @Inject
    public AddRetentionPresenterImpl(AddRetentionApi api){
        this.api = api ;
    }

    @Override
    public void saveRetention(Map<String, Object> param) {
        invoke(api.saveRetention(param));
    }
}