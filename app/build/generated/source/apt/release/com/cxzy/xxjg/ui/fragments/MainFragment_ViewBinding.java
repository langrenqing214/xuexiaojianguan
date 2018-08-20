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

  private View view2131296346;

  private View view2131296692;

  private View view2131296754;

  private View view2131296706;

  private View view2131296486;

  private View view2131296381;

  private View view2131296384;

  private View view2131296387;

  private View view2131296382;

  private View view2131296383;

  private View view2131296380;

  private View view2131296385;

  private View view2131296386;

  private View view2131296513;

  private View view2131296511;

  private View view2131296499;

  private View view2131296521;

  private View view2131296506;

  @UiThread
  public MainFragment_ViewBinding(final MainFragment target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.button_test, "field 'btnTest' and method 'onViewClicked'");
    target.btnTest = Utils.castView(view, R.id.button_test, "field 'btnTest'", Button.class);
    view2131296346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.dlMyMain = Utils.findRequiredViewAsType(source, R.id.dl_my_main, "field 'dlMyMain'", DrawerLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_alarm, "field 'tvAlarm' and method 'onViewClicked'");
    target.tvAlarm = Utils.castView(view, R.id.tv_alarm, "field 'tvAlarm'", TextView.class);
    view2131296692 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_warning, "field 'tvWarning' and method 'onViewClicked'");
    target.tvWarning = Utils.castView(view, R.id.tv_warning, "field 'tvWarning'", TextView.class);
    view2131296754 = view;
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
    target.civAlarm = Utils.findRequiredViewAsType(source, R.id.civ_alarm, "field 'civAlarm'", CircleImageView.class);
    target.civWarning = Utils.findRequiredViewAsType(source, R.id.civ_warning, "field 'civWarning'", CircleImageView.class);
    target.civDeal = Utils.findRequiredViewAsType(source, R.id.civ_deal, "field 'civDeal'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_to_zxing, "method 'onViewClicked'");
    view2131296486 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_listorical_warning, "method 'onViewClicked'");
    view2131296381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_regulatory_information, "method 'onViewClicked'");
    view2131296384 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_video, "method 'onViewClicked'");
    view2131296387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_menu, "method 'onViewClicked'");
    view2131296382 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_purchase, "method 'onViewClicked'");
    view2131296383 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_health_examination, "method 'onViewClicked'");
    view2131296380 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_retention_manage, "method 'onViewClicked'");
    view2131296385 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cv_trial_management, "method 'onViewClicked'");
    view2131296386 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_canteen, "method 'onViewClicked'");
    view2131296513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_exit_login, "method 'onViewClicked'");
    view2131296511 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_about_us, "method 'onViewClicked'");
    view2131296499 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_user_protocol, "method 'onViewClicked'");
    view2131296521 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_password, "method 'onViewClicked'");
    view2131296506 = view;
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

    view2131296346.setOnClickListener(null);
    view2131296346 = null;
    view2131296692.setOnClickListener(null);
    view2131296692 = null;
    view2131296754.setOnClickListener(null);
    view2131296754 = null;
    view2131296706.setOnClickListener(null);
    view2131296706 = null;
    view2131296486.setOnClickListener(null);
    view2131296486 = null;
    view2131296381.setOnClickListener(null);
    view2131296381 = null;
    view2131296384.setOnClickListener(null);
    view2131296384 = null;
    view2131296387.setOnClickListener(null);
    view2131296387 = null;
    view2131296382.setOnClickListener(null);
    view2131296382 = null;
    view2131296383.setOnClickListener(null);
    view2131296383 = null;
    view2131296380.setOnClickListener(null);
    view2131296380 = null;
    view2131296385.setOnClickListener(null);
    view2131296385 = null;
    view2131296386.setOnClickListener(null);
    view2131296386 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
    view2131296511.setOnClickListener(null);
    view2131296511 = null;
    view2131296499.setOnClickListener(null);
    view2131296499 = null;
    view2131296521.setOnClickListener(null);
    view2131296521 = null;
    view2131296506.setOnClickListener(null);
    view2131296506 = null;

    super.unbind();
  }
}
