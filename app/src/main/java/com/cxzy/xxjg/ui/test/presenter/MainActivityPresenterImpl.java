package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IMainActivityContract;
import com.cxzy.xxjg.utils.T;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/16
 */
public class MainActivityPresenterImpl extends BasePresenter<IMainActivityContract.View> implements IMainActivityContract.Presenter {

    @Inject
    MainActivityPresenterImpl(){}

    private Long firstTime = 0L;

    @Override
    public void isBackApp() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 1500) {
            T.showShort(MyApp.appComponent.getContext(), "再按一次退出");
            firstTime = secondTime;
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}