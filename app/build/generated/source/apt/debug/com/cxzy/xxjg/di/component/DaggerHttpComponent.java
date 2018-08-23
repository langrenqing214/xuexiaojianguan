// Generated by Dagger (https://google.github.io/dagger).
package com.cxzy.xxjg.di.component;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.base.BaseActivity_MembersInjector;
import com.cxzy.xxjg.base.BaseFragment_MembersInjector;
import com.cxzy.xxjg.ui.activitys.AddMenuActivity;
import com.cxzy.xxjg.ui.activitys.AddRetentionActivity;
import com.cxzy.xxjg.ui.activitys.AddTrialActivity;
import com.cxzy.xxjg.ui.activitys.ChangePasswordActivity;
import com.cxzy.xxjg.ui.activitys.HealthExaminationActivity;
import com.cxzy.xxjg.ui.activitys.ListoricalWarningActivity;
import com.cxzy.xxjg.ui.activitys.LoginActivity;
import com.cxzy.xxjg.ui.activitys.MenuActivity;
import com.cxzy.xxjg.ui.activitys.PurchaseActivity;
import com.cxzy.xxjg.ui.activitys.RetentionManageActivity;
import com.cxzy.xxjg.ui.activitys.ScanResultActivity;
import com.cxzy.xxjg.ui.activitys.TrialManagementActivity;
import com.cxzy.xxjg.ui.activitys.VideoActivity;
import com.cxzy.xxjg.ui.fragments.MainFragment;
import com.cxzy.xxjg.ui.test.presenter.AddMenuPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.AddRetentionPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.AddTrialPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.AddTrialPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.ChangePasswordPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.ChangePasswordPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.HealthExaminationPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.HealthExaminationPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.LoginActivityPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.MainActivityPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.MainFragmentContractPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.MenuActivityPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.PurchaseActivityPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.RetentionPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.RetentionPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.ScanResultPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.ScanResultPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.TrialManagementPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.TrialManagementPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.VideoPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.VideoPresenterImpl_Factory;
import com.cxzy.xxjg.ui.test.presenter.WarningPresenterImpl;
import com.cxzy.xxjg.ui.test.presenter.WarningPresenterImpl_Factory;
import dagger.internal.Preconditions;

public final class DaggerHttpComponent implements HttpComponent {
  private AppComponent appComponent;

  private DaggerHttpComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  private MainFragmentContractPresenterImpl getMainFragmentContractPresenterImpl() {
    return new MainFragmentContractPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getUserInfoApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private PurchaseActivityPresenterImpl getPurchaseActivityPresenterImpl() {
    return new PurchaseActivityPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getPurchaseApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private LoginActivityPresenterImpl getLoginActivityPresenterImpl() {
    return new LoginActivityPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getNetLoginApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private TrialManagementPresenterImpl getTrialManagementPresenterImpl() {
    return TrialManagementPresenterImpl_Factory.newTrialManagementPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getTrialApi(), "Cannot return null from a non-@Nullable component method"),
        Preconditions.checkNotNull(
            appComponent.getAddTrialApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private MenuActivityPresenterImpl getMenuActivityPresenterImpl() {
    return new MenuActivityPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getMenuApi(), "Cannot return null from a non-@Nullable component method"));
  }

  private AddMenuPresenterImpl getAddMenuPresenterImpl() {
    return new AddMenuPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getAddMenuApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private AddRetentionPresenterImpl getAddRetentionPresenterImpl() {
    return new AddRetentionPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getAddRetentApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private RetentionPresenterImpl getRetentionPresenterImpl() {
    return RetentionPresenterImpl_Factory.newRetentionPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getRetentApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private WarningPresenterImpl getWarningPresenterImpl() {
    return WarningPresenterImpl_Factory.newWarningPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getWarningApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private AddTrialPresenterImpl getAddTrialPresenterImpl() {
    return AddTrialPresenterImpl_Factory.newAddTrialPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getAddTrialApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private HealthExaminationPresenterImpl getHealthExaminationPresenterImpl() {
    return HealthExaminationPresenterImpl_Factory.newHealthExaminationPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getHealthApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private ScanResultPresenterImpl getScanResultPresenterImpl() {
    return ScanResultPresenterImpl_Factory.newScanResultPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getScanResultApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private VideoPresenterImpl getVideoPresenterImpl() {
    return VideoPresenterImpl_Factory.newVideoPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getVideoApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  private ChangePasswordPresenterImpl getChangePasswordPresenterImpl() {
    return ChangePasswordPresenterImpl_Factory.newChangePasswordPresenterImpl(
        Preconditions.checkNotNull(
            appComponent.getChangePwdApi(),
            "Cannot return null from a non-@Nullable component method"));
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.appComponent = builder.appComponent;
  }

  @Override
  public void inject(MainFragment mainFragment) {
    injectMainFragment(mainFragment);
  }

  @Override
  public void inject(MainActivity mainActivity) {
    injectMainActivity(mainActivity);
  }

  @Override
  public void inject(PurchaseActivity purchaseActivity) {
    injectPurchaseActivity(purchaseActivity);
  }

  @Override
  public void inject(LoginActivity loginActivity) {
    injectLoginActivity(loginActivity);
  }

  @Override
  public void inject(TrialManagementActivity trialManagementActivity) {
    injectTrialManagementActivity(trialManagementActivity);
  }

  @Override
  public void inject(MenuActivity menuActivity) {
    injectMenuActivity(menuActivity);
  }

  @Override
  public void inject(AddMenuActivity addMenuActivity) {
    injectAddMenuActivity(addMenuActivity);
  }

  @Override
  public void inject(AddRetentionActivity addRetentionActivity) {
    injectAddRetentionActivity(addRetentionActivity);
  }

  @Override
  public void inject(RetentionManageActivity retentionManageActivity) {
    injectRetentionManageActivity(retentionManageActivity);
  }

  @Override
  public void inject(ListoricalWarningActivity listoricalWarningActivity) {
    injectListoricalWarningActivity(listoricalWarningActivity);
  }

  @Override
  public void inject(AddTrialActivity addTrialActivity) {
    injectAddTrialActivity(addTrialActivity);
  }

  @Override
  public void inject(HealthExaminationActivity examinationActivity) {
    injectHealthExaminationActivity(examinationActivity);
  }

  @Override
  public void inject(ScanResultActivity scanResultActivity) {
    injectScanResultActivity(scanResultActivity);
  }

  @Override
  public void inject(VideoActivity videoActivity) {
    injectVideoActivity(videoActivity);
  }

  @Override
  public void inject(ChangePasswordActivity changePasswordActivity) {
    injectChangePasswordActivity(changePasswordActivity);
  }

  private MainFragment injectMainFragment(MainFragment instance) {
    BaseFragment_MembersInjector.injectMPresenter(instance, getMainFragmentContractPresenterImpl());
    return instance;
  }

  private MainActivity injectMainActivity(MainActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(
        instance, MainActivityPresenterImpl_Factory.newMainActivityPresenterImpl());
    return instance;
  }

  private PurchaseActivity injectPurchaseActivity(PurchaseActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getPurchaseActivityPresenterImpl());
    return instance;
  }

  private LoginActivity injectLoginActivity(LoginActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getLoginActivityPresenterImpl());
    return instance;
  }

  private TrialManagementActivity injectTrialManagementActivity(TrialManagementActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getTrialManagementPresenterImpl());
    return instance;
  }

  private MenuActivity injectMenuActivity(MenuActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getMenuActivityPresenterImpl());
    return instance;
  }

  private AddMenuActivity injectAddMenuActivity(AddMenuActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getAddMenuPresenterImpl());
    return instance;
  }

  private AddRetentionActivity injectAddRetentionActivity(AddRetentionActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getAddRetentionPresenterImpl());
    return instance;
  }

  private RetentionManageActivity injectRetentionManageActivity(RetentionManageActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getRetentionPresenterImpl());
    return instance;
  }

  private ListoricalWarningActivity injectListoricalWarningActivity(
      ListoricalWarningActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getWarningPresenterImpl());
    return instance;
  }

  private AddTrialActivity injectAddTrialActivity(AddTrialActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getAddTrialPresenterImpl());
    return instance;
  }

  private HealthExaminationActivity injectHealthExaminationActivity(
      HealthExaminationActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getHealthExaminationPresenterImpl());
    return instance;
  }

  private ScanResultActivity injectScanResultActivity(ScanResultActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getScanResultPresenterImpl());
    return instance;
  }

  private VideoActivity injectVideoActivity(VideoActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getVideoPresenterImpl());
    return instance;
  }

  private ChangePasswordActivity injectChangePasswordActivity(ChangePasswordActivity instance) {
    BaseActivity_MembersInjector.injectMPresenter(instance, getChangePasswordPresenterImpl());
    return instance;
  }

  public static final class Builder {
    private AppComponent appComponent;

    private Builder() {}

    public HttpComponent build() {
      if (appComponent == null) {
        throw new IllegalStateException(AppComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerHttpComponent(this);
    }

    public Builder appComponent(AppComponent appComponent) {
      this.appComponent = Preconditions.checkNotNull(appComponent);
      return this;
    }
  }
}