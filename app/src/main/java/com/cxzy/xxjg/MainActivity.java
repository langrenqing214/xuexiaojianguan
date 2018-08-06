package com.cxzy.xxjg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.base.SupportFragment;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.fragments.MainFragment;
import com.cxzy.xxjg.ui.test.presenter.MainActivityPresenterImpl;
import com.cxzy.xxjg.utils.StatusBarUtil;
import com.cxzy.xxjg.wideget.BottomBar;
import com.trello.rxlifecycle2.LifecycleTransformer;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPresenterImpl> {

    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private SupportFragment[] mFragments = new SupportFragment[1];

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
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
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
        if (savedInstanceState == null) {
            mFragments[0] = MainFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,
                    mFragments[0]);
        } else {
            mFragments[0] = findFragment(MainFragment.class);
        }

        //添加底部tab
//        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, "新闻"))
//                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_foreground, "视频"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                getSupportDelegate().showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
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

    //退出APP
    @Override
    public void onBackPressedSupport() {
        mPresenter.isBackApp();
    }

}
