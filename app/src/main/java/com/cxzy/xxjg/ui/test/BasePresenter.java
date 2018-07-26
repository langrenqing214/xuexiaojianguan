package com.cxzy.xxjg.ui.test;

import com.cxzy.xxjg.base.BaseContract;


/**
 * Author: demo
 * Created on 2018/6/26
 */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}