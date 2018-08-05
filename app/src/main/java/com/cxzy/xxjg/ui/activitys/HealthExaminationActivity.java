package com.cxzy.xxjg.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.cxzy.xxjg.ui.test.presenter.HealthExaminationPresenterImpl;
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

/**
 * 卫生检查
 */
public class HealthExaminationActivity extends BaseActivity<HealthExaminationPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, RadioGroup.OnCheckedChangeListener {

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
    @BindView(R.id.tv_positive_pic)
    TextView tvPositivePic;
    @BindView(R.id.main_title_id)
    TextView tvTitle;
    @BindView(R.id.tv_check_name)
    TextView tvCheckName;
    @BindView(R.id.ll_environmental_check)
    LinearLayout llEnvironmentalCheck;
    @BindView(R.id.rg_env_check)
    RadioGroup rgEnvCheck ;
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
        rgCheck.setOnCheckedChangeListener(this);
        rgEnvCheck.setOnCheckedChangeListener(this);
        mAdapter = new CheckItemsAdapter(this, itemList);
        lvCheckItems.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        rgCheck.check(R.id.rb_morning_check);
        if (mData != null) {
            bean = (HealthExaminationBean) mData;
            personBean.clear();
            personBean.addAll(bean.persons);
        }
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_select_canteen, R.id.btn_add_through_person, R.id.btn_save_morningcheck, R.id.btn_add_no_through_person, R.id.btn_save_environmental_check ,
                R.id.tv_positive_pic , R.id.tv_opposite_pic , R.id.tv_positive_pic_one , R.id.tv_opposite_pic_one})
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
                File file = new File("F:\\myLearnMvp\\xuexiaojianguan\\app\\src\\main\\res\\drawable-xhdpi\\camera.png");
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                List<String> personList = new ArrayList<>();
                allList.clear();
                allList.addAll(throughPerson);
                allList.addAll(noThroughPerson);
                for (PersonsBean bean : allList) {
                    personList.add(new Gson().toJson(bean));
                }
                Map<String, Object> param = new HashMap<>();
                param.put("persons", personList);
//                param.put("files", fileList);
//                param.put("canteenId", canteenId);
//                param.put("typeId", typeId);

                mPresenter.saveMorningCheck(param);
                break;
            case R.id.btn_save_environmental_check://提交环境检查
                Map<String , Object> envParam = new HashMap<>();
                envParam.put("canteenId" ,canteenId);
                envParam.put("state" , envState);
                envParam.put("typeId" , typeId);
                mPresenter.saveEnvCheck(envParam);
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
            case R.id.tv_positive_pic ://正面照片
                if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE) || !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                } else {
                    ScreenUtils.initScreen(this);
                    Intent intent = new Intent(mContext, PhotoWallActivity.class);
                    intent.putExtra("isRadio", true);
                    startActivityForResult(intent, Constants.FLAG_CHOOSE_IMG);
                }
                break;
            case  R.id.tv_opposite_pic ://反面

                break;
            case R.id.tv_positive_pic_one ://正面照片2

                break;
            case R.id.tv_opposite_pic_one ://反面

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
            case R.id.rb_normal ://正常
                envState = "NORMAL";
                break;
            case R.id.rb_error ://不正常
                envState = "ERROR";
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
