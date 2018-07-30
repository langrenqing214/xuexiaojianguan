package com.cxzy.xxjg.ui.activitys;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;

import butterknife.BindView;

/**
 * 添加留样
 */
public class AddRetentionActivity extends BaseActivity {

    @BindView(R.id.tv_select_canteen)
    TextView tvSelectCanteen ;
    @BindView(R.id.et_put_food_name)
    EditText etFoodName ;
    @BindView(R.id.tv_add_retention_date)
    TextView tvRetentionDate ;
    @BindView(R.id.tv_add_retention_person)
    TextView tvRetentionPerson ;
    @BindView(R.id.tv_select_expiry_time)
    TextView tvExpiryTime ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_add_retention;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {

    }

    @Override
    public void onRetry() {

    }
}
