// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.test.presenter;

import dagger.internal.Factory;

public final class MainActivityPresenterImpl_Factory implements Factory<MainActivityPresenterImpl> {
  private static final MainActivityPresenterImpl_Factory INSTANCE =
      new MainActivityPresenterImpl_Factory();

  @Override
  public MainActivityPresenterImpl get() {
    return new MainActivityPresenterImpl();
  }

  public static MainActivityPresenterImpl_Factory create() {
    return INSTANCE;
  }

  public static MainActivityPresenterImpl newMainActivityPresenterImpl() {
    return new MainActivityPresenterImpl();
  }
}
