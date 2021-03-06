// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.activitys;

import com.cxzy.xxjg.base.BaseActivity_MembersInjector;
import com.cxzy.xxjg.ui.test.presenter.AddRetentionPresenterImpl;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AddRetentionActivity_MembersInjector
    implements MembersInjector<AddRetentionActivity> {
  private final Provider<AddRetentionPresenterImpl> mPresenterProvider;

  public AddRetentionActivity_MembersInjector(
      Provider<AddRetentionPresenterImpl> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<AddRetentionActivity> create(
      Provider<AddRetentionPresenterImpl> mPresenterProvider) {
    return new AddRetentionActivity_MembersInjector(mPresenterProvider);
  }

  @Override
  public void injectMembers(AddRetentionActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, mPresenterProvider.get());
  }
}
