package com.cxzy.xxjg.ui.test.contract;

import android.app.Activity;
import android.content.Context;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.LoginBean;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

/**
 * 登录
 * Author: demo
 * Created on 2018/7/23
 */
public interface ILoginActivityContract {

    interface View extends BaseContract.BaseView {
        void loginResult(LoginBean loginBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void toLogin(String userName , String passWord , Activity activity);
    }

    interface Model {

    }
}