package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.AddRetentionApi;
import com.cxzy.xxjg.net.RetentionApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IRetentionContract;
import com.cxzy.xxjg.ui.test.model.RetentionModelImpl;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public class RetentionPresenterImpl extends BasePresenter<IRetentionContract.View> implements IRetentionContract.Presenter {

    private RetentionApi api ;

    @Inject
    RetentionPresenterImpl(RetentionApi api){
        this.api = api ;
    }

    //请求列表
    @Override
    public void getRetentionList(String canteenId, String eatTimeStart , String eatTimeEnd , int pageNumber, int pageSize) {
        invoke(api.getRetentionList(canteenId , eatTimeStart , eatTimeEnd , pageNumber , pageSize));
    }

    //处理
    @Override
    public void dealRetention(Map<String, Object> param) {
        invoke(api.saveRetention(param));
    }

}