package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

/**
 * Author: demo
 * Created on 2018/7/16
 */
public interface IMainActivityContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void isBackApp();
    }
}