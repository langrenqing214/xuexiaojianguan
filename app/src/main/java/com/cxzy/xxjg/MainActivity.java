package com.cxzy.xxjg;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.base.SupportFragment;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.fragments.FristFragment;
import com.cxzy.xxjg.ui.fragments.SecondFragment;
import com.cxzy.xxjg.utils.StatusBarUtil;
import com.cxzy.xxjg.wideget.BottomBar;
import com.cxzy.xxjg.wideget.BottomBarTab;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private SupportFragment[] mFragments = new SupportFragment[4];

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

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
        if (savedInstanceState == null) {
            mFragments[0] = FristFragment.newInstance();
            mFragments[1] = SecondFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,
                    mFragments[0],
                    mFragments[1]);
        } else {
            mFragments[0] = findFragment(FristFragment.class);
            mFragments[1] = findFragment(SecondFragment.class);
        }

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_launcher_background, "新闻"))
                .addItem(new BottomBarTab(this, R.drawable.ic_launcher_foreground, "视频"));
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
    public void onRetry() {

    }
}
