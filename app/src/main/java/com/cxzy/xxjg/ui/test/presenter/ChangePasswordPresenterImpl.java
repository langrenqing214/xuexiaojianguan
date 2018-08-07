package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.ChangePwdApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IChangePasswordContract;
import com.cxzy.xxjg.ui.test.model.ChangePasswordModelImpl;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/8/7
 */
public class ChangePasswordPresenterImpl extends BasePresenter<IChangePasswordContract.View> implements IChangePasswordContract.Presenter {
    private ChangePwdApi api ;
    @Inject
    ChangePasswordPresenterImpl(ChangePwdApi api){
        this.api = api ;
    }

    @Override
    public void changePwd() {
        invoke(api.changePwd());
    }
}