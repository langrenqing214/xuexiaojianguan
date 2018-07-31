package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.LoginBean;

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
        void toLogin(String userName , String passWord);
    }

    interface Model {

    }
}