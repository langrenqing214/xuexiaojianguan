package com.cxzy.xxjg.di.component;

import com.cxzy.xxjg.ui.fragments.FristFragment;

import dagger.Component;

/**
 * Created by demo on 2018/6/27.
 */
@Component(dependencies = AppComponent.class)
public interface HttpComponent {
    void inject(FristFragment fristFragment);
}
