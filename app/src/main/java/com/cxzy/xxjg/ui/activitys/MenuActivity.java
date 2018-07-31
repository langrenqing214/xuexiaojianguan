package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;
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
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerAppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.ui.adapter.MenuAdapter;
import com.cxzy.xxjg.ui.test.presenter.MenuActivityPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 菜谱
 */
public class MenuActivity extends BaseActivity<MenuActivityPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener {

    @BindView(R.id.rv_canteen_menu)
    RecyclerView rvMenu;
    @BindView(R.id.main_title_id)
    TextView tvTitle;

    private MenuAdapter menuAdapter;

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
        mPresenter.getMenuList(1, 0, 10);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter(this);
        rvMenu.setAdapter(menuAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
        MenuBean bean = (MenuBean) mData;
        menuAdapter.setData(bean.list);
        menuAdapter.notifyDataSetChanged();
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
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this , this);
                canteenDialog.show();
                break;
            case R.id.ll_add_menu://添加菜谱
                startActivity(new Intent(this , AddMenuActivity.class));
                break;
        }
    }

    @Override
    public void selectCanteenItem(int positon) {

    }
}
