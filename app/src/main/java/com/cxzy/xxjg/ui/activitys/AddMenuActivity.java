package com.cxzy.xxjg.ui.activitys;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.test.presenter.AddMenuPresenterImpl;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加菜谱
 */
public class AddMenuActivity extends BaseActivity<AddMenuPresenterImpl> implements DatePickerDialog.OnDateSetListener, SelectCanteenDialog.SelectCanteenItemListener {

    @BindView(R.id.tv_select_canteen)
    TextView tvCanteen ;
    @BindView(R.id.tv_select_time)
    TextView tvTime ;
    @BindView(R.id.et_breakfast)
    EditText etBreakfast ;
    @BindView(R.id.et_lunch)
    EditText etLunch ;
    @BindView(R.id.et_dinner)
    EditText etDinner ;
    private ArrayList<SchoolCanteenBean> dataList;
    private String canteenId;
    private String date;
    private int type; //0:增加菜谱 1:修改菜谱
    private String canteenName;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String releaseTime;
    private String id;

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_menu;
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
        type = getIntent().getIntExtra("type" , 0);
        canteenName = getIntent().getStringExtra("canteenName" );
        canteenId = getIntent().getStringExtra("canteenId" );
        breakfast = getIntent().getStringExtra("breakfast" );
        lunch = getIntent().getStringExtra("lunch" );
        dinner = getIntent().getStringExtra("dinner" );
        releaseTime = getIntent().getStringExtra("releaseTime" );
        id = getIntent().getStringExtra("id");
        if (type == 1){
            tvCanteen.setText(canteenName);
            tvTime.setText(DateUtil.timeToDataTimeString(releaseTime == null ? "" : releaseTime ));
            etBreakfast.setText(breakfast);
            etLunch.setText(lunch);
            etDinner.setText(dinner);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        ToastUtil.showShort(this , "添加成功");
        setResult(11);
        finish();
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

    @OnClick({R.id.tv_select_canteen , R.id.tv_select_time , R.id.btn_add_menu , R.id.back_btn_id})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
            case R.id.tv_select_canteen ://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this ,dataList , this);
                canteenDialog.show();
                break;
            case R.id.tv_select_time ://选择时间、
                SelectTimeDialog timeDialog = new SelectTimeDialog(this , this);
                timeDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
                timeDialog.show();
                break;
            case R.id.btn_add_menu ://提交
                if (TextUtils.isEmpty(canteenId)){
                    ToastUtil.showShort(this , "请选择食堂");
                    return;
                }
                if (TextUtils.isEmpty(releaseTime)){
                    ToastUtil.showShort(this , "请选择时间");
                    return;
                }
                Map<String , Object> param = new HashMap<>();
                param.put("breakfast" , etBreakfast.getText().toString().trim());
                param.put("dinner" , etDinner.getText().toString().trim());
                param.put("lunch" , etLunch.getText().toString().trim());
                param.put("releaseTime" , releaseTime);
                param.put("canteenId" , canteenId);
                param.put("id" , id);
                mPresenter.saveMenu(param);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR,year);
        startcal.set(Calendar.MONTH,month);
        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//        TimePickerDialog dialog = new TimePickerDialog(AddMenuActivity.this , TimePicker.AUTOFILL_TYPE_LIST,this , 8 , 00 , true);
//        dialog.show();
        releaseTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
        tvTime.setText(releaseTime);
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        tvCanteen.setText(canteenName);
    }
}
