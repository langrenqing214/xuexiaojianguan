package com.cxzy.xxjg.ui.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.cxzy.xxjg.dialog.ScanResultDialog;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.adapter.RetentionManageAdapter;
import com.cxzy.xxjg.ui.test.presenter.RetentionPresenterImpl;
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
 * 留样管理
 */
public class RetentionManageActivity extends BaseActivity<RetentionPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, DatePickerDialog.OnDateSetListener, OnRefreshLoadMoreListener, RetentionManageAdapter.DealRetentionListener, ScanResultDialog.ScanResultListener {

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
    @BindView(R.id.srl_retention)
    SmartRefreshLayout srlRetention ;
    private String canteenId;
    private ArrayList<SchoolCanteenBean> dataList;
    private RetentionManageAdapter mAdapter;
    private int page = 0;
    private int pageSize = 10;
    private String createDateStart = "" ;
    private String createDateEnd = "" ;
    private String dateStart = "";
    private String dateEnd = "";
    private RetentionItemBean info ;
    private int clickType = 0 ;//0 为未处理  1为处理
    private List<RetentionItemBean> beanList = new ArrayList<>();

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
    }

    @Override
    public void initData() {
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        String canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name ;
        tvCanteenShow.setText(canteenName);
        Long str = Calendar.getInstance().getTimeInMillis() - 7 * 24 * 60 * 60 * 1000 ;
        dateStart = DateUtil.timeToDataTime(str.toString());
        createDateStart = DateUtil.timeToDataTimeString(str.toString());
        dateEnd = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateEnd = DateUtil.date2NYR(Calendar.getInstance().getTime());
        tvTimeShow.setText(dateStart + "-" + dateEnd);

        srlRetention.setOnRefreshLoadMoreListener(this);
        srlRetention.setEnableLoadMore(false);
        page = 0 ;
        mPresenter.getRetentionList(canteenId, createDateStart , createDateEnd , page, pageSize);
        rvRetention.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RetentionManageAdapter(this , this);
        rvRetention.setAdapter(mAdapter);
    }

    @Override
    public void refreshView(Object mData) {
        if (mData != null) {
            RetentionBean bean = (RetentionBean) mData;
            if (page == 0){
                beanList.clear();
            }
            beanList.addAll(bean.list);
            mAdapter.setData(beanList);
            if (beanList != null && beanList.size() < bean.total){
                srlRetention.setEnableLoadMore(true);//启用加载;
            }else {
                srlRetention.setEnableLoadMore(false);
            }
        }

        if (clickType == 1){
            srlRetention.autoRefresh();
        }
        mAdapter.notifyDataSetChanged();
        srlRetention.finishRefresh();//结束刷新
        srlRetention.finishLoadMore();//结束加载
    }

    @Override
    public void refreshFaild(String faildCode) {
        srlRetention.finishRefresh(false);//结束刷新（刷新失败）
        srlRetention.finishLoadMore(false);//结束加载（加载失败）
        if ("401".equals(faildCode)){
            finish();
        }
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
                startActivityForResult(intent , 1);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1){
            srlRetention.autoRefresh();
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        page = 0 ;
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
                tvTimeShow.setText(dateStart + "-" + dateEnd);
                page = 0 ;
                mPresenter.getRetentionList(canteenId , createDateStart , createDateEnd , page , pageSize);
            }
        });
        timeDialog.show();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++ ;
        mPresenter.getRetentionList(canteenId , createDateStart , createDateEnd , page , pageSize);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0 ;
        mPresenter.getRetentionList(canteenId , createDateStart , createDateEnd , page , pageSize);
    }

    //处理
    @Override
    public void dealRetentionListener(int position, RetentionItemBean info) {
        this.info = info ;
        ScanResultDialog dialog = new ScanResultDialog(this , 2 , this);
        dialog.show();
    }

    //填写处理人
    @Override
    public void scanResult(String person, String num) {
        if (TextUtils.isEmpty(person)){
            ToastUtil.showShort(this , "处理人不能为空");
            return;
        }
        clickType = 1 ;
        Map<String, Object> param = new HashMap<>();
        param.put("id" , info.id);
        param.put("dealPerson" , person);
        mPresenter.dealRetention(param);
    }
}
