package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.TestBean;

/**
 * Author: demo
 * Created on 2018/6/26
 */
public interface ITestContractContract {

    interface View extends BaseContract.BaseView {
        void loadTestDetails(TestBean testBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTestDetails();
    }
}