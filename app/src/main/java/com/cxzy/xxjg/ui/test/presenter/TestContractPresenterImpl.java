package com.cxzy.xxjg.ui.test.presenter;

import android.content.Intent;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.bean.TestBean;
import com.cxzy.xxjg.net.RxSchedulers;
import com.cxzy.xxjg.net.testApi;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.ITestContractContract;
import com.cxzy.xxjg.utils.T;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author: demo
 * Created on 2018/6/26
 */
public class TestContractPresenterImpl extends BasePresenter<ITestContractContract.View> implements ITestContractContract.Presenter {

    private testApi api ;

    @Inject
    TestContractPresenterImpl(testApi api){
        this.api = api ;
    }

    @Override
    public void getTestDetails() {
        api.getTestDetail()
                .compose(RxSchedulers.<TestBean>applySchedulers())
                .compose(mView.<TestBean>bindToLife())
                .subscribe(new Observer<TestBean>() {

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showFaild();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TestBean testBean) {
                        mView.loadTestDetails(testBean);
                    }

                });
    }
}