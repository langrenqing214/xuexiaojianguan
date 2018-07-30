package com.cxzy.xxjg.ui.test;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.http.util.CallBack;
import com.cxzy.xxjg.http.util.HttpUtils;

import io.reactivex.Observable;


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

    protected <T> void invoke(Observable<BaseBean<T>> observable) {
        HttpUtils.invoke( mView, observable);
    }
}