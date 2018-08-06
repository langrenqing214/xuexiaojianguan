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

public class AddRetentionActivity_ViewBinding extends BaseActivity_ViewBinding {
  private AddRetentionActivity target;

  private View view2131296607;

  private View view2131296577;

  private View view2131296609;

  private View view2131296298;

  private View view2131296307;

  @UiThread
  public AddRetentionActivity_ViewBinding(AddRetentionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddRetentionActivity_ViewBinding(final AddRetentionActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_select_canteen, "field 'tvSelectCanteen' and method 'onViewClicked'");
    target.tvSelectCanteen = Utils.castView(view, R.id.tv_select_canteen, "field 'tvSelectCanteen'", TextView.class);
    view2131296607 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etFoodName = Utils.findRequiredViewAsType(source, R.id.et_put_food_name, "field 'etFoodName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_add_retention_date, "field 'tvRetentionDate' and method 'onViewClicked'");
    target.tvRetentionDate = Utils.castView(view, R.id.tv_add_retention_date, "field 'tvRetentionDate'", TextView.class);
    view2131296577 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvRetentionPerson = Utils.findRequiredViewAsType(source, R.id.tv_add_retention_person, "field 'tvRetentionPerson'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_select_expiry_time, "field 'tvExpiryTime' and method 'onViewClicked'");
    target.tvExpiryTime = Utils.castView(view, R.id.tv_select_expiry_time, "field 'tvExpiryTime'", TextView.class);
    view2131296609 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_add_retention, "method 'onViewClicked'");
    view2131296307 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    AddRetentionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvSelectCanteen = null;
    target.etFoodName = null;
    target.tvRetentionDate = null;
    target.tvRetentionPerson = null;
    target.tvExpiryTime = null;

    view2131296607.setOnClickListener(null);
    view2131296607 = null;
    view2131296577.setOnClickListener(null);
    view2131296577 = null;
    view2131296609.setOnClickListener(null);
    view2131296609 = null;
    view2131296298.setOnClickListener(null);
    view2131296298 = null;
    view2131296307.setOnClickListener(null);
    view2131296307 = null;

    super.unbind();
  }
}
