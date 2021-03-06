// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.di.module;

import com.cxzy.xxjg.net.AddRetentionApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class HttpModule_ProvideNetAddRetentionFactory implements Factory<AddRetentionApi> {
  private final HttpModule module;

  private final Provider<OkHttpClient.Builder> builderProvider;

  public HttpModule_ProvideNetAddRetentionFactory(
      HttpModule module, Provider<OkHttpClient.Builder> builderProvider) {
    this.module = module;
    this.builderProvider = builderProvider;
  }

  @Override
  public AddRetentionApi get() {
    return Preconditions.checkNotNull(
        module.provideNetAddRetention(builderProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static HttpModule_ProvideNetAddRetentionFactory create(
      HttpModule module, Provider<OkHttpClient.Builder> builderProvider) {
    return new HttpModule_ProvideNetAddRetentionFactory(module, builderProvider);
  }

  public static AddRetentionApi proxyProvideNetAddRetention(
      HttpModule instance, OkHttpClient.Builder builder) {
    return Preconditions.checkNotNull(
        instance.provideNetAddRetention(builder),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
