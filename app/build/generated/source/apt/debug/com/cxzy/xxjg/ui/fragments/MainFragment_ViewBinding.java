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
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding extends BaseFragment_ViewBinding {
  private MainFragment target;

  private View view2131296352;

  private View view2131296694;

  private View view2131296752;

  private View view2131296706;

  private View view2131296488;

  private View view2131296384;

  private View view2131296387;

  private View view2131296390;

  private View view2131296385;

  private View view2131296386;

  private View view2131296383;

  private View view2131296388;

  private View view2131296389;

  private View view2131296515;

  private View view2131296513;

  private View view2131296501;

  private View view2131296523;

  private View view2131296508;

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
    view2131296694 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_warning, "field 'tvWarning' and method 'onViewClicked'");
    target.tvWarning = Utils.castView(view, R.id.tv_warning, "field 'tvWarning'", TextView.class);
    view2131296752 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_deal, "field 'tvDeal' and method 'onViewClicked'");
    target.tvDeal = Utils.castView(view, R.id.tv_deal, "field 'tvDeal'", TextView.class);
    view2131296706 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvUserName = Utils.findRequiredViewAsType(source, R.id.tv_user_name, "field 'tvUserName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_to_zxing, "method 'onViewClicked'");
    view2131296488 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_listorical_warning, "method 'onViewClicked'");
    view2131296384 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_regulatory_information, "method 'onViewClicked'");
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_video, "method 'onViewClicked'");
    view2131296390 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_menu, "method 'onViewClicked'");
    view2131296385 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_purchase, "method 'onViewClicked'");
    view2131296386 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_health_examination, "method 'onViewClicked'");
    view2131296383 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_retention_manage, "method 'onViewClicked'");
    view2131296388 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_trial_management, "method 'onViewClicked'");
    view2131296389 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_canteen, "method 'onViewClicked'");
    view2131296515 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_exit_login, "method 'onViewClicked'");
    view2131296513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about_us, "method 'onViewClicked'");
    view2131296501 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_user_protocol, "method 'onViewClicked'");
    view2131296523 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_password, "method 'onViewClicked'");
    view2131296508 = view;
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

    view2131296352.setOnClickListener(null);
    view2131296352 = null;
    view2131296694.setOnClickListener(null);
    view2131296694 = null;
    view2131296752.setOnClickListener(null);
    view2131296752 = null;
    view2131296706.setOnClickListener(null);
    view2131296706 = null;
    view2131296488.setOnClickListener(null);
    view2131296488 = null;
    view2131296384.setOnClickListener(null);
    view2131296384 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
    view2131296390.setOnClickListener(null);
    view2131296390 = null;
    view2131296385.setOnClickListener(null);
    view2131296385 = null;
    view2131296386.setOnClickListener(null);
    view2131296386 = null;
    view2131296383.setOnClickListener(null);
    view2131296383 = null;
    view2131296388.setOnClickListener(null);
    view2131296388 = null;
    view2131296389.setOnClickListener(null);
    view2131296389 = null;
    view2131296515.setOnClickListener(null);
    view2131296515 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
    view2131296501.setOnClickListener(null);
    view2131296501 = null;
    view2131296523.setOnClickListener(null);
    view2131296523 = null;
    view2131296508.setOnClickListener(null);
    view2131296508 = null;

    super.unbind();
  }
}