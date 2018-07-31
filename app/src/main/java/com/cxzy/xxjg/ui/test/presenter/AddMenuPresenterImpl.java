package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.AddMenuApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IAddMenuContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/31
 */
public class AddMenuPresenterImpl extends BasePresenter<IAddMenuContract.View> implements IAddMenuContract.Presenter {
    private AddMenuApi api ;

    @Inject
    public AddMenuPresenterImpl(AddMenuApi api){
        this.api = api ;
    }

    @Override
    public void saveMenu(Map<String , Object> param) {
        invoke(api.saveMenu(param));
    }
}