package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.MenuBean;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerAppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.ui.adapter.MenuAdapter;
import com.cxzy.xxjg.ui.test.presenter.MenuActivityPresenterImpl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 菜谱
 */
public class MenuActivity extends BaseActivity<MenuActivityPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, MenuAdapter.EditMenuClickListener, OnRefreshLoadMoreListener {

    @BindView(R.id.rv_canteen_menu)
    RecyclerView rvMenu;
    @BindView(R.id.main_title_id)
    TextView tvTitle;
    @BindView(R.id.srl_menu)
    SmartRefreshLayout srlMenu ;

    private MenuAdapter menuAdapter;
    private ArrayList<SchoolCanteenBean> dataList;
    private String canteenId;
    private String canteenName;
    private int page = 0 ;
    private int pageSize = 10 ;
    private MenuBean bean;

    @Override
    public int getContentLayout() {
        return R.layout.activity_menu;
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
        canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name ;
        tvTitle.setText(canteenName);
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id ;
        page = 0 ;
        mPresenter.getMenuList(canteenId, page, pageSize);

        srlMenu.setOnRefreshLoadMoreListener(this);
        srlMenu.setEnableLoadMore(false);

        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter(this , this);
        rvMenu.setAdapter(menuAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        if (mData != null) {
            bean = (MenuBean) mData;
            menuAdapter.setData(bean.list);
            if (bean.list != null && bean.list.size() == pageSize) {
                srlMenu.setEnableLoadMore(true);
                ;//启用加载;
            } else {
                srlMenu.setEnableLoadMore(false);
            }
            menuAdapter.notifyDataSetChanged();
        }
        srlMenu.finishRefresh();//结束刷新
        srlMenu.finishLoadMore();//结束加载
    }

    @Override
    public void refreshFaild(String faildCode) {
        srlMenu.finishRefresh(false);//结束刷新（刷新失败）
        srlMenu.finishLoadMore(false);//结束加载（加载失败）
        if ("401".equals(faildCode)){
            finish();
        }
    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_select_canteen, R.id.ll_add_menu})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.ll_select_canteen://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this ,dataList , this);
                canteenDialog.show();
                break;
            case R.id.ll_add_menu://添加菜谱
                Intent intent = new Intent(this , AddMenuActivity.class);
                intent.putExtra("canteenList" , dataList);
                intent.putExtra("type" , 0);
                startActivityForResult(intent , 1);
                break;
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId;
        this.canteenName = canteenName ;
        tvTitle.setText(canteenName);
        page = 0 ;
        mPresenter.getMenuList(canteenId , page , pageSize);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        page = 0 ;
        mPresenter.getMenuList(canteenId , page , pageSize);
    }

    //编辑菜谱
    @Override
    public void editMenuClickListener(int position) {
        Intent intent = new Intent(this , AddMenuActivity.class);
        intent.putExtra("canteenList" , dataList);
        intent.putExtra("canteenName" , canteenName);
        intent.putExtra("canteenId" , canteenId);
        intent.putExtra("breakfast" , bean.list.get(position).breakfast);
        intent.putExtra("lunch" , bean.list.get(position).lunch);
        intent.putExtra("dinner" , bean.list.get(position).dinner);
        intent.putExtra("releaseTime" , bean.list.get(position).releaseTime);
        intent.putExtra("id" , bean.list.get(position).id);
        intent.putExtra("type" , 1);
        startActivityForResult(intent , 1);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++ ;
        mPresenter.getMenuList(canteenId , page , pageSize);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0 ;
        mPresenter.getMenuList(canteenId , page , pageSize);
    }
}
