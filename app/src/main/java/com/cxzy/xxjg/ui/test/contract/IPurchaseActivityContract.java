package com.cxzy.xxjg.ui.test.contract;

import android.app.Activity;
import android.content.Intent;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

import java.util.List;

/**
 * 食材采购
 * Author: demo
 * Created on 2018/7/19
 */
public interface IPurchaseActivityContract {

    interface View extends BaseContract.BaseView {
        void refreshPicAdapter();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
       void getReadStoragePermission();
       List<String> dealPicResult(Activity activity , int requestCode, int resultCode, Intent data);
    }
}