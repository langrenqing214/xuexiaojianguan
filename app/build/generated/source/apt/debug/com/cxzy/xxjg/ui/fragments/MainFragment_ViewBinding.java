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

  private View view2131296320;

  private View view2131296420;

  private View view2131296342;

  private View view2131296345;

  private View view2131296348;

  private View view2131296343;

  private View view2131296344;

  private View view2131296341;

  private View view2131296346;

  private View view2131296347;

  private View view2131296437;

  private View view2131296435;

  @UiThread
  public MainFragment_ViewBinding(final MainFragment target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.button_test, "field 'btnTest' and method 'onViewClicked'");
    target.btnTest = Utils.castView(view, R.id.button_test, "field 'btnTest'", Button.class);
    view2131296320 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.dlMyMain = Utils.findRequiredViewAsType(source, R.id.dl_my_main, "field 'dlMyMain'", DrawerLayout.class);
    target.tvAlarm = Utils.findRequiredViewAsType(source, R.id.tv_alarm, "field 'tvAlarm'", TextView.class);
    target.tvWarning = Utils.findRequiredViewAsType(source, R.id.tv_warning, "field 'tvWarning'", TextView.class);
    target.tvDeal = Utils.findRequiredViewAsType(source, R.id.tv_deal, "field 'tvDeal'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_to_zxing, "method 'onViewClicked'");
    view2131296420 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_listorical_warning, "method 'onViewClicked'");
    view2131296342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_regulatory_information, "method 'onViewClicked'");
    view2131296345 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_video, "method 'onViewClicked'");
    view2131296348 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_menu, "method 'onViewClicked'");
    view2131296343 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_purchase, "method 'onViewClicked'");
    view2131296344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_health_examination, "method 'onViewClicked'");
    view2131296341 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_retention_manage, "method 'onViewClicked'");
    view2131296346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_trial_management, "method 'onViewClicked'");
    view2131296347 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_canteen, "method 'onViewClicked'");
    view2131296437 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_exit_login, "method 'onViewClicked'");
    view2131296435 = view;
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

    view2131296320.setOnClickListener(null);
    view2131296320 = null;
    view2131296420.setOnClickListener(null);
    view2131296420 = null;
    view2131296342.setOnClickListener(null);
    view2131296342 = null;
    view2131296345.setOnClickListener(null);
    view2131296345 = null;
    view2131296348.setOnClickListener(null);
    view2131296348 = null;
    view2131296343.setOnClickListener(null);
    view2131296343 = null;
    view2131296344.setOnClickListener(null);
    view2131296344 = null;
    view2131296341.setOnClickListener(null);
    view2131296341 = null;
    view2131296346.setOnClickListener(null);
    view2131296346 = null;
    view2131296347.setOnClickListener(null);
    view2131296347 = null;
    view2131296437.setOnClickListener(null);
    view2131296437 = null;
    view2131296435.setOnClickListener(null);
    view2131296435 = null;

    super.unbind();
  }
}
