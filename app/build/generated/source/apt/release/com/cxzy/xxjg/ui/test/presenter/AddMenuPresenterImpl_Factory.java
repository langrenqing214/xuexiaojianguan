// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.AddMenuApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AddMenuPresenterImpl_Factory implements Factory<AddMenuPresenterImpl> {
  private final Provider<AddMenuApi> apiProvider;

  public AddMenuPresenterImpl_Factory(Provider<AddMenuApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public AddMenuPresenterImpl get() {
    return new AddMenuPresenterImpl(apiProvider.get());
  }

  public static AddMenuPresenterImpl_Factory create(Provider<AddMenuApi> apiProvider) {
    return new AddMenuPresenterImpl_Factory(apiProvider);
  }
}