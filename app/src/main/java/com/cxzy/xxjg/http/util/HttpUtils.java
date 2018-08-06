package com.cxzy.xxjg.http.util;

import android.widget.Toast;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.BaseBean;
import com.cxzy.xxjg.http.RxSchedulers;
import com.cxzy.xxjg.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by demo on 2018/7/30.
 */

public class HttpUtils {
    public static <T> void invoke(final BaseContract.BaseView mView, Observable<BaseBean<T>> observable) {
        observable.compose(RxSchedulers.<BaseBean<T>>applySchedulers())
                .compose(mView.<BaseBean<T>>bindToLife())
                .subscribe(new Observer<BaseBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<T> t) {
                        if (t != null && t.ok){
                            mView.refreshView(t.data);
                        }else {
                            ToastUtil.showShort(MyApp.appComponent.getContext() , t.message);
                            mView.refreshFaild();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.refreshFaild();
                        ToastUtil.showShort(MyApp.appComponent.getContext() , e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
