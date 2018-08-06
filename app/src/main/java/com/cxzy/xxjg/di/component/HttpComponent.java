package com.cxzy.xxjg.di.component;

import com.cxzy.xxjg.MainActivity;
import com.cxzy.xxjg.ui.activitys.AddMenuActivity;
import com.cxzy.xxjg.ui.activitys.AddRetentionActivity;
import com.cxzy.xxjg.ui.activitys.AddTrialActivity;
import com.cxzy.xxjg.ui.activitys.HealthExaminationActivity;
import com.cxzy.xxjg.ui.activitys.ListoricalWarningActivity;
import com.cxzy.xxjg.ui.activitys.LoginActivity;
import com.cxzy.xxjg.ui.activitys.MenuActivity;
import com.cxzy.xxjg.ui.activitys.PurchaseActivity;
import com.cxzy.xxjg.ui.activitys.RetentionManageActivity;
import com.cxzy.xxjg.ui.activitys.ScanResultActivity;
import com.cxzy.xxjg.ui.activitys.TrialManagementActivity;
import com.cxzy.xxjg.ui.fragments.MainFragment;

import dagger.Component;

/**
 * Created by demo on 2018/6/27.
 */
@Component(dependencies = AppComponent.class)
public interface HttpComponent {
    void inject(MainFragment mainFragment);
    void inject(MainActivity mainActivity);
    void inject(PurchaseActivity purchaseActivity);
    void inject(LoginActivity loginActivity);
    void inject(TrialManagementActivity trialManagementActivity);
    void inject(MenuActivity menuActivity);
    void inject(AddMenuActivity addMenuActivity);
    void inject(AddRetentionActivity addRetentionActivity);
    void inject(RetentionManageActivity retentionManageActivity);
    void inject(ListoricalWarningActivity listoricalWarningActivity);
    void inject(AddTrialActivity addTrialActivity);
    void inject(HealthExaminationActivity examinationActivity);
    void inject(ScanResultActivity scanResultActivity);
}
