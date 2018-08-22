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

public class RetentionManageActivity_ViewBinding extends BaseActivity_ViewBinding {
  private RetentionManageActivity target;

  private View view2131296507;

  private View view2131296521;

  private View view2131296300;

  private View view2131296504;

  @UiThread
  public RetentionManageActivity_ViewBinding(RetentionManageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetentionManageActivity_ViewBinding(final RetentionManageActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.rvRetention = Utils.findRequiredViewAsType(source, R.id.rv_retention, "field 'rvRetention'", RecyclerView.class);
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
    target.srlRetention = Utils.findRequiredViewAsType(source, R.id.srl_retention, "field 'srlRetention'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_add_retention, "method 'onViewClicked'");
    view2131296504 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    RetentionManageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvRetention = null;
    target.llCanteenSelect = null;
    target.tvCanteenShow = null;
    target.llTimeSelect = null;
    target.tvTimeShow = null;
    target.srlRetention = null;

    view2131296507.setOnClickListener(null);
    view2131296507 = null;
    view2131296521.setOnClickListener(null);
    view2131296521 = null;
    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296504.setOnClickListener(null);
    view2131296504 = null;

    super.unbind();
  }
}
