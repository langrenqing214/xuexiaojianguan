package com.cxzy.xxjg.ui.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.bean.WarningBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.adapter.WarningAdapter;
import com.cxzy.xxjg.ui.test.presenter.WarningPresenterImpl;
import com.cxzy.xxjg.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 历史警告
 */
public class ListoricalWarningActivity extends BaseActivity<WarningPresenterImpl> implements DatePickerDialog.OnDateSetListener, SelectCanteenDialog.SelectCanteenItemListener {

    @BindView(R.id.ll_canteen_select)
    LinearLayout llCanteenSelect;
    @BindView(R.id.tv_canteen_show)
    TextView tvCanteenShow;
    @BindView(R.id.ll_time_select)
    LinearLayout llTimeSelect;
    @BindView(R.id.tv_time_show)
    TextView tvTimeShow;
    @BindView(R.id.rv_warning)
    RecyclerView rvWarning ;
    private WarningAdapter mAdapter;
    private ArrayList<SchoolCanteenBean> dataList;
    private String canteenId;
    private int page = 0 ;
    private int pageSize = 10 ;
    private String createDateStart = "" ;
    private String createDateEnd = "" ;
    private String timeStr = "";

    @Override
    public int getContentLayout() {
        return R.layout.activity_listorical_warning;
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
        setStatusBarColor(ContextCompat.getColor(mContext , R.color.main_style_color));
        createDateStart = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateEnd = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        dataList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");
        tvCanteenShow.setText(dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name);
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        tvTimeShow.setText(createDateStart + "-" + createDateEnd);
        mPresenter.getWarningList(canteenId , createDateStart , createDateEnd , page , pageSize);

        rvWarning.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WarningAdapter();
        rvWarning.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        WarningBean bean = (WarningBean) mData;
        mAdapter.setData(bean.list);
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.ll_canteen_select , R.id.ll_time_select})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.ll_canteen_select ://食堂选择
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this ,dataList , this);
                canteenDialog.show();
                break;
            case R.id.ll_time_select ://时间选择
                SelectTimeDialog timeDialog = new SelectTimeDialog(this, this);
                timeDialog.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR,year);
        startcal.set(Calendar.MONTH,month);
        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        createDateStart = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
        SelectTimeDialog timeDialog = new SelectTimeDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar startcal = Calendar.getInstance();
                startcal.set(Calendar.YEAR,year);
                startcal.set(Calendar.MONTH,month);
                startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                createDateEnd = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
                tvTimeShow.setText(createDateStart + "-" + createDateEnd);
                mPresenter.getWarningList(canteenId , createDateStart , createDateEnd , page , pageSize);
            }
        });
        timeDialog.show();
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        tvCanteenShow.setText(canteenName);
        mPresenter.getWarningList(canteenId , createDateStart , createDateEnd , page , pageSize);
    }
}
