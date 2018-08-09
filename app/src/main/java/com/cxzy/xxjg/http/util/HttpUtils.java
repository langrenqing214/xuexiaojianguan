package com.cxzy.xxjg.http.util;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.http.RxSchedulers;
import com.cxzy.xxjg.utils.DialogHelper;
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
                            mView.refreshFaild();
                        }
                        if (mDialog != null){
                            mDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.refreshFaild();
                        if (mDialog != null){
                            mDialog.dismiss();
                        }
                        ToastUtil.showShort(MyApp.appComponent.getContext() , e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
