// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrialManagementActivity_ViewBinding extends BaseActivity_ViewBinding {
  private TrialManagementActivity target;

  private View view2131296507;

  private View view2131296521;

  private View view2131296306;

  private View view2131296505;

  @UiThread
  public TrialManagementActivity_ViewBinding(TrialManagementActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrialManagementActivity_ViewBinding(final TrialManagementActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.rvTrial = Utils.findRequiredViewAsType(source, R.id.rv_trial_management, "field 'rvTrial'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_canteen_select, "field 'llCanteenSelect' and method 'onViewClicked'");
    target.llCanteenSelect = Utils.castView(view, R.id.ll_canteen_select, "field 'llCanteenSelect'", LinearLayout.class);
    view2131296507 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCanteenShow = Utils.findRequiredViewAsType(source, R.id.tv_canteen_show, "field 'tvCanteenShow'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_time_select, "field 'llTimeSelect' and method 'onViewClicked'");
    target.llTimeSelect = Utils.castView(view, R.id.ll_time_select, "field 'llTimeSelect'", LinearLayout.class);
    view2131296521 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTimeShow = Utils.findRequiredViewAsType(source, R.id.tv_time_show, "field 'tvTimeShow'", TextView.class);
    target.srlTrial = Utils.findRequiredViewAsType(source, R.id.srl_trial, "field 'srlTrial'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_add_trial, "method 'onViewClicked'");
    view2131296505 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    TrialManagementActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvTrial = null;
    target.llCanteenSelect = null;
    target.tvCanteenShow = null;
    target.llTimeSelect = null;
    target.tvTimeShow = null;
    target.srlTrial = null;

    view2131296507.setOnClickListener(null);
    view2131296507 = null;
    view2131296521.setOnClickListener(null);
    view2131296521 = null;
    view2131296306.setOnClickListener(null);
    view2131296306 = null;
    view2131296505.setOnClickListener(null);
    view2131296505 = null;

    super.unbind();
  }
}
