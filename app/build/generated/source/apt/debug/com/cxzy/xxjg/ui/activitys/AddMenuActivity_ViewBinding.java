// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddMenuActivity_ViewBinding extends BaseActivity_ViewBinding {
  private AddMenuActivity target;

  private View view2131296727;

  private View view2131296730;

  private View view2131296330;

  private View view2131296306;

  @UiThread
  public AddMenuActivity_ViewBinding(AddMenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddMenuActivity_ViewBinding(final AddMenuActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_select_canteen, "field 'tvCanteen' and method 'onViewClicked'");
    target.tvCanteen = Utils.castView(view, R.id.tv_select_canteen, "field 'tvCanteen'", TextView.class);
    view2131296727 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_select_time, "field 'tvTime' and method 'onViewClicked'");
    target.tvTime = Utils.castView(view, R.id.tv_select_time, "field 'tvTime'", TextView.class);
    view2131296730 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etBreakfast = Utils.findRequiredViewAsType(source, R.id.et_breakfast, "field 'etBreakfast'", EditText.class);
    target.etLunch = Utils.findRequiredViewAsType(source, R.id.et_lunch, "field 'etLunch'", EditText.class);
    target.etDinner = Utils.findRequiredViewAsType(source, R.id.et_dinner, "field 'etDinner'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_add_menu, "method 'onViewClicked'");
    view2131296330 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    AddMenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCanteen = null;
    target.tvTime = null;
    target.etBreakfast = null;
    target.etLunch = null;
    target.etDinner = null;

    view2131296727.setOnClickListener(null);
    view2131296727 = null;
    view2131296730.setOnClickListener(null);
    view2131296730 = null;
    view2131296330.setOnClickListener(null);
    view2131296330 = null;
    view2131296306.setOnClickListener(null);
    view2131296306 = null;

    super.unbind();
  }
}
