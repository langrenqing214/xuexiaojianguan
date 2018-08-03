package com.cxzy.xxjg.ui.test.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.net.AddTrialApi;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IAddTrialContract;
import com.cxzy.xxjg.ui.test.model.AddTrialModelImpl;
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
 * Created on 2018/8/1
 */
public class AddTrialPresenterImpl extends BasePresenter<IAddTrialContract.View> implements IAddTrialContract.Presenter {

    private AddTrialApi api ;
    private String picName;//图片名字
    private List<String> pictureList = new ArrayList<String>();//图片地址集合
    private List<String> originalPicList = new ArrayList<String>();//图片原始地址
    private String picUrl = "";
    private Bitmap newBitmap = null;
    private ImageView ivPic ;


    @Inject
    AddTrialPresenterImpl(AddTrialApi api){
        this.api = api ;
    }

    @Override
    public void saveTrial(Map<String, Object> param) {
        invoke(api.saveTrial(param));
    }

    @Override
    public File getPicUrl(int requestCode, int resultCode, Intent data , ImageView ivPic) {
        this.ivPic = ivPic ;
        switch (requestCode) {
            case Constants.FLAG_CHOOSE_IMG:// 直接从相册获取
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        String camera = data.getStringExtra("camera");
                        if (!TextUtils.isEmpty(camera)) {
                            //照相返回
                            picUrl = camera ;
                            try {
                                newBitmap = BitmapUtil.getBitmapByPath(picUrl, BitmapUtil.getOptions(picUrl) , MyApp.width , MyApp.height);
                                ivPic.setImageBitmap(newBitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            return new File(camera);
                        }

                        int code = data.getIntExtra("code", -1);
                        if (code != 100) {
                            picUrl = camera ;
                            try {
                                newBitmap = BitmapUtil.getBitmapByPath(picUrl, BitmapUtil.getOptions(picUrl) , MyApp.width , MyApp.height);
                                ivPic.setImageBitmap(newBitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            return new File(camera);
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
                                            picUrl = MyApp.tempDir + File.separator + picName;

                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                Message message = handler.obtainMessage();
                                message.what = 1;
                                message.sendToTarget();

                            }
                        }.start();


                    }
                }
                break;
            case Constants.FLAG_CHOOSE_CAMERA:// 通过相机拍照获取
                if (resultCode == Activity.RESULT_OK) {
//                    picNameList.add(picName);
                    // File.separator是分隔符 "/"
                    picUrl = MyApp.tempDir + File.separator + picName;// 定义图片路径
                    try {
                        Bitmap bm = BitmapUtil.getBitmapByPath(picUrl, BitmapUtil.getOptions(picUrl), ScreenUtils.getScreenW(), ScreenUtils.getScreenH());
                        BitmapUtil.saveMyBitmap(bm, picUrl, picUrl, ScreenUtils.getScreenW(), ScreenUtils.getScreenH());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

                break;

        }
        return new File(picUrl) ;
    }

    /**
     * 图片压缩后更新适配器
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                newBitmap = BitmapUtil.getBitmapByPath(picUrl, BitmapUtil.getOptions(picUrl) , MyApp.width , MyApp.height);
                ivPic.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };
}