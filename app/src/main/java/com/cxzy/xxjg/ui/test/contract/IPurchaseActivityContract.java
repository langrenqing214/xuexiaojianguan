package com.cxzy.xxjg.ui.test.contract;

import android.app.Activity;
import android.content.Intent;

import com.cxzy.xxjg.base.BaseContract;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 食材采购
 * Author: demo
 * Created on 2018/7/19
 */
public interface IPurchaseActivityContract {

    interface View extends BaseContract.BaseView {
        void refreshPicAdapter();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getSupplierList();

        List<String> dealPicResult(Activity activity, int requestCode, int resultCode, Intent data);

        void savePurchase(Map<String, RequestBody> param);

        Map<String , RequestBody> checkInfo(String name, String type, String price, String weight, String purchasePerson, String qualityGuaranteeDate,
                                      String qualityGuaranteeEndDate, String suppliers, int flag, String canteenId, List<String> picList);
    }
}