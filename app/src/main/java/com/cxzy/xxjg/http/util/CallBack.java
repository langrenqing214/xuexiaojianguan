package com.cxzy.xxjg.http.util;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.bean.BaseBean;

import rx.Subscriber;

/**
 * Created by demo on 2018/7/30.
 */

public class CallBack<T>  {

    private Stateful target;

    public void setTarget(Stateful target) {
        this.target = target;
    }



    /*@Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        BaseBean bean = (BaseBean) t;
        if (bean.ok){
            ((BaseContract.BaseView) target).refreshView(bean.data);
        }
    }*/
}
