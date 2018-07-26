package com.cxzy.xxjg.ui.test.presenter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.bean.LoginBean;
import com.cxzy.xxjg.net.LoginApi;
import com.cxzy.xxjg.http.RxSchedulers;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;
import com.cxzy.xxjg.utils.T;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Author: demo
 * Created on 2018/7/23
 */
public class LoginActivityPresenterImpl extends BasePresenter<ILoginActivityContract.View> implements ILoginActivityContract.Presenter {
    private LoginApi api;

    @Inject
    public LoginActivityPresenterImpl(LoginApi api) {
        this.api = api;
    }

    @Override
    public void toLogin(String userName, String passWord, Activity activity) {
        if (TextUtils.isEmpty(userName)) {
            T.showShort(activity, "请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(passWord)) {
            T.showShort(activity, "请输入密码");
            return;
        }

        Map<String , String> params = new HashMap<>();
        params.put("loginName" , userName);
        params.put("password" , passWord);

        api.login(params)
                .compose(RxSchedulers.<LoginBean>applySchedulers())
                .compose(mView.<LoginBean>bindToLife())
                .subscribe(new Observer<LoginBean>() {

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showFaild();
                        Log.e("haha" , "error == " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (loginBean != null && loginBean.ok && loginBean.data != null) {
                            SharedPreferencesUtils.setParam(MyApp.appComponent.getContext() , "app_token" , loginBean.data.access_token);
                            mView.loginResult(loginBean);
                        }else {
                            T.showShort(MyApp.appComponent.getContext() , loginBean.message);
                        }
                    }

                });
    }
}