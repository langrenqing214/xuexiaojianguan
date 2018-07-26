package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.adapter.MenuAdapter;

import butterknife.BindView;

/**
 * 菜谱
 */
public class MenuActivity extends BaseActivity {

    @BindView(R.id.rv_canteen_menu)
    RecyclerView rvMenu ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_menu;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        MenuAdapter menuAdapter = new MenuAdapter();
        rvMenu.setAdapter(menuAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }
}
