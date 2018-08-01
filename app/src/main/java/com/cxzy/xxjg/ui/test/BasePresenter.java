package com.cxzy.xxjg.ui.test;

/**
 * Author: demo
 * Created on 2018/8/1
 */
public interface BasePresenter<V> {
    void init(V baseView);

    void unInit();
}