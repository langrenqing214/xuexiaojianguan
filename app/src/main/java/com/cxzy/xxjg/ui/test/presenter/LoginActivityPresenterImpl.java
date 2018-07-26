package com.cxzy.xxjg.ui.test.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract.Presenter;
import com.cxzy.xxjg.ui.test.model.LoginActivityModelImpl;
import com.cxzy.xxjg.utils.T;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/23
 */
public class LoginActivityPresenterImpl extends BasePresenter<ILoginActivityContract.View> implements ILoginActivityContract.Presenter {

    @Inject
    LoginActivityPresenterImpl(){

    }

    @Override
    public void toLogin(String userName, String passWord , Activity activity) {
        if (TextUtils.isEmpty(userName)){
            T.showShort(activity , "请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(passWord)){
            T.showShort(activity , "请输入密码");
            return;
        }
        activity.startActivity(new Intent(activity ,MainActivity.class));
        activity.finish();
    }
}