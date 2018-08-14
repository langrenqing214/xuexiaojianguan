package com.cxzy.xxjg.ui.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.test.presenter.ChangePasswordPresenterImpl;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenterImpl> {

    @BindView(R.id.et_old_pwd)
    EditText etOldPwd ;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd ;
    @BindView(R.id.et_again_pwd)
    EditText etAgainPwd ;
    private String userId;

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
        userId = getIntent().getStringExtra("id");
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshView(Object mData) {
//        ToastUtil.show(mContext , "成功");
        ToastUtil.showShort(mContext , "成功");
    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id , R.id.btn_change_pwd})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()){
            case R.id.back_btn_id : //返回
                finish();
                break;
            case R.id.btn_change_pwd ://修改密码
                Map<String  , Object> param = new HashMap<>();
                param.put("oldPassword" , etOldPwd.getText().toString().trim());
                param.put("newPassword" , etNewPwd.getText().toString().trim());
                param.put("id" , userId);
                mPresenter.changePwd(param);
                break;
        }
    }
}
