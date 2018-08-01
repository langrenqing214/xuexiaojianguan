package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public interface IWarningContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getWarningList(String canteenId ,String createDateStart ,String createDateEnd ,int pageNumber ,int pageSize);
    }

    interface Model {
    }
}