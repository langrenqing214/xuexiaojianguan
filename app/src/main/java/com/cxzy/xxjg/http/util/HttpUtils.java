package com.cxzy.xxjg.http.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.http.RxSchedulers;
import com.cxzy.xxjg.ui.activitys.LoginActivity;
import com.cxzy.xxjg.utils.ConstantsUtil;
import com.cxzy.xxjg.utils.DialogHelper;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;
import com.cxzy.xxjg.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by demo on 2018/7/30.
 */

public class HttpUtils{

    private static Context mContext;
    private static Dialog mDialog;

    public static <T> void invoke(final BaseContract.BaseView mView, Observable<BaseBean<T>> observable) {
        observable.compose(RxSchedulers.<BaseBean<T>>applySchedulers())
                .compose(mView.<BaseBean<T>>bindToLife())
                .subscribe(new Observer<BaseBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mContext = mView.getDialogContext();
                        mDialog = DialogHelper.getLoadingDialog(mContext);
                        mDialog.show();
                    }

                    @Override
                    public void onNext(BaseBean<T> t) {
                        if (t != null && t.ok){
                            mView.refreshView(t.data);
                        }else {
                            ToastUtil.showShort(MyApp.appComponent.getContext() , t.message);
                            mView.refreshFaild("");
                        }
                        if (mDialog != null){
                            mDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().contains("401") || e.getMessage().contains("java.net.SocketTimeoutException")){
                            ToastUtil.showShort(MyApp.appComponent.getContext() , "登录过期，请重新登录");
                            mContext.startActivity(new Intent(mContext , LoginActivity.class));
                            Intent intent = new Intent();
                            intent.setAction(ConstantsUtil.AUTH_LOGIN_STATUS_SUCCESS);
                            mContext.sendBroadcast(intent);
                            SharedPreferencesUtils.setParam(MyApp.appComponent.getContext() , "app_token" , "");
                            SharedPreferencesUtils.setParam(MyApp.appComponent.getContext() , "main_url" , "");
                        }
                        mView.refreshFaild("401");
                        if (mDialog != null){
                            mDialog.dismiss();
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
