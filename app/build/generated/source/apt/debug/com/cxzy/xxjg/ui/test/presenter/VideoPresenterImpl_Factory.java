// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.test.presenter;

import com.cxzy.xxjg.net.VideoApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class VideoPresenterImpl_Factory implements Factory<VideoPresenterImpl> {
  private final Provider<VideoApi> apiProvider;

  public VideoPresenterImpl_Factory(Provider<VideoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public VideoPresenterImpl get() {
    return new VideoPresenterImpl(apiProvider.get());
  }

  public static VideoPresenterImpl_Factory create(Provider<VideoApi> apiProvider) {
    return new VideoPresenterImpl_Factory(apiProvider);
  }

  public static VideoPresenterImpl newVideoPresenterImpl(VideoApi api) {
    return new VideoPresenterImpl(api);
  }
}