package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.ui.adapter.PurchaseAdapter;
import com.cxzy.xxjg.ui.test.contract.IPurchaseActivityContract;
import com.cxzy.xxjg.ui.test.presenter.PurchaseActivityPresenterImpl;
import com.cxzy.xxjg.utils.ScreenUtils;
import com.cxzy.xxjg.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 食材采购
 */
public class PurchaseActivity extends BaseActivity<PurchaseActivityPresenterImpl> implements IPurchaseActivityContract.View, PurchaseAdapter.RecyclerViewItemClickListener {

    @BindView(R.id.rv_add_pic)
    RecyclerView rvAddPic;
    @BindView(R.id.et_food_name)
    EditText etFoodName ;
    @BindView(R.id.et_food_style)
    EditText etFoodStyle ;
    @BindView(R.id.et_food_price)
    EditText etFoodPrice ;
    @BindView(R.id.et_food_weight)
    EditText etFoodWeight ;
    @BindView(R.id.et_purchaser)
    EditText etPurchaser ;
    @BindView(R.id.et_manufacture_date)
    EditText etManufactureDate ;
    @BindView(R.id.et_shelf_life)
    EditText etShelfLife ;
    @BindView(R.id.et_shelf_life_end)
    EditText etShelfLifeEnd ;
    @BindView(R.id.et_suppliers)
    EditText etSuppliers ;

    private List<String> picList = new ArrayList<>();
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private PurchaseAdapter mAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_purchase;
    }

    @Override
    public void initInjector(AppComponent appComponent) {
        DaggerHttpComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        rvAddPic.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new PurchaseAdapter(picList);
        rvAddPic.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        ToastUtil.showShort(this , "入库成功");
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onItemClick(int position) {
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) || !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            ScreenUtils.initScreen(this);
            Intent intent = new Intent(mContext, PhotoWallActivity.class);
//            intent.putExtra("number", picList.size());
            startActivityForResult(intent, Constants.FLAG_CHOOSE_IMG);
        }
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE:
                if (grantResults == null || grantResults.length == 0) {
                    return;
                }
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (cameraAccepted) {
                    //授权成功之后，调用系统相机进行拍照操作等
                    ScreenUtils.initScreen(this);
                    Intent picintent = new Intent(mContext, PhotoWallActivity.class);
//                    picintent.putExtra("number", picList.size());
                    startActivityForResult(picintent, Constants.FLAG_CHOOSE_IMG);
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) || !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        ToastUtil.showShort(mContext, "权限已拒绝请到设置页面手动开启");
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        picList = mPresenter.dealPicResult(this, requestCode, resultCode, data);
    }

    @Override
    public void refreshPicAdapter() {
        mAdapter.setDatas(picList);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.back_btn_id , R.id.btn_warehousing ,R.id.btn_warehousing_and_out_treasury})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                break;
            case R.id.btn_warehousing ://出库
                String foodName = etFoodName.getText().toString().trim();
                String foodStyle = etFoodStyle.getText().toString().trim();
                String foodPrice = etFoodPrice.getText().toString().trim();
                String foodWeight = etFoodWeight.getText().toString().trim();
                String purchaser = etPurchaser.getText().toString().trim();
                String manufactureDate = etManufactureDate.getText().toString().trim();
                String shelfLife = etShelfLife.getText().toString().trim();
                String shelfLifeEnd = etShelfLifeEnd.getText().toString().trim();
                String suppliers = etSuppliers.getText().toString().trim();

                List<File> fileList = new ArrayList<>();
                for (String str :picList) {
                    File folder = new File(str);
                    fileList.add(folder);
                }

                Map<String , Object> param = new HashMap<>();
                param.put("name" , foodName);
                param.put("type" , foodStyle);
                param.put("price" , foodPrice);
                param.put("weight" , foodWeight);
                param.put("purchasePerson" , purchaser);
                param.put("manufactureDate" , manufactureDate);
                param.put("qualityGuaranteeDate" , shelfLife);
                param.put("qualityGuaranteeEndDate" , suppliers);
                param.put("files" , fileList);
                mPresenter.savePurchase(param);
                break;
            case R.id.btn_warehousing_and_out_treasury ://入库并出库
                break;
        }
    }
}
