package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;

import java.util.Map;

/**
 * 添加留样
 * Author: demo
 * Created on 2018/7/31
 */
public interface IAddRetentionContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void saveRetention(Map<String , Object> param);
    }

    interface Model {
    }
}