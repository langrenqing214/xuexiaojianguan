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
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListoricalWarningActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ListoricalWarningActivity target;

  private View view2131296432;

  private View view2131296439;

  @UiThread
  public ListoricalWarningActivity_ViewBinding(ListoricalWarningActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListoricalWarningActivity_ViewBinding(final ListoricalWarningActivity target,
      View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_canteen_select, "field 'llCanteenSelect' and method 'onViewClicked'");
    target.llCanteenSelect = Utils.castView(view, R.id.ll_canteen_select, "field 'llCanteenSelect'", LinearLayout.class);
    view2131296432 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCanteenShow = Utils.findRequiredViewAsType(source, R.id.tv_canteen_show, "field 'tvCanteenShow'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_time_select, "field 'llTimeSelect' and method 'onViewClicked'");
    target.llTimeSelect = Utils.castView(view, R.id.ll_time_select, "field 'llTimeSelect'", LinearLayout.class);
    view2131296439 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTimeShow = Utils.findRequiredViewAsType(source, R.id.tv_time_show, "field 'tvTimeShow'", TextView.class);
    target.rvWarning = Utils.findRequiredViewAsType(source, R.id.rv_warning, "field 'rvWarning'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    ListoricalWarningActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llCanteenSelect = null;
    target.tvCanteenShow = null;
    target.llTimeSelect = null;
    target.tvTimeShow = null;
    target.rvWarning = null;

    view2131296432.setOnClickListener(null);
    view2131296432 = null;
    view2131296439.setOnClickListener(null);
    view2131296439 = null;

    super.unbind();
  }
}
