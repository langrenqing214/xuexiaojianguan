// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.fragments;

import android.support.annotation.UiThread;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseFragment_ViewBinding;
import com.cxzy.xxjg.wideget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding extends BaseFragment_ViewBinding {
  private MainFragment target;

  private View view2131296352;

  private View view2131296698;

  private View view2131296758;

  private View view2131296710;

  private View view2131296492;

  private View view2131296387;

  private View view2131296390;

  private View view2131296393;

  private View view2131296388;

  private View view2131296389;

  private View view2131296386;

  private View view2131296391;

  private View view2131296392;

  private View view2131296519;

  private View view2131296517;

  private View view2131296505;

  private View view2131296527;

  private View view2131296512;

  @UiThread
  public MainFragment_ViewBinding(final MainFragment target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.button_test, "field 'btnTest' and method 'onViewClicked'");
    target.btnTest = Utils.castView(view, R.id.button_test, "field 'btnTest'", Button.class);
    view2131296352 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.dlMyMain = Utils.findRequiredViewAsType(source, R.id.dl_my_main, "field 'dlMyMain'", DrawerLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_alarm, "field 'tvAlarm' and method 'onViewClicked'");
    target.tvAlarm = Utils.castView(view, R.id.tv_alarm, "field 'tvAlarm'", TextView.class);
    view2131296698 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_warning, "field 'tvWarning' and method 'onViewClicked'");
    target.tvWarning = Utils.castView(view, R.id.tv_warning, "field 'tvWarning'", TextView.class);
    view2131296758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_deal, "field 'tvDeal' and method 'onViewClicked'");
    target.tvDeal = Utils.castView(view, R.id.tv_deal, "field 'tvDeal'", TextView.class);
    view2131296710 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvUserName = Utils.findRequiredViewAsType(source, R.id.tv_user_name, "field 'tvUserName'", TextView.class);
    target.civAlarm = Utils.findRequiredViewAsType(source, R.id.civ_alarm, "field 'civAlarm'", CircleImageView.class);
    target.civWarning = Utils.findRequiredViewAsType(source, R.id.civ_warning, "field 'civWarning'", CircleImageView.class);
    target.civDeal = Utils.findRequiredViewAsType(source, R.id.civ_deal, "field 'civDeal'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_to_zxing, "method 'onViewClicked'");
    view2131296492 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_listorical_warning, "method 'onViewClicked'");
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_regulatory_information, "method 'onViewClicked'");
    view2131296390 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_video, "method 'onViewClicked'");
    view2131296393 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_menu, "method 'onViewClicked'");
    view2131296388 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_purchase, "method 'onViewClicked'");
    view2131296389 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_health_examination, "method 'onViewClicked'");
    view2131296386 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_retention_manage, "method 'onViewClicked'");
    view2131296391 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_trial_management, "method 'onViewClicked'");
    view2131296392 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_canteen, "method 'onViewClicked'");
    view2131296519 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_exit_login, "method 'onViewClicked'");
    view2131296517 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about_us, "method 'onViewClicked'");
    view2131296505 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_user_protocol, "method 'onViewClicked'");
    view2131296527 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_password, "method 'onViewClicked'");
    view2131296512 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnTest = null;
    target.dlMyMain = null;
    target.tvAlarm = null;
    target.tvWarning = null;
    target.tvDeal = null;
    target.tvUserName = null;
    target.civAlarm = null;
    target.civWarning = null;
    target.civDeal = null;

    view2131296352.setOnClickListener(null);
    view2131296352 = null;
    view2131296698.setOnClickListener(null);
    view2131296698 = null;
    view2131296758.setOnClickListener(null);
    view2131296758 = null;
    view2131296710.setOnClickListener(null);
    view2131296710 = null;
    view2131296492.setOnClickListener(null);
    view2131296492 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
    view2131296390.setOnClickListener(null);
    view2131296390 = null;
    view2131296393.setOnClickListener(null);
    view2131296393 = null;
    view2131296388.setOnClickListener(null);
    view2131296388 = null;
    view2131296389.setOnClickListener(null);
    view2131296389 = null;
    view2131296386.setOnClickListener(null);
    view2131296386 = null;
    view2131296391.setOnClickListener(null);
    view2131296391 = null;
    view2131296392.setOnClickListener(null);
    view2131296392 = null;
    view2131296519.setOnClickListener(null);
    view2131296519 = null;
    view2131296517.setOnClickListener(null);
    view2131296517 = null;
    view2131296505.setOnClickListener(null);
    view2131296505 = null;
    view2131296527.setOnClickListener(null);
    view2131296527 = null;
    view2131296512.setOnClickListener(null);
    view2131296512 = null;

    super.unbind();
  }
}
