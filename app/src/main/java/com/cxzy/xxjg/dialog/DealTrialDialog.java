package com.cxzy.xxjg.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.TrialListBean;
import com.cxzy.xxjg.ui.activitys.TrialManagementActivity;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.Calendar;

/**
 * 跟进试吃
 * Created by demo on 2018/8/3.
 */

public class DealTrialDialog extends Dialog implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private LinearLayout llNormal;
    private LinearLayout llError;
    private ImageView ivNormal;
    private ImageView ivError;
    private EditText etIntervalTime;
    private TextView tvStatusTime;
    private EditText etRemarks;
    private Button btnDeal;
    private Context mContext;
    private String status = "";//是否异常
    private String nyTime;
    private String hmTime;
    private String createDateStart;
    private SubmitDealTrialListener mListener ;
    private TrialListBean info ;

    public DealTrialDialog(@NonNull Context context ,TrialListBean info , SubmitDealTrialListener mListener) {
        super(context, R.style.select_canteen_dialog);
        this.mContext = context;
        this.mListener = mListener ;
        this.info = info ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_trial_dialog);
        llNormal = findViewById(R.id.ll_select_normal);
        llError = findViewById(R.id.ll_select_error);
        ivNormal = findViewById(R.id.iv_normal);
        ivError = findViewById(R.id.iv_error);
        etIntervalTime = findViewById(R.id.et_interval_time);
        tvStatusTime = findViewById(R.id.tv_status_time);
        etRemarks = findViewById(R.id.et_remarks);
        btnDeal = findViewById(R.id.btn_deal_trial);
        initListener();
    }

    @Override
    public void show() {

//        windowDeploy();

        // 设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
        super.show();
    }

    private void initListener() {
        llNormal.setOnClickListener(this);
        llError.setOnClickListener(this);
        tvStatusTime.setOnClickListener(this);
        btnDeal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_select_normal: //正常
                ivError.setBackgroundResource(R.drawable.icon_circle);
                ivNormal.setBackgroundResource(R.drawable.icon_normal);
                status = "NORMAL";
                break;
            case R.id.ll_select_error: //异常
                ivNormal.setBackgroundResource(R.drawable.icon_circle);
                ivError.setBackgroundResource(R.drawable.icon_error);
                status = "ERROR";
                break;
            case R.id.tv_status_time: //选择时间
                SelectTimeDialog dialog = new SelectTimeDialog(mContext, this);
                dialog.show();
                break;
            case R.id.btn_deal_trial: //正提交
                String intervalTime = etIntervalTime.getText().toString().trim();
                String remarks = etRemarks.getText().toString().trim();

                if (TextUtils.isEmpty(status)){
                    ToastUtil.showShort(mContext , "请选择是否正常");
                    return;
                }

//                if (TextUtils.isEmpty(intervalTime)){
//                    ToastUtil.showShort(mContext , "请输入间隔时间");
//                    return;
//                }
                if (TextUtils.isEmpty(remarks)){
                    ToastUtil.showShort(mContext , "请输入试吃反应");
                    return;
                }

                if (TextUtils.isEmpty(createDateStart) || TextUtils.isEmpty(hmTime)){
                    ToastUtil.showShort(mContext , "请选择反应时间");
                    return;
                }
                mListener.submitTrialListener(intervalTime ,status , (createDateStart + " " + hmTime) , remarks , info);
                dismiss();
                break;
        }
    }

    public interface SubmitDealTrialListener {
        void submitTrialListener(String intervalTime ,String status , String statusTime , String remarks , TrialListBean info);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR,year);
        startcal.set(Calendar.MONTH,month);
        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        nyTime = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
        TimePickerDialog dialog = new TimePickerDialog(mContext , TimePicker.AUTOFILL_TYPE_LIST,this , startcal.get(Calendar.HOUR) , startcal.get(Calendar.MINUTE) , true);
        dialog.show();
        createDateStart = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.HOUR,hour);
        startcal.set(Calendar.MINUTE,minute);
        hmTime = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(startcal.getTimeInMillis()));
        tvStatusTime.setText(nyTime + " " + hmTime);
    }
}
