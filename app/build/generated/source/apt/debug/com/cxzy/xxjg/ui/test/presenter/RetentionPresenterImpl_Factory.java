// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.RetentionApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RetentionPresenterImpl_Factory implements Factory<RetentionPresenterImpl> {
  private final Provider<RetentionApi> apiProvider;

  public RetentionPresenterImpl_Factory(Provider<RetentionApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public RetentionPresenterImpl get() {
    return new RetentionPresenterImpl(apiProvider.get());
  }

  public static RetentionPresenterImpl_Factory create(Provider<RetentionApi> apiProvider) {
    return new RetentionPresenterImpl_Factory(apiProvider);
  }

  public static RetentionPresenterImpl newRetentionPresenterImpl(RetentionApi api) {
    return new RetentionPresenterImpl(api);
  }
}
