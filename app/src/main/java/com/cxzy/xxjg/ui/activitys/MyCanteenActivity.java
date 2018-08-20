package com.cxzy.xxjg.ui.activitys;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.adapter.MyCanteenAdapter;

import butterknife.BindView;

/**
 * 我的食堂
 */
public class MyCanteenActivity extends BaseActivity {

    @BindView(R.id.rv_my_canteen)
    RecyclerView rvMyCanteen ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_my_canteen;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        rvMyCanteen.setLayoutManager(new LinearLayoutManager(this));
        MyCanteenAdapter mAdapter = new MyCanteenAdapter();
        rvMyCanteen.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {

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
}
