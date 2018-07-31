package com.cxzy.xxjg.ui.test.contract;

import android.content.Intent;

import com.cxzy.xxjg.base.BaseContract;

/**
 * Author: demo
 * Created on 2018/7/16
 */
public interface IMainFragmentContract {

    interface View extends BaseContract.BaseView {
//        void getUserInfo(Object o);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        String getZxingResult(int requestCode, int resultCode, Intent data);
        void getUserInfo();
    }

}