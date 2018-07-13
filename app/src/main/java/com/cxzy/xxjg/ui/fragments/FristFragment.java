package com.cxzy.xxjg.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseFragment;
import com.cxzy.xxjg.bean.TestBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.ui.test.contract.ITestContractContract;
import com.cxzy.xxjg.ui.test.presenter.TestContractPresenterImpl;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FristFragment extends BaseFragment<TestContractPresenterImpl> implements ITestContractContract.View {

    @BindView(R.id.tv_first_fragment)
    TextView tvFristFragment ;

    public static FristFragment newInstance() {
        Bundle args = new Bundle();
        FristFragment fragment = new FristFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_frist;
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
        mPresenter.getTestDetails();
    }


    @Override
    public void loadTestDetails(TestBean testBean) {
        tvFristFragment.setText(testBean.update_content);
    }
}
