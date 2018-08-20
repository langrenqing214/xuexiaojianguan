package com.cxzy.xxjg.ui.test.contract;

import android.content.Intent;
import android.widget.ImageView;

import com.cxzy.xxjg.base.BaseContract;
import com.cxzy.xxjg.ui.test.BasePresenter;
import com.cxzy.xxjg.ui.test.BaseView;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * 添加试吃
 * Author: demo
 * Created on 2018/8/1
 */
public interface IAddTrialContract {

    interface View extends BaseContract.BaseView {
        void getPicFile(File file);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void saveTrial(Map<String , RequestBody> param);
        File getPicUrl(int requestCode, int resultCode, Intent data , ImageView ivPic);
    }

    interface Model {
    }
}