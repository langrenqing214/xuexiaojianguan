package com.cxzy.xxjg.ui.test.contract;

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
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHealthCheck(String canteenId);
        void saveMorningCheck(List<PersonsBean> persons , List<File> files);
        void saveEnvCheck( String canteenId ,String state);
    }

    interface Model {
    }
}