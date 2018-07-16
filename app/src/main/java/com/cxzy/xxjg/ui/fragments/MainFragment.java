package com.cxzy.xxjg.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseFragment;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.test.contract.IMainFragmentContract;
import com.cxzy.xxjg.ui.test.presenter.TestContractPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 监管平台
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<TestContractPresenterImpl> implements IMainFragmentContract.View {

    @BindView(R.id.button_test)
    Button btnTest ;
    @BindView(R.id.dl_my_main)
    DrawerLayout dlMyMain ;

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

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.button_test)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.button_test :
                dlMyMain.openDrawer(Gravity.LEFT);
                break;
        }
    }


}
