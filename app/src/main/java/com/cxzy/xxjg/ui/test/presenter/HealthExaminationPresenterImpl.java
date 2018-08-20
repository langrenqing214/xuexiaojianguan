package com.cxzy.xxjg.ui.test.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.bean.PersonsBean;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.net.HealthExaminationApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.activitys.HealthExaminationActivity;
import com.cxzy.xxjg.ui.test.contract.IHealthExaminationContract;
import com.cxzy.xxjg.ui.test.model.HealthExaminationModelImpl;
import com.cxzy.xxjg.utils.BitmapUtil;
import com.cxzy.xxjg.utils.ScreenUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Author: demo
 * Created on 2018/8/2
 */
public class HealthExaminationPresenterImpl extends BasePresenter<IHealthExaminationContract.View> implements IHealthExaminationContract.Presenter {
    private HealthExaminationApi api;
    private List<String> pictureList = new ArrayList<String>();//图片地址集合
    private List<String> originalPicList = new ArrayList<String>();//图片原始地址
    private String picName;//图片名字

    @Inject
    HealthExaminationPresenterImpl(HealthExaminationApi api) {
        this.api = api;
    }

    @Override
    public void getHealthCheck(String canteenId) {
        invoke(api.getHealthCheck(canteenId));
    }

    @Override
    public void saveMorningCheck(Map<String, RequestBody> param) {
        invoke(api.saveMorningCheck(param));
    }

    @Override
    public void saveEnvCheck(Map<String, Object> param) {
        invoke(api.saveEnvCheck(param));
    }

    @Override
    public List<String> dealPicResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.FLAG_CHOOSE_IMG:// 直接从相册获取
                if (data != null) {
                    String camera = data.getStringExtra("camera");
                    if (!TextUtils.isEmpty(camera)) {
                        //照相返回
                        pictureList.add(camera);
                        originalPicList.add(camera);
                        mView.refreshPicAdapter();
                        return new ArrayList<>();
                    }

                    int code = data.getIntExtra("code", -1);
                    if (code != 100) {
                        return new ArrayList<>();
                    }

                    final ArrayList<String> paths = data.getStringArrayListExtra("paths");
                    //添加，去重
//                        boolean hasUpdate = false;
                    new Thread() {
                        @Override
                        public void run() {
                            for (String path : paths) {
                                if (!originalPicList.contains(path)) {
                                    try {
                                        //压缩图片
                                        picName = System.currentTimeMillis() + ".jpg";
                                        String newPath = MyApp.tempDir + File.separator + picName;
                                        Bitmap bm = BitmapUtil.getBitmapByPath(path, BitmapUtil.getOptions(path), ScreenUtils.getScreenW(), ScreenUtils.getScreenH());

                                        BitmapUtil.saveMyBitmap(bm, newPath, path, ScreenUtils.getScreenW(), ScreenUtils.getScreenW());

                                        BitmapUtil.setExif(path, newPath);
                                        pictureList.add(MyApp.tempDir + File.separator + picName);

//                                            adapter.notifyDataSetChanged();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    //原始图片路径 保存这个是为了去掉重复图片
                                    originalPicList.add(path);
//                                        hasUpdate = true;
                                }
                            }
                            Message message = handler.obtainMessage();
                            message.what = 1;
                            message.sendToTarget();

                        }
                    }.start();


                }

                break;
            case Constants.FLAG_CHOOSE_CAMERA:// 通过相机拍照获取
//                    picNameList.add(picName);
                    // File.separator是分隔符 "/"
                    String string = MyApp.tempDir + File.separator + picName;// 定义图片路径
                    try {
                        Bitmap bm = BitmapUtil.getBitmapByPath(string, BitmapUtil.getOptions(string), ScreenUtils.getScreenW(), ScreenUtils.getScreenH());
                        BitmapUtil.saveMyBitmap(bm, string, string, ScreenUtils.getScreenW(), ScreenUtils.getScreenH());

                        pictureList.add(string);
                        mView.refreshPicAdapter();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                break;

        }
        return pictureList;
    }

    /**
     * 图片压缩后更新适配器
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mView.refreshPicAdapter();
        }
    };
}