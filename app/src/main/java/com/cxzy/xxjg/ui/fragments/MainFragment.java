package com.cxzy.xxjg.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseFragment;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.activitys.ScanActivity;
import com.cxzy.xxjg.ui.activitys.TestActivity;
import com.cxzy.xxjg.ui.test.contract.IMainFragmentContract;
import com.cxzy.xxjg.ui.test.presenter.MainFragmentContractPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.TestContractPresenterImpl;
import com.cxzy.xxjg.utils.StatusBarUtil;
import com.cxzy.xxjg.utils.T;
import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 监管平台
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<MainFragmentContractPresenterImpl> implements IMainFragmentContract.View {

    @BindView(R.id.button_test)
    Button btnTest;
    @BindView(R.id.dl_my_main)
    DrawerLayout dlMyMain;
    @BindView(R.id.tv_phone_info)
    TextView tvInfo ;


    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_second;
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
    }

    @Override
    public void initData() {
        tvInfo.setText("系统版本号：" + StatusBarUtil.getSystemVersion() + "  状态栏高度：" + StatusBarUtil.getStatusBarHeight(mContext));
    }


    @OnClick({R.id.button_test, R.id.iv_to_zxing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_test:
//                dlMyMain.openDrawer(Gravity.LEFT);
                startActivity(new Intent(mContext, TestActivity.class));
                break;
            case R.id.iv_to_zxing:
                new IntentIntegrator(_mActivity)
                        .setOrientationLocked(false)
                        .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是ScanActivity
                        .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                        .setPrompt("请对准二维码")// 设置提示语
                        .setCameraId(0)// 选择摄像头,可使用前置或者后置
                        .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                        .setBarcodeImageEnabled(false)// 扫完码之后生成二维码的图片
                        .initiateScan();// 初始化扫码
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = mPresenter.getZxingResult(requestCode, resultCode, data);
        if (result != null) {
            T.showShort(MyApp.appComponent.getContext(), result);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
