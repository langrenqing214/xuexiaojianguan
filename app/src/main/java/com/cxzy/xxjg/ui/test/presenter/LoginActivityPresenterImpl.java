package com.cxzy.xxjg.ui.test.presenter;

import android.text.TextUtils;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.bean.LoginBean;
import com.cxzy.xxjg.net.LoginApi;
import com.cxzy.xxjg.http.RxSchedulers;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;
import com.cxzy.xxjg.utils.ToastUtil;

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
    public void toLogin(String userName, String passWord) {
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.showShort(MyApp.appComponent.getContext(), "请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(passWord)) {
            ToastUtil.showShort(MyApp.appComponent.getContext(), "请输入密码");
            return;
        }

        Map<String , String> params = new HashMap<>();
        params.put("loginName" , userName);
        params.put("password" , passWord);

        api.login(params)
                .compose(RxSchedulers.<BaseBean<LoginBean>>applySchedulers())
                .compose(mView.<BaseBean<LoginBean>>bindToLife())
                .subscribe(new Observer<BaseBean<LoginBean>>() {

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtil.showShort(MyApp.appComponent.getContext() , e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> bean) {
                        if (bean != null && bean.ok && bean.data != null) {
                            SharedPreferencesUtils.setParam(MyApp.appComponent.getContext() , "app_token" , bean.data.access_token);
                            mView.loginResult(bean.data);
                        }else {
                            ToastUtil.showShort(MyApp.appComponent.getContext() , bean.message);
                        }
                    }

                });
    }
}