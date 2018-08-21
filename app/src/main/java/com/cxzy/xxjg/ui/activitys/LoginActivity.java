package com.cxzy.xxjg.ui.activitys;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.LoginBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract;
import com.cxzy.xxjg.ui.test.presenter.LoginActivityPresenterImpl;
import com.cxzy.xxjg.utils.NetUtil;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;
import com.cxzy.xxjg.utils.StatusBarUtil;
import com.cxzy.xxjg.utils.ToastUtil;

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
    @BindView(R.id.et_main_url)
    EditText etMainUrl ;
    private AppComponent appComponent ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initInjector(AppComponent appComponent) {
        this.appComponent = appComponent ;
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
        String mainUrl = (String) SharedPreferencesUtils.getParam(this , "main_url" , "http://47.95.252.122:8080/wisdom/");
        etMainUrl.setText(TextUtils.isEmpty(mainUrl) ? "http://47.95.252.122:8080/wisdom/" : mainUrl);
        /*DaggerHttpComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);*/
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

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view){

        switch (view.getId()){
            case R.id.btn_login :
                String url = TextUtils.isEmpty(etMainUrl.getText().toString().trim())? "http://47.95.252.122:8080/wisdom/" : etMainUrl.getText().toString().trim() ;
                SharedPreferencesUtils.setParam(this , "main_url" , url);
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

