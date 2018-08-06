package com.cxzy.xxjg.ui.test.contract;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.PersonsBean;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 卫生检查
 * Author: demo
 * Created on 2018/8/2
 */
public interface IHealthExaminationContract {

    interface View extends BaseContract.BaseView {
        void refreshPicAdapter();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHealthCheck(String canteenId);
        void saveMorningCheck(Map<String , Object> param);
        void saveEnvCheck(Map<String , Object> param);
        List<String> dealPicResult(int requestCode, int resultCode, Intent data);
    }

    interface Model {
    }
}