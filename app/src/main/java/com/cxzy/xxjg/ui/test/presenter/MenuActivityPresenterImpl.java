package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.MenuApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IMenuActivityContract;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/31
 */
public class MenuActivityPresenterImpl extends BasePresenter<IMenuActivityContract.View> implements IMenuActivityContract.Presenter {
    private MenuApi api ;

    @Inject
    public MenuActivityPresenterImpl(MenuApi api){
        this.api = api ;
    }

    @Override
    public void getMenuList(String canteenId ,int pageNumber ,int pageSize) {
        invoke(api.getMenuList(canteenId , pageNumber , pageSize));
    }
}