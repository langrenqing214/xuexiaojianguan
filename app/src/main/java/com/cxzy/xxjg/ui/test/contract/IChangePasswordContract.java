package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

import java.util.Map;

/**
 * Author: demo
 * Created on 2018/8/7
 */
public interface IChangePasswordContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void changePwd(Map<String  , Object> param);
    }

    interface Model {
    }
}