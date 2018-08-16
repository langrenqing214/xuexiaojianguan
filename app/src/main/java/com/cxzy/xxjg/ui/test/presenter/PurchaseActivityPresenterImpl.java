package com.cxzy.xxjg.ui.test.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.net.PurchaseApi;
import com.cxzy.xxjg.presenter.BasePresenter;
import com.cxzy.xxjg.ui.test.contract.IPurchaseActivityContract;
import com.cxzy.xxjg.utils.BitmapUtil;
import com.cxzy.xxjg.utils.ScreenUtils;
import com.cxzy.xxjg.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author: demo
 * Created on 2018/7/19
 */
public class PurchaseActivityPresenterImpl extends BasePresenter<IPurchaseActivityContract.View> implements IPurchaseActivityContract.Presenter {

    private List<String> pictureList = new ArrayList<String>(5);//图片地址集合
    private List<String> originalPicList = new ArrayList<String>(5);//图片原始地址
    private String picName;//图片名字
    private PurchaseApi api ;

    @Inject
    public PurchaseActivityPresenterImpl(PurchaseApi api){
        this.api = api ;
    }

    @Override
    public void getSupplierList() {
        invoke(api.getSupplierList());
    }

    @Override
    public List<String> dealPicResult(Activity activity , int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.FLAG_CHOOSE_IMG:// 直接从相册获取
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        String camera = data.getStringExtra("camera");
                        if (!TextUtils.isEmpty(camera)) {
                            //照相返回
                            pictureList.add(camera);
                            checkPic();
                            originalPicList.add(camera);
//                            mView.refreshPicAdapter();
                            Message message = handler.obtainMessage();
                            message.what = 1;
                            message.sendToTarget();
                            return pictureList;
                        }

                        int code = data.getIntExtra("code", -1);
                        if (code != 100) {
                            return pictureList;
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
                }
                break;
            case Constants.FLAG_CHOOSE_CAMERA:// 通过相机拍照获取
                if (resultCode == Activity.RESULT_OK) {
//                    picNameList.add(picName);
                    // File.separator是分隔符 "/"
                    String string = MyApp.tempDir + File.separator + picName;// 定义图片路径
                    try {
                        Bitmap bm = BitmapUtil.getBitmapByPath(string, BitmapUtil.getOptions(string), ScreenUtils.getScreenW(), ScreenUtils.getScreenH());
                        BitmapUtil.saveMyBitmap(bm, string, string, ScreenUtils.getScreenW(), ScreenUtils.getScreenH());

                        pictureList.add(string);
//                        mView.refreshPicAdapter();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

                break;

        }
        return pictureList ;
    }

    @Override
    public void savePurchase(Map<String, Object> param) {
        invoke(api.savePurchase(param));
    }

    @Override
    public Map<String, Object> checkInfo(String name, String type, String price, String weight, String purchasePerson,
                                         String qualityGuaranteeDate, String qualityGuaranteeEndDate,
                                         String suppliers, int flag, String canteenId, List<String> picList) {
        if (TextUtils.isEmpty(name)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请输入食材名称");
            return null;
        }

        if (TextUtils.isEmpty(type)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请选择食材类别");
            return null;
        }

        if (TextUtils.isEmpty(price)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请输入食材价格");
            return null ;
        }

        if (TextUtils.isEmpty(weight)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请输入食材重量");
            return null ;
        }

        if (TextUtils.isEmpty(purchasePerson)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请输入采购人");
            return null ;
        }

        if (TextUtils.isEmpty(qualityGuaranteeDate)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请选择生产日期");
            return null ;
        }

        if (TextUtils.isEmpty(qualityGuaranteeEndDate)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请选择保质到期");
            return null ;
        }

        if (TextUtils.isEmpty(suppliers)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请选择供应商");
            return null ;
        }

        if (TextUtils.isEmpty(canteenId)){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "请选择食堂");
            return null ;
        }

        if (picList != null && picList.size() != 0){
            ToastUtil.showShort(MyApp.appComponent.getContext() , "图片不能为空");
            return null ;
        }

        List<File> fileList = new ArrayList<>();
        for (String str :picList) {
            File folder = new File(str);
            fileList.add(folder);
        }

        Map<String , Object> param = new HashMap<>();
        param.put("name" , name);
        param.put("type" , type);
        param.put("price" , price);
        param.put("weight" , weight);
        param.put("purchasePerson" , purchasePerson);
        param.put("qualityGuaranteeDate" , qualityGuaranteeDate);
        param.put("qualityGuaranteeEndDate" , qualityGuaranteeEndDate);
        param.put("supplierId" , suppliers);
        param.put("flag" , flag);
        param.put("canteenId" , canteenId);
        param.put("files" , fileList);
        return param;
    }

    /**
     * 图片压缩后更新适配器
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mView.refreshPicAdapter();
            checkPic();
        }
    };

    /**
     * 检查图片是否存在
     */
    private void checkPic() {
       /* if (TextUtils.isEmpty(contentEt.getText().toString().trim())) {
            if (pictureList.size() == 0) {
                submitTv.setTextColor(getResources().getColor(R.color.gray_color));
            } else {
                submitTv.setTextColor(getResources().getColor(R.color.red));
            }
        } else {
            submitTv.setTextColor(getResources().getColor(R.color.red));
        }*/
    }
}