// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.MenuApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MenuActivityPresenterImpl_Factory implements Factory<MenuActivityPresenterImpl> {
  private final Provider<MenuApi> apiProvider;

  public MenuActivityPresenterImpl_Factory(Provider<MenuApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public MenuActivityPresenterImpl get() {
    return new MenuActivityPresenterImpl(apiProvider.get());
  }

  public static MenuActivityPresenterImpl_Factory create(Provider<MenuApi> apiProvider) {
    return new MenuActivityPresenterImpl_Factory(apiProvider);
  }
}
