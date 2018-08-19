package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.HealthExaminationBean;
import com.cxzy.xxjg.bean.ItemsBean;
import com.cxzy.xxjg.bean.PersonsBean;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectPersonDialog;
import com.cxzy.xxjg.net.Constants;
import com.cxzy.xxjg.ui.adapter.CheckItemsAdapter;
import com.cxzy.xxjg.ui.adapter.PersonLabelAdapter;
import com.cxzy.xxjg.ui.adapter.PurchaseAdapter;
import com.cxzy.xxjg.ui.test.contract.IHealthExaminationContract;
import com.cxzy.xxjg.ui.test.presenter.HealthExaminationPresenterImpl;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.ScreenUtils;
import com.cxzy.xxjg.utils.ToastUtil;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 卫生检查
 */
public class HealthExaminationActivity extends BaseActivity<HealthExaminationPresenterImpl> implements IHealthExaminationContract.View , SelectCanteenDialog.SelectCanteenItemListener, RadioGroup.OnCheckedChangeListener, PurchaseAdapter.RecyclerViewItemClickListener {

    @BindView(R.id.rg_check)
    RadioGroup rgCheck;
    @BindView(R.id.tv_check_des)
    TextView tvCheckDes;
    @BindView(R.id.lv_check_items)
    ListView lvCheckItems;
    @BindView(R.id.ll_morning_check)
    LinearLayout llMorningCheck;
    @BindView(R.id.rv_through_person)
    RecyclerView rvThroughPerson;
    @BindView(R.id.rv_no_through_person)
    RecyclerView rvNoThroughPerson;
    @BindView(R.id.main_title_id)
    TextView tvTitle;
    @BindView(R.id.tv_check_name)
    TextView tvCheckName;
    @BindView(R.id.ll_environmental_check)
    LinearLayout llEnvironmentalCheck;
    @BindView(R.id.rv_add_pic)
    RecyclerView rvAddPic;
    @BindView(R.id.ll_nomor)
    LinearLayout llNomor ;
    @BindView(R.id.iv_normal)
    ImageView ivNormal ;
    @BindView(R.id.ll_error)
    LinearLayout llError ;
    @BindView(R.id.iv_error)
    ImageView ivError ;
    private String canteenId;
    private ArrayList<SchoolCanteenBean> dataList = new ArrayList<>();
    private HealthExaminationBean bean = new HealthExaminationBean();
    private List<ItemsBean> itemList = new ArrayList<>();
    private CheckItemsAdapter mAdapter;
    private List<PersonsBean> personBean = new ArrayList<>();
    private List<PersonsBean> throughPerson = new ArrayList<>(); //检查通过
    private List<PersonsBean> noThroughPerson = new ArrayList<>(); //检查未通过
    private List<PersonsBean> allList = new ArrayList<>();
    private String typeId = "";
    private String envState = "";
    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private PurchaseAdapter picAdapter;
    private List<String> picList = new ArrayList<>();
    private int clickType ; //1为提交


    @Override
    public int getContentLayout() {
        return R.layout.activity_health_examination;
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
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        mPresenter.getHealthCheck(canteenId);
        String canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name;
        tvTitle.setText(canteenName);
        mAdapter = new CheckItemsAdapter(this, itemList);
        lvCheckItems.setAdapter(mAdapter);
        rvAddPic.setLayoutManager(new GridLayoutManager(this, 3));
        picAdapter = new PurchaseAdapter(this , picList , 4);
        rvAddPic.setAdapter(picAdapter);
    }

    @Override
    public void initData() {
        rgCheck.setOnCheckedChangeListener(this);
        picAdapter.setItemClickListener(this);
    }

    @Override
    public void refreshView(Object mData) {
        try {
            if (mData != null) {
                bean = (HealthExaminationBean) mData;
                personBean.clear();
                personBean.addAll(bean.persons);
            }
            rgCheck.check(R.id.rb_morning_check);
            mAdapter.notifyDataSetChanged();
        }catch (Exception e){}

        if (clickType == 1){
            ToastUtil.showShort(this , "提交成功");
            finish();
        }
    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_select_canteen, R.id.btn_add_through_person, R.id.btn_save_morningcheck, R.id.btn_add_no_through_person,
            R.id.btn_save_environmental_check ,R.id.ll_nomor , R.id.ll_error})
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
            case R.id.btn_save_morningcheck://提交晨检
                List<File> fileList = new ArrayList<>();
                for (String str : picList){
                    File folder = new File(str);
                    fileList.add(folder);
                }
                List<String> personList = new ArrayList<>();
                allList.clear();
                allList.addAll(throughPerson);
                allList.addAll(noThroughPerson);
                if (allList == null || allList.size() == 0){
                    ToastUtil.showShort(this , "请对人员进行筛选");
                    return;
                }
                if (fileList == null || fileList.size() == 0){
                    ToastUtil.showShort(this , "拍照不能为空");
                    return;
                }
                Map<String, RequestBody> param = new HashMap<>();
                param.put("persons", RequestBody.create(MediaType.parse("form-data"),new Gson().toJson(allList)));
//                param.put("files", fileList);
                if(fileList != null && !fileList.isEmpty()) {
                    for (File file:fileList){
                        param.put("files\";filename=\""+file.getName(), RequestBody.create(MediaType.parse("form-data"), file));
                    }
                }
                param.put("canteenId", RequestBody.create(MediaType.parse("form-data"),canteenId));
                param.put("typeId", RequestBody.create(MediaType.parse("form-data"),typeId));

                mPresenter.saveMorningCheck(param);
                clickType = 1 ;
                break;
            case R.id.btn_save_environmental_check://提交环境检查
                if (TextUtils.isEmpty(envState)){
                    ToastUtil.showShort(this , "请选择是否正常");
                    return;
                }
                Map<String , Object> envParam = new HashMap<>();
                envParam.put("canteenId" ,canteenId);
                envParam.put("state" , envState);
                envParam.put("typeId" , typeId);
                mPresenter.saveEnvCheck(envParam);
                clickType = 1 ;
                break;
            case R.id.btn_add_through_person://添加检查通过
                SelectPersonDialog dialog = new SelectPersonDialog(this, 0, personBean, new SelectPersonDialog.SelectPersonClickLister() {
                    @Override
                    public void selectItemPerson(List<PersonsBean> infoList) {
                        throughPerson.addAll(infoList);
                        for (PersonsBean bean : infoList) {
                            if (personBean != null && personBean.contains(bean)) {
                                personBean.remove(bean);
                            }
                        }
                        FlexboxLayoutManager manager = new FlexboxLayoutManager(HealthExaminationActivity.this);
                        //设置主轴排列方式
                        manager.setFlexDirection(FlexDirection.ROW);
                        //设置是否换行
                        manager.setFlexWrap(FlexWrap.WRAP);
                        manager.setAlignItems(AlignItems.STRETCH);
                        rvThroughPerson.setLayoutManager(manager);
                        PersonLabelAdapter adapter = new PersonLabelAdapter(throughPerson, HealthExaminationActivity.this);
                        rvThroughPerson.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.show();
                break;
            case R.id.btn_add_no_through_person://添加检查未通过
                SelectPersonDialog dialog1 = new SelectPersonDialog(this, 1, personBean, new SelectPersonDialog.SelectPersonClickLister() {
                    @Override
                    public void selectItemPerson(List<PersonsBean> infoList) {
                        noThroughPerson.addAll(infoList);
                        for (PersonsBean bean : infoList) {
                            if (personBean != null && personBean.contains(bean)) {
                                personBean.remove(bean);
                            }
                        }
                        FlexboxLayoutManager manager = new FlexboxLayoutManager(HealthExaminationActivity.this);
                        //设置主轴排列方式
                        manager.setFlexDirection(FlexDirection.ROW);
                        //设置是否换行
                        manager.setFlexWrap(FlexWrap.WRAP);
                        manager.setAlignItems(AlignItems.STRETCH);
                        rvNoThroughPerson.setLayoutManager(manager);
                        PersonLabelAdapter adapter = new PersonLabelAdapter(noThroughPerson, HealthExaminationActivity.this);
                        rvNoThroughPerson.setAdapter(adapter);
                    }
                });
                dialog1.show();
                break;
            case R.id.ll_nomor ://正常
                ivNormal.setBackgroundResource(R.drawable.icon_normal);
                ivError.setBackgroundResource(R.drawable.icon_circle);
                envState = "NORMAL";
                break;
            case R.id.ll_error ://异常
                ivNormal.setBackgroundResource(R.drawable.icon_circle);
                ivError.setBackgroundResource(R.drawable.icon_error);
                envState = "ERROR";
                break;
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
                    picintent.putExtra("maxNumber", 4);
                    startActivityForResult(picintent, Constants.FLAG_CHOOSE_IMG);
                } else {
                    //用户授权拒绝之后，友情提示一下就可以了
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) || !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        ToastUtil.showShort(mContext, "权限已拒绝请到设置页面手动开启");
                    }
                }
                break;
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        tvTitle.setText(canteenName);
        mPresenter.getHealthCheck(canteenId);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_morning_check ://晨检
                try {
                    llMorningCheck.setVisibility(View.VISIBLE);
                    llEnvironmentalCheck.setVisibility(View.GONE);
                    tvCheckName.setText(bean.types.get(0).typeName);
                    tvCheckDes.setText(bean.types.get(0).typeDesc);
                    itemList.clear();
                    itemList.addAll(bean.types.get(0).items);
                    mAdapter.notifyDataSetChanged();
                    typeId = bean.types.get(0).typeId;
                }catch (Exception e){}
                break;
            case R.id.rb_environmental_check ://环境检查
                try {
                llMorningCheck.setVisibility(View.GONE);
                llEnvironmentalCheck.setVisibility(View.VISIBLE);
                tvCheckName.setText(bean.types.get(1).typeName);
                tvCheckDes.setText(bean.types.get(1).typeDesc);
                itemList.clear();
                itemList.addAll(bean.types.get(1).items);
                mAdapter.notifyDataSetChanged();
                typeId = bean.types.get(1).typeId;
                }catch (Exception e){}
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            picList = mPresenter.dealPicResult(requestCode , resultCode , data);
        }
    }

    @Override
    public void onItemClick(int position) {
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) || !hasPermission(Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.CAMERA},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
        } else {
            ScreenUtils.initScreen(this);
            Intent intent = new Intent(mContext, PhotoWallActivity.class);
            intent.putExtra("number", picList.size());
            intent.putExtra("maxNumber", 4);
            startActivityForResult(intent, Constants.FLAG_CHOOSE_IMG);
        }
    }

    @Override
    public void refreshPicAdapter() {
        picAdapter.setDatas(picList);
        picAdapter.notifyDataSetChanged();
    }
}
