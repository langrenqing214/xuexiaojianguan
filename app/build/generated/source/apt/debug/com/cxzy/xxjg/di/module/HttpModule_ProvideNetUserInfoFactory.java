// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.di.module;

import com.cxzy.xxjg.net.MainFragmentApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class HttpModule_ProvideNetUserInfoFactory implements Factory<MainFragmentApi> {
  private final HttpModule module;

  private final Provider<OkHttpClient.Builder> builderProvider;

  public HttpModule_ProvideNetUserInfoFactory(
      HttpModule module, Provider<OkHttpClient.Builder> builderProvider) {
    this.module = module;
    this.builderProvider = builderProvider;
  }

  @Override
  public MainFragmentApi get() {
    return Preconditions.checkNotNull(
        module.provideNetUserInfo(builderProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static HttpModule_ProvideNetUserInfoFactory create(
      HttpModule module, Provider<OkHttpClient.Builder> builderProvider) {
    return new HttpModule_ProvideNetUserInfoFactory(module, builderProvider);
  }

  public static MainFragmentApi proxyProvideNetUserInfo(
      HttpModule instance, OkHttpClient.Builder builder) {
    return Preconditions.checkNotNull(
        instance.provideNetUserInfo(builder),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}