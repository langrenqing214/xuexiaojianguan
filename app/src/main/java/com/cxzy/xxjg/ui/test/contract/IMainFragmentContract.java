package com.cxzy.xxjg.ui.test.contract;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

/**
 * Author: demo
 * Created on 2018/7/16
 */
public interface IMainFragmentContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        String getZxingResult(int requestCode, int resultCode, Intent data);
    }

}