package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;

/**
 * Author: demo
 * Created on 2018/7/30
 */
public interface ITrialManagementContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTrialList(int pageNumber , String canteenId , String eatTimeStart, String eatTimeEnd, int pageSize);
    }

    interface Model {
    }
}