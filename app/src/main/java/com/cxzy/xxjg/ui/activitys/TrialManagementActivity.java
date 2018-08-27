package com.cxzy.xxjg.ui.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.bean.TrialBean;
import com.cxzy.xxjg.bean.TrialListBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.DealTrialDialog;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.adapter.TrialManagementAdapter;
import com.cxzy.xxjg.ui.test.presenter.TrialManagementPresenterImpl;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 试吃管理
 */
public class TrialManagementActivity extends BaseActivity<TrialManagementPresenterImpl> implements DatePickerDialog.OnDateSetListener, SelectCanteenDialog.SelectCanteenItemListener, TrialManagementAdapter.DealTrialListener, DealTrialDialog.SubmitDealTrialListener, OnRefreshLoadMoreListener {

    @BindView(R.id.rv_trial_management)
    RecyclerView rvTrial;
    @BindView(R.id.ll_canteen_select)
    LinearLayout llCanteenSelect;
    @BindView(R.id.tv_canteen_show)
    TextView tvCanteenShow;
    @BindView(R.id.ll_time_select)
    LinearLayout llTimeSelect;
    @BindView(R.id.tv_time_show)
    TextView tvTimeShow;
    @BindView(R.id.srl_trial)
    SmartRefreshLayout srlTrial;
    private ArrayList<SchoolCanteenBean> canteenList;
    private String canteenId = "";
    private String canteenName = "";
    private TrialManagementAdapter mAdapter;
    private int page = 1;
    private int pageSize = 10;
    private String createDateStart = "";
    private String createDateEnd = "";
    private String dateStart = "";
    private String dateEnd = "";
    private List<TrialListBean> beanList = new ArrayList<>();
    private int clickType = 0 ;//1 点击试吃跟进

    @Override
    public int getContentLayout() {
        return R.layout.activity_trial_management;
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
        canteenList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");
    }

    @Override
    public void initData() {
        canteenId = canteenList == null || canteenList.size() == 0 ? "" : canteenList.get(0).id;
        canteenName = canteenList == null || canteenList.size() == 0 ? "" : canteenList.get(0).name;
        tvCanteenShow.setText(canteenName);
        Long str = Calendar.getInstance().getTimeInMillis() - 7 * 24 * 60 * 60 * 1000 ;
        dateStart = DateUtil.timeToDataTime(str.toString());
        createDateStart = DateUtil.timeToDataTimeString(str.toString());
        dateEnd = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateEnd = DateUtil.date2NYR(Calendar.getInstance().getTime());
        tvTimeShow.setText(dateStart + "-" + dateEnd);

        srlTrial.setOnRefreshLoadMoreListener(this);
        srlTrial.setEnableLoadMore(false);

        page = 1 ;
        mPresenter.getTrialList(page, canteenId, createDateStart, createDateEnd, pageSize);

        rvTrial.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TrialManagementAdapter(mContext, this);
        rvTrial.setAdapter(mAdapter);
    }

    @Override
    public void refreshView(Object mData) {
        if (mData != null) {
            TrialBean bean = (TrialBean) mData;
            if (page == 1){
                beanList.clear();
            }
            beanList.addAll(bean.list);
            mAdapter.setData(beanList);
            if (beanList != null && beanList.size() < bean.total) {
                srlTrial.setEnableLoadMore(true);//启用加载;
            } else {
                srlTrial.setEnableLoadMore(false);
            }
        }
        mAdapter.notifyDataSetChanged();
        srlTrial.finishRefresh();//结束刷新
        srlTrial.finishLoadMore();//结束加载
        if (clickType == 1){
            srlTrial.autoRefresh();
        }
    }

    @Override
    public void refreshFaild(String faildCode) {
        srlTrial.finishRefresh(false);//结束刷新（刷新失败）
        srlTrial.finishLoadMore(false);//结束加载（加载失败）
        if ("401".equals(faildCode)){
            finish();
        }
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_add_trial, R.id.ll_canteen_select, R.id.ll_time_select})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.ll_add_trial://添加试吃
                Intent intent = new Intent(mContext, AddTrialActivity.class);
                intent.putExtra("canteenList", canteenList);
                intent.putExtra("REACTION_INTERVAL", getIntent().getSerializableExtra("REACTION_INTERVAL"));
                startActivityForResult(intent , 1);
                break;
            case R.id.ll_canteen_select://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, canteenList, this);
                canteenDialog.show();
                break;
            case R.id.ll_time_select://选择时间
                SelectTimeDialog timeDialog = new SelectTimeDialog(this, this);
                timeDialog.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1){
            srlTrial.autoRefresh();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar startcal = Calendar.getInstance();
        startcal.set(Calendar.YEAR, year);
        startcal.set(Calendar.MONTH, month);
        startcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateStart = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
        createDateStart = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
        SelectTimeDialog timeDialog = new SelectTimeDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar startcal = Calendar.getInstance();
                startcal.set(Calendar.YEAR, year);
                startcal.set(Calendar.MONTH, month);
                startcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateEnd = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(startcal.getTimeInMillis()));
                createDateEnd = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(startcal.getTimeInMillis()));
                tvTimeShow.setText(dateStart + "-" + dateEnd);
                page = 1 ;
                mPresenter.getTrialList(page, canteenId, createDateStart, createDateEnd, pageSize);
            }
        });
        timeDialog.show();
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        tvCanteenShow.setText(canteenName);
        page = 1 ;
        mPresenter.getTrialList(page, canteenId, createDateStart, createDateEnd, pageSize);
    }

    @Override
    public void dealTrialListener(TrialListBean info) {
        DealTrialDialog dialog = new DealTrialDialog(this, info, this);
        dialog.show();
    }

    @Override
    public void submitTrialListener(String intervalTime, String status, String statusTime, String remarks, TrialListBean info) {
        clickType = 1 ;
        Map<String, Object> param = new HashMap<>();
        param.put("canteenId", canteenId);
        param.put("id", info.id);
        param.put("statusTime", statusTime);
//        param.put("internalTime", intervalTime);
        param.put("status", status);
        param.put("remarks", remarks);
        mPresenter.dealTrialItem(param);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++ ;
        mPresenter.getTrialList(page, canteenId, createDateStart, createDateEnd, pageSize);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1 ;
        mPresenter.getTrialList(page, canteenId, createDateStart, createDateEnd, pageSize);
    }
}
