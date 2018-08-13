package com.cxzy.xxjg.ui.test.contract;

import android.app.Activity;
import android.content.Intent;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

import java.util.Map;

/**
 * 扫描结果
 * Author: demo
 * Created on 2018/8/6
 */
public interface IScanResultContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getScanResult(String url , String barCode);
        void dealOutStock(Map<String , Object> param);//出库
        void dealSave(Map<String , Object> param);//销样
        void dealSavedSave(Map<String , Object> param);//取出
        String getZxingResult(int requestCode, int resultCode, Intent data , Activity activity);
    }

    interface Model {

    }
}