package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;

/**
 * Author: demo
 * Created on 2018/7/31
 */
public interface IMenuActivityContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getMenuList(int canteenId ,int pageNumber ,int pageSize);
    }

    interface Model {

    }
}