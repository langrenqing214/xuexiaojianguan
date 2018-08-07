package com.cxzy.xxjg.ui.test.contract;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

/**
 * 视频
 * Author: demo
 * Created on 2018/8/7
 */
public interface IVideoContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getVideoList( String canteenId);
    }

    interface Model {
    }
}