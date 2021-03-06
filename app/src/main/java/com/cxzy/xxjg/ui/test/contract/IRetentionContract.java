package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;

import java.util.Map;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public interface IRetentionContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getRetentionList(String canteenId, String eatTimeStart, String eatTimeEnd, int pageNum, int pageSize);
        void dealRetention(Map<String, Object> param);
    }

    interface Model {

    }
}