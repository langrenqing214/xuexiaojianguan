package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

import java.util.Map;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public interface IWarningContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getWarningList(String level , String canteenId ,String createDateStart ,String createDateEnd ,int pageNum ,int pageSize);
        void dealWarning(Map<String , Object> param);
    }

    interface Model {
    }
}