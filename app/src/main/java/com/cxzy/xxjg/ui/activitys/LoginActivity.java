package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.LoginBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract;
import com.cxzy.xxjg.ui.test.presenter.LoginActivityPresenterImpl;
import com.cxzy.xxjg.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginActivityPresenterImpl> implements ILoginActivityContract.View{

    @BindView(R.id.et_username)
    EditText etUserName ;
    @BindView(R.id.et_password)
    EditText etPassWord ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
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
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
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

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btn_login :
                mPresenter.toLogin(etUserName.getText().toString().trim() , etPassWord.getText().toString().trim());
                break;
        }
    }

    //处理登录成功
    @Override
    public void loginResult(LoginBean loginBean) {
        startActivity(new Intent(this , MainActivity.class));
        finish();
    }
}

