package com.cxzy.xxjg.ui.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.cxzy.xxjg.ui.test.presenter.AddRetentionPresenterImpl;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加留样
 */
public class AddRetentionActivity extends BaseActivity<AddRetentionPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.tv_select_canteen)
    TextView tvSelectCanteen;
    @BindView(R.id.et_put_food_name)
    EditText etFoodName;
    @BindView(R.id.tv_add_retention_date)
    TextView tvRetentionDate;
    @BindView(R.id.tv_add_retention_person)
    EditText tvRetentionPerson;
    @BindView(R.id.tv_select_expiry_time)
    TextView tvExpiryTime;

    private int clickType = 0;//0 为留样时间 1 为到期时间
    private String expiryTime;
    private String retentionDate;
    private ArrayList<SchoolCanteenBean> dataList;
    private String canteenId = "";

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_retention;
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
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        ToastUtil.showShort(this , "添加成功");
        setResult(Activity.RESULT_OK);
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

    @OnClick({R.id.back_btn_id, R.id.btn_add_retention, R.id.tv_select_canteen, R.id.tv_select_expiry_time, R.id.tv_add_retention_date})
    @Override
    public void onViewClicked(View view) {

        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.btn_add_retention://添加留样
                String foodName = etFoodName.getText().toString().trim();
                String person = tvRetentionPerson.getText().toString().trim();
                if (TextUtils.isEmpty(canteenId)){
                    ToastUtil.showShort(this , "请选择食堂");
                    return;
                }
                if (TextUtils.isEmpty(foodName)){
                    ToastUtil.showShort(this , "请输入食品名称");
                    return;
                }
                if (TextUtils.isEmpty(retentionDate)){
                    ToastUtil.showShort(this , "请选择留样时间");
                    return;
                }
                if (TextUtils.isEmpty(person)){
                    ToastUtil.showShort(this , "请输入留样人");
                    return;
                }
                if (TextUtils.isEmpty(expiryTime)){
                    ToastUtil.showShort(this , "请选择到期时间");
                    return;
                }
                Map<String, Object> param = new HashMap<>();
                param.put("canteenId" , canteenId);
                param.put("foodName" , foodName);
                param.put("reservedTimeStr" , retentionDate);
                param.put("expiryTimeStr" , expiryTime);
                param.put("reservedPerson" , person);
                mPresenter.saveRetention(param);
                break;
            case R.id.tv_select_canteen://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, dataList , this);
                canteenDialog.show();
                break;
            case R.id.tv_select_expiry_time://选择到期时间
                clickType = 1;
                SelectTimeDialog endTimeDialog = new SelectTimeDialog(this, this);
                endTimeDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
                endTimeDialog.show();
                break;
            case R.id.tv_add_retention_date://选择留样时间
                clickType = 0;
                SelectTimeDialog timeDialog = new SelectTimeDialog(this, this);
                timeDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() - 1000);
                timeDialog.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR, year);
        startcal.set(Calendar.MONTH, month);
        startcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (clickType == 1) {
            expiryTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
//            tvExpiryTime.setText(expiryTime);
        }else {
            retentionDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
//            tvRetentionDate.setText(retentionDate);
        }
        TimePickerDialog dialog = new TimePickerDialog(AddRetentionActivity.this , TimePicker.AUTOFILL_TYPE_LIST,this , startcal.get(Calendar.HOUR_OF_DAY) , startcal.get(Calendar.MINUTE) , true);
        dialog.show();
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        tvSelectCanteen.setText(canteenName);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.HOUR,hour);
        startcal.set(Calendar.MINUTE,minute);
        if (clickType == 1) {
            expiryTime = expiryTime + " " + new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(startcal.getTimeInMillis()));
            tvExpiryTime.setText(expiryTime);
        }else {
            retentionDate = retentionDate + " " + new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(startcal.getTimeInMillis()));
            tvRetentionDate.setText(retentionDate);
        }
    }
}
