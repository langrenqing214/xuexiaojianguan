package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.VideoApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IVideoContract;
import com.cxzy.xxjg.ui.test.model.VideoModelImpl;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/8/7
 */
public class VideoPresenterImpl extends BasePresenter<IVideoContract.View> implements IVideoContract.Presenter {
    private VideoApi api ;

    @Inject
    VideoPresenterImpl(VideoApi api){
        this.api = api ;
    }

    @Override
    public void getVideoList(String canteenId) {
        invoke(api.getVideoList(canteenId ));
    }
}