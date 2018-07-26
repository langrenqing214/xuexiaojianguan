package com.cxzy.xxjg.ui.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.LoginBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.test.contract.ILoginActivityContract;
import com.cxzy.xxjg.ui.test.presenter.LoginActivityPresenterImpl;
import com.cxzy.xxjg.utils.StatusBarUtil;
import com.cxzy.xxjg.utils.T;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.READ_CONTACTS;

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
    public void onRetry() {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btn_login :
                mPresenter.toLogin(etUserName.getText().toString().trim() , etPassWord.getText().toString().trim() , this);
                break;
        }
    }

    @Override
    public void loginResult(LoginBean loginBean) {

    }
}

