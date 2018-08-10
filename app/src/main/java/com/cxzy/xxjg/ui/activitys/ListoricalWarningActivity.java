package com.cxzy.xxjg.ui.activitys;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.bean.WarningBean;
import com.cxzy.xxjg.bean.WarningItemBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.ScanResultDialog;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectTimeDialog;
import com.cxzy.xxjg.ui.adapter.WarningAdapter;
import com.cxzy.xxjg.ui.test.presenter.WarningPresenterImpl;
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
 * 历史警告
 */
public class ListoricalWarningActivity extends BaseActivity<WarningPresenterImpl> implements DatePickerDialog.OnDateSetListener, SelectCanteenDialog.SelectCanteenItemListener, WarningAdapter.DealItemClickListener, OnRefreshLoadMoreListener, ScanResultDialog.ScanResultListener {

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
    @BindView(R.id.srl_warning)
    SmartRefreshLayout srlWarning ;
    private WarningAdapter mAdapter;
    private ArrayList<SchoolCanteenBean> dataList;
    private String canteenId;
    private int page = 0 ;
    private int pageSize = 10 ;
    private String createDateStart = "" ;
    private String createDateEnd = "" ;
    private String timeStr = "";
    private String dateStart = "";
    private String dateEnd = "";
    private String level;
    private List<WarningItemBean> itemList = new ArrayList<>();
    private WarningItemBean info ;
    private int clickType = 0 ;//1位处理后返回数据

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
        dateStart = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateStart = DateUtil.date2NYR(Calendar.getInstance().getTime());
        dateEnd = DateUtil.date2yyyyMMdd(Calendar.getInstance().getTime());
        createDateEnd = DateUtil.date2NYR(Calendar.getInstance().getTime());
        dataList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");
        level = getIntent().getStringExtra("level");
        tvCanteenShow.setText(dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name);
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        tvTimeShow.setText(createDateStart + "-" + createDateEnd);

        mPresenter.getWarningList(level , canteenId , createDateStart , createDateEnd , page , pageSize);
        //刷新
        srlWarning.setOnRefreshLoadMoreListener(this);
        srlWarning.setEnableLoadMore(false);

        rvWarning.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WarningAdapter(this , itemList);
        rvWarning.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        if(mData != null) {
            WarningBean bean = (WarningBean) mData;
            if (page == 0){
                itemList.clear();
            }
            itemList.addAll(bean.list);
            mAdapter.setData(itemList);
            if (itemList != null && itemList.size() >= bean.total){
                srlWarning.setEnableLoadMore(false);;//启用加载;
            }else {
                srlWarning.setEnableLoadMore(true);
            }

            mAdapter.notifyDataSetChanged();
            srlWarning.finishRefresh();//结束刷新
            srlWarning.finishLoadMore();//结束加载
        }
        if (clickType == 1){
            srlWarning.autoRefresh();
        }
    }

    @Override
    public void refreshFaild() {
        srlWarning.finishRefresh(false);//结束刷新（刷新失败）
        srlWarning.finishLoadMore(false);//结束加载（加载失败）
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id , R.id.ll_canteen_select , R.id.ll_time_select})
    public void onViewClicked(View v){

        switch (v.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
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
                page = 0 ;
                mPresenter.getWarningList(level , canteenId , createDateStart , createDateEnd , page , pageSize);
            }
        });
        timeDialog.show();
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        tvCanteenShow.setText(canteenName);
        page = 0 ;
        mPresenter.getWarningList(level , canteenId , createDateStart , createDateEnd , page , pageSize);
    }

    @Override
    public void dealItemClickListener(int position , WarningItemBean info) {
        this.info = info ;
        ScanResultDialog dialog = new ScanResultDialog(this , 2 , this);
        dialog.show();

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++ ;
        mPresenter.getWarningList(level , canteenId , createDateStart , createDateEnd , page , pageSize);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0 ;
        mPresenter.getWarningList(level , canteenId , createDateStart , createDateEnd , page , pageSize);
    }

    @Override
    public void scanResult(String person, String num) {
        if (TextUtils.isEmpty(person)){
            ToastUtil.showShort(this , "处理人不能为空");
            return;
        }
        clickType = 1 ;
        Map<String , Object> param = new HashMap<>();
        param.put("id" , info.id);
        param.put("dealUserName" , person);
        mPresenter.dealWarning(param);
    }
}
