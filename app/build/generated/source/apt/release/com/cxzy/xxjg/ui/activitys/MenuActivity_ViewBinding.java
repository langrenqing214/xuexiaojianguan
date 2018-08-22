// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MenuActivity_ViewBinding extends BaseActivity_ViewBinding {
  private MenuActivity target;

  private View view2131296300;

  private View view2131296518;

  private View view2131296502;

  @UiThread
  public MenuActivity_ViewBinding(MenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MenuActivity_ViewBinding(final MenuActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.rvMenu = Utils.findRequiredViewAsType(source, R.id.rv_canteen_menu, "field 'rvMenu'", RecyclerView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.main_title_id, "field 'tvTitle'", TextView.class);
    target.srlMenu = Utils.findRequiredViewAsType(source, R.id.srl_menu, "field 'srlMenu'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_select_canteen, "method 'onViewClicked'");
    view2131296518 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_add_menu, "method 'onViewClicked'");
    view2131296502 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    MenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvMenu = null;
    target.tvTitle = null;
    target.srlMenu = null;

    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296518.setOnClickListener(null);
    view2131296518 = null;
    view2131296502.setOnClickListener(null);
    view2131296502 = null;

    super.unbind();
  }
}
