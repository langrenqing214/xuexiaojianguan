package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;

import java.util.Map;

/**
 * Author: demo
 * Created on 2018/7/31
 */
public interface IAddMenuContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
       void saveMenu(Map<String , Object> param);
    }

    interface Model {
    }
}