package com.cxzy.xxjg.ui.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.RetentionBean;
import com.cxzy.xxjg.bean.RetentionItemBean;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.adapter.RetentionManageAdapter;
import com.cxzy.xxjg.ui.test.presenter.RetentionPresenterImpl;
import com.cxzy.xxjg.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 留样管理
 */
public class RetentionManageActivity extends BaseActivity<RetentionPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.rv_retention)
    RecyclerView rvRetention;
    @BindView(R.id.ll_canteen_select)
    LinearLayout llCanteenSelect;
    @BindView(R.id.tv_canteen_show)
    TextView tvCanteenShow;
    @BindView(R.id.ll_time_select)
    LinearLayout llTimeSelect;
    @BindView(R.id.tv_time_show)
    TextView tvTimeShow;
    private String canteenId;
    private ArrayList<SchoolCanteenBean> dataList;
    private RetentionManageAdapter mAdapter;
    private int page = 0;
    private int pageSize = 10;
    private String createDateStart = "" ;
    private String createDateEnd = "" ;
    private String dateStart = "";
    private String dateEnd = "";

    @Override
    public int getContentLayout() {
        return R.layout.activity_retention_manage;
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
        tvCanteenShow.setText(dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name);
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        String canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name ;
        tvCanteenShow.setText(canteenName);
        dateStart = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateStart = DateUtil.date2NYR(Calendar.getInstance().getTime());
        dateEnd = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateEnd = DateUtil.date2NYR(Calendar.getInstance().getTime());
        tvTimeShow.setText(dateStart + "-" + dateEnd);
        mPresenter.getRetentionList(canteenId, createDateStart , createDateEnd , page, pageSize);
        rvRetention.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RetentionManageAdapter(this);
        rvRetention.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        RetentionBean bean = (RetentionBean) mData;
        mAdapter.setData(bean.list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_add_retention, R.id.ll_time_select, R.id.ll_canteen_select})
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.ll_add_retention://添加留样
                Intent intent = new Intent(mContext, AddRetentionActivity.class);
                intent.putExtra("canteenList", dataList);
                startActivity(intent);
                break;
            case R.id.ll_canteen_select://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, dataList, this);
                canteenDialog.show();
                break;
            case R.id.ll_time_select://选择时间
                SelectTimeDialog timeDialog = new SelectTimeDialog(this, this);
                timeDialog.show();
                break;
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        tvCanteenShow.setText(canteenName);
        mPresenter.getRetentionList(canteenId , createDateStart , createDateEnd , page , pageSize);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR,year);
        startcal.set(Calendar.MONTH,month);
        startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        dateStart = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
        createDateStart = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
        SelectTimeDialog timeDialog = new SelectTimeDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar startcal = Calendar.getInstance();
                startcal.set(Calendar.YEAR,year);
                startcal.set(Calendar.MONTH,month);
                startcal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                dateEnd = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
                createDateEnd = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                tvTimeShow.setText(createDateStart + "-" + createDateEnd);
                mPresenter.getRetentionList(canteenId , createDateStart , createDateEnd , page , pageSize);
            }
        });
        timeDialog.show();
    }
}
