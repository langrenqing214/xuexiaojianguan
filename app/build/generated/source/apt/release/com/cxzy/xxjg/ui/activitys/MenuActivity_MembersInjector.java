// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.ui.activitys;

import com.cxzy.xxjg.base.BaseActivity_MembersInjector;
import com.cxzy.xxjg.ui.test.presenter.MenuActivityPresenterImpl;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MenuActivity_MembersInjector implements MembersInjector<MenuActivity> {
  private final Provider<MenuActivityPresenterImpl> mPresenterProvider;

  public MenuActivity_MembersInjector(Provider<MenuActivityPresenterImpl> mPresenterProvider) {
    this.mPresenterProvider = mPresenterProvider;
  }

  public static MembersInjector<MenuActivity> create(
      Provider<MenuActivityPresenterImpl> mPresenterProvider) {
    return new MenuActivity_MembersInjector(mPresenterProvider);
  }

  @Override
  public void injectMembers(MenuActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, mPresenterProvider.get());
  }
}
