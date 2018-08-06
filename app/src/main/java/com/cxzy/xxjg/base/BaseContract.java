package com.cxzy.xxjg.base;

import com.cxzy.xxjg.utils.ToastUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by demo on 2018/6/26.
 */

public interface BaseContract {
    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        //显示进度中
        void showLoading();

        //显示请求成功
        void showSuccess();

        //失败重试
        void showFaild();

        //显示当前网络不可用
        void showNoNet();

        //获取数据成功调用该方法。
        void refreshView(Object mData);

        //获取数据失败
        void refreshFaild();

        //重试
        void onRetry();

        /**
         * 绑定生命周期
         *
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

    }
}
