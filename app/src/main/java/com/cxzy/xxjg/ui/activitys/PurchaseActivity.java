package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.PurchaseBean;
import com.cxzy.xxjg.bean.ResultItemBean;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectFoodStyleDialog;
import com.cxzy.xxjg.dialog.SelectSupplierDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.ui.adapter.PurchaseAdapter;
import com.cxzy.xxjg.ui.test.contract.IPurchaseActivityContract;
import com.cxzy.xxjg.ui.test.presenter.PurchaseActivityPresenterImpl;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.ScreenUtils;
import com.cxzy.xxjg.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 食材采购
 */
public class PurchaseActivity extends BaseActivity<PurchaseActivityPresenterImpl> implements IPurchaseActivityContract.View, PurchaseAdapter.RecyclerViewItemClickListener, SelectCanteenDialog.SelectCanteenItemListener, DatePickerDialog.OnDateSetListener, SelectSupplierDialog.SelectSupplierListener, SelectFoodStyleDialog.SelectFoodStyleListener, PurchaseAdapter.DeletePicItemListener {

    @BindView(R.id.rv_add_pic)
    RecyclerView rvAddPic;
    @BindView(R.id.et_food_name)
    EditText etFoodName;
    @BindView(R.id.et_food_style)
    TextView etFoodStyle;
    @BindView(R.id.et_food_price)
    EditText etFoodPrice;
    @BindView(R.id.et_food_weight)
    EditText etFoodWeight;
    @BindView(R.id.et_purchaser)
    EditText etPurchaser;
    @BindView(R.id.et_manufacture_date)
    TextView etManufactureDate;
    @BindView(R.id.et_shelf_life)
    EditText etShelfLife;
    @BindView(R.id.et_shelf_life_end)
    TextView etShelfLifeEnd;
    @BindView(R.id.et_suppliers)
    TextView etSuppliers;
    @BindView(R.id.main_title_id)
    TextView tvTitle;

    private List<String> picList = new ArrayList<>(5);
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private PurchaseAdapter mAdapter;
    private ArrayList<SchoolCanteenBean> dataList;
    private String canteenId;

    private int clickType = -1 ; //-1 为供应商 0 生产日期 1 保质到期
    private String manufactureDate;
    private String shelfLifeEnd;
    private List<PurchaseBean> listData;
    private String supplierId;
    private ArrayList<ResultItemBean> foodStyleList;
    private String foodStyleId;

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
        dataList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");
        foodStyleList = (ArrayList<ResultItemBean>) getIntent().getSerializableExtra("food_style");
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        String canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name;
        tvTitle.setText(canteenName);

        clickType = -1 ;
        mPresenter.getSupplierList();

        rvAddPic.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new PurchaseAdapter(this, picList , 5);
        rvAddPic.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
        mAdapter.setDeletePicItemListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        try {
            if (clickType == -1){
                listData = (List<PurchaseBean>) mData;
            }else {
                ToastUtil.showShort(this, "入库成功");
                finish();
            }
        }catch (Exception e){}

    }

    @Override
    public void refreshFaild(String faildCode) {
        if ("401".equals(faildCode)){
            finish();
        }
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onItemClick(int position) {
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) || !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            ScreenUtils.initScreen(this);
            Intent intent = new Intent(mContext, PhotoWallActivity.class);
            intent.putExtra("number", picList.size());
            intent.putExtra("maxNumber", 5);
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
                    picintent.putExtra("number", picList.size());
                    picintent.putExtra("maxNumber", 5);
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

    @OnClick({R.id.back_btn_id, R.id.btn_warehousing, R.id.btn_warehousing_and_out_treasury, R.id.ll_select_canteen,
            R.id.et_manufacture_date, R.id.et_shelf_life_end , R.id.et_suppliers , R.id.et_food_style})
    @Override
    public void onViewClicked(View view) {

        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.ll_select_canteen://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, dataList, this);
                canteenDialog.show();
                break;
            case R.id.btn_warehousing://出库
                String foodName = etFoodName.getText().toString().trim();
                String foodStyle = etFoodStyle.getText().toString().trim();
                String foodPrice = etFoodPrice.getText().toString().trim();
                String foodWeight = etFoodWeight.getText().toString().trim();
                String purchaser = etPurchaser.getText().toString().trim();
                String shelfLife = etShelfLife.getText().toString().trim();
                String suppliers = etSuppliers.getText().toString().trim();

                Map<String , RequestBody> param = mPresenter.checkInfo(foodName, foodStyleId, foodPrice, foodWeight, purchaser, manufactureDate, shelfLifeEnd,
                        supplierId, 1, canteenId, picList);

                if (param != null) {
                    mPresenter.savePurchase(param);
                }
                break;
            case R.id.btn_warehousing_and_out_treasury://入库并出库
                String foodName1 = etFoodName.getText().toString().trim();
                String foodStyle1 = etFoodStyle.getText().toString().trim();
                String foodPrice1 = etFoodPrice.getText().toString().trim();
                String foodWeight1 = etFoodWeight.getText().toString().trim();
                String purchaser1 = etPurchaser.getText().toString().trim();
                String shelfLife1 = etShelfLife.getText().toString().trim();
                String suppliers1 = etSuppliers.getText().toString().trim();

                Map<String, RequestBody> param1 = mPresenter.checkInfo(foodName1, foodStyleId, foodPrice1, foodWeight1, purchaser1, manufactureDate, shelfLifeEnd,
                        supplierId, 2, canteenId, picList);

                if (param1 != null) {
                    mPresenter.savePurchase(param1);
                }
                break;
            case R.id.et_manufacture_date://选择生产日期
                clickType = 0 ;
                SelectTimeDialog dialog = new SelectTimeDialog(this, this);
                dialog.show();
                break;
            case R.id.et_shelf_life_end://选择保质到期
                clickType = 1 ;
                SelectTimeDialog dialog1 = new SelectTimeDialog(this, this);
                dialog1.show();
                break;
            case R.id.et_suppliers ://选择供应商
                SelectSupplierDialog supplierDialog = new SelectSupplierDialog(this , listData , this);
                supplierDialog.show();
                break;
            case R.id.et_food_style ://选择食材类别
                SelectFoodStyleDialog foodStyleDialog = new SelectFoodStyleDialog(this , foodStyleList , this);
                foodStyleDialog.show();
                break;
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        tvTitle.setText(canteenName);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR,year);
        startcal.set(Calendar.MONTH,month);
        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        if (clickType == 0){//生产日期
            String nyTime = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
            manufactureDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
            etManufactureDate.setText(nyTime);
        }else {//保质到期
            String nyTime = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
            shelfLifeEnd = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
            etShelfLifeEnd.setText(nyTime);
        }
    }

    @Override
    public void selectSupplierItem(int positon, PurchaseBean info) {
        etSuppliers.setText(info.name);
        supplierId = info.id;
    }

    @Override
    public void selectFoodStyleItem(int positon, ResultItemBean info) {
        etFoodStyle.setText(info.name);
        foodStyleId = info.key;
    }

    @Override
    public void onDeletePicItem(int position) {
        picList.remove(position);
        mAdapter.notifyDataSetChanged();
    }
}
