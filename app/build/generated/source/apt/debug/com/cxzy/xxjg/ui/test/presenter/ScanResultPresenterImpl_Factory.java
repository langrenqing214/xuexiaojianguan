// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.ScanResultApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ScanResultPresenterImpl_Factory implements Factory<ScanResultPresenterImpl> {
  private final Provider<ScanResultApi> apiProvider;

  public ScanResultPresenterImpl_Factory(Provider<ScanResultApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ScanResultPresenterImpl get() {
    return new ScanResultPresenterImpl(apiProvider.get());
  }

  public static ScanResultPresenterImpl_Factory create(Provider<ScanResultApi> apiProvider) {
    return new ScanResultPresenterImpl_Factory(apiProvider);
  }

  public static ScanResultPresenterImpl newScanResultPresenterImpl(ScanResultApi api) {
    return new ScanResultPresenterImpl(api);
  }
}