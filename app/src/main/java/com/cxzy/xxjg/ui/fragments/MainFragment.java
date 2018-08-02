package com.cxzy.xxjg.ui.fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.base.BaseFragment;
import com.cxzy.xxjg.bean.MainFragmentBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.activitys.HealthExaminationActivity;
import com.cxzy.xxjg.ui.activitys.ListoricalWarningActivity;
import com.cxzy.xxjg.ui.activitys.LoginActivity;
import com.cxzy.xxjg.ui.activitys.MenuActivity;
import com.cxzy.xxjg.ui.activitys.MyCanteenActivity;
import com.cxzy.xxjg.ui.activitys.PurchaseActivity;
import com.cxzy.xxjg.ui.activitys.RegulatoryInformationActivity;
import com.cxzy.xxjg.ui.activitys.RetentionManageActivity;
import com.cxzy.xxjg.ui.activitys.ScanActivity;
import com.cxzy.xxjg.ui.activitys.SplashActivityActivity;
import com.cxzy.xxjg.ui.activitys.TrialManagementActivity;
import com.cxzy.xxjg.ui.activitys.VideoActivity;
import com.cxzy.xxjg.ui.test.contract.IMainFragmentContract;
import com.cxzy.xxjg.ui.test.presenter.MainFragmentContractPresenterImpl;
import com.cxzy.xxjg.utils.SharedPreferencesUtils;
import com.cxzy.xxjg.utils.ToastUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.trello.rxlifecycle2.LifecycleTransformer;

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
    @BindView(R.id.tv_alarm)
    TextView tvAlarm ;
    @BindView(R.id.tv_warning)
    TextView tvWarning ;
    @BindView(R.id.tv_deal)
    TextView tvDeal ;
    private MainFragmentBean bean = new MainFragmentBean();


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
        mPresenter.getUserInfo();
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.button_test, R.id.iv_to_zxing , R.id.cv_listorical_warning , R.id.cv_regulatory_information , R.id.cv_video , R.id.cv_menu ,
            R.id.cv_purchase , R.id.cv_health_examination , R.id.cv_retention_manage , R.id.cv_trial_management , R.id.ll_my_canteen ,
            R.id.ll_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_test:
                dlMyMain.openDrawer(Gravity.LEFT);
//                startActivity(new Intent(mContext, TestActivity.class));
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
            case R.id.cv_listorical_warning://历史警告
                Intent warningIntent = new Intent(mContext , ListoricalWarningActivity.class);
                warningIntent.putExtra("canteenList" , bean.canteenList);
                startActivity(warningIntent);
                break;
            case R.id.cv_regulatory_information://监管信息
                startActivity(new Intent(mContext , RegulatoryInformationActivity.class));
                break;
            case R.id.cv_video://视频
                startActivity(new Intent(mContext , VideoActivity.class));
                break;
            case R.id.cv_menu://菜谱
                Intent menuIntent = new Intent(mContext , MenuActivity.class);
                menuIntent.putExtra("canteenList" , bean.canteenList);
                startActivity(menuIntent);
                break;
            case R.id.cv_purchase://采购
                startActivity(new Intent(mContext , PurchaseActivity.class));
                break;
            case R.id.cv_health_examination://卫生检查
                Intent healthIntent = new Intent(mContext , HealthExaminationActivity.class);
                healthIntent.putExtra("canteenList" , bean.canteenList);
                startActivity(healthIntent);
                break;
            case R.id.cv_retention_manage://留样管理
                Intent retentionIntent = new Intent(mContext , RetentionManageActivity.class);
                retentionIntent.putExtra("canteenList" , bean.canteenList);
                startActivity(retentionIntent);
                break;
            case R.id.cv_trial_management://试吃管理
                Intent trialIntent = new Intent(mContext , TrialManagementActivity.class);
                trialIntent.putExtra("canteenList" , bean.canteenList);
                startActivity(trialIntent);
                break;
            case R.id.ll_my_canteen://我的食堂
                startActivity(new Intent(mContext , MyCanteenActivity.class));
                break;
            case R.id.ll_exit_login://退出登录
                exitdialog();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = mPresenter.getZxingResult(requestCode, resultCode, data);
        if (result != null) {
            ToastUtil.showShort(MyApp.appComponent.getContext(), result);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void refreshView(Object mData) {
        bean = (MainFragmentBean) mData;
        tvAlarm.setText(bean.alarmTotal);
        tvWarning.setText(bean.warnTotal);
        tvDeal.setText(bean.dealTotal);
    }

    protected void exitdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ToastUtil.showShort(mContext , "退出登录成功");
                SharedPreferencesUtils.setParam(MyApp.appComponent.getContext() , "app_token" , "");
                mContext.startActivity(new Intent(mContext ,LoginActivity.class));
                getActivity().finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    /*@Override
    public void getUserInfo(Object bean) {

    }*/
}
