// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.di.module;

import com.cxzy.xxjg.net.TrialManagementApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class HttpModule_ProvideNetTrialListFactory implements Factory<TrialManagementApi> {
  private final HttpModule module;

  private final Provider<OkHttpClient.Builder> builderProvider;

  public HttpModule_ProvideNetTrialListFactory(
      HttpModule module, Provider<OkHttpClient.Builder> builderProvider) {
    this.module = module;
    this.builderProvider = builderProvider;
  }

  @Override
  public TrialManagementApi get() {
    return Preconditions.checkNotNull(
        module.provideNetTrialList(builderProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static HttpModule_ProvideNetTrialListFactory create(
      HttpModule module, Provider<OkHttpClient.Builder> builderProvider) {
    return new HttpModule_ProvideNetTrialListFactory(module, builderProvider);
  }

  public static TrialManagementApi proxyProvideNetTrialList(
      HttpModule instance, OkHttpClient.Builder builder) {
    return Preconditions.checkNotNull(
        instance.provideNetTrialList(builder),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
