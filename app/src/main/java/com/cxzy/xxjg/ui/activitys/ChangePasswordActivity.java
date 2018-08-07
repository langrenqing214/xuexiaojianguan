package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.test.presenter.ChangePasswordPresenterImpl;

import butterknife.OnClick;

/**
 * 修改密码
 */
public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenterImpl> {

    @Override
    public int getContentLayout() {
        return R.layout.activity_change_password;
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
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {

    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick
    @Override
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back_btn_id ://返回
                finish();
                break;
        }
    }
}
