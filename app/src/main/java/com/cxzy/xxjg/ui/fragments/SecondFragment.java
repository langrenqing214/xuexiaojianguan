package com.cxzy.xxjg.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseFragment;
import com.cxzy.xxjg.bean.TestBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.ui.activitys.TestActivity;
import com.cxzy.xxjg.ui.test.contract.ITestContractContract;
import com.cxzy.xxjg.ui.test.presenter.TestContractPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment<TestContractPresenterImpl> implements ITestContractContract.View {

    @BindView(R.id.button_test)
    Button btnTest ;

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
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

    @Override
    public void loadTestDetails(TestBean testBean) {

    }

    @OnClick(R.id.button_test)
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.button_test :
                startActivity(new Intent(mContext , TestActivity.class));
                break;
        }
    }
}
