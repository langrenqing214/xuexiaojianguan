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

  private View view2131296742;

  private View view2131296698;

  private View view2131296744;

  private View view2131296306;

  private View view2131296332;

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
    view2131296742 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etFoodName = Utils.findRequiredViewAsType(source, R.id.et_put_food_name, "field 'etFoodName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_add_retention_date, "field 'tvRetentionDate' and method 'onViewClicked'");
    target.tvRetentionDate = Utils.castView(view, R.id.tv_add_retention_date, "field 'tvRetentionDate'", TextView.class);
    view2131296698 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvRetentionPerson = Utils.findRequiredViewAsType(source, R.id.tv_add_retention_person, "field 'tvRetentionPerson'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_select_expiry_time, "field 'tvExpiryTime' and method 'onViewClicked'");
    target.tvExpiryTime = Utils.castView(view, R.id.tv_select_expiry_time, "field 'tvExpiryTime'", TextView.class);
    view2131296744 = view;
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
    view = Utils.findRequiredView(source, R.id.btn_add_retention, "method 'onViewClicked'");
    view2131296332 = view;
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

    view2131296742.setOnClickListener(null);
    view2131296742 = null;
    view2131296698.setOnClickListener(null);
    view2131296698 = null;
    view2131296744.setOnClickListener(null);
    view2131296744 = null;
    view2131296306.setOnClickListener(null);
    view2131296306 = null;
    view2131296332.setOnClickListener(null);
    view2131296332 = null;

    super.unbind();
  }
}
