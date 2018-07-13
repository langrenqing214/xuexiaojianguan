package com.cxzy.xxjg.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxzy.xxjg.di.component.AppComponent;

/**
 * Created by demo on 2018/6/26.
 */

public interface IBase {
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getView();

    int getContentLayout();

    void initInjector(AppComponent appComponent);

    void bindView(View view,Bundle savedInstanceState);

    void initData();
}
