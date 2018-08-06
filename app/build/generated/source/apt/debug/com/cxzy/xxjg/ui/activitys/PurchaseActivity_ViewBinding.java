// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PurchaseActivity_ViewBinding extends BaseActivity_ViewBinding {
  private PurchaseActivity target;

  private View view2131296298;

  private View view2131296317;

  private View view2131296318;

  @UiThread
  public PurchaseActivity_ViewBinding(PurchaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PurchaseActivity_ViewBinding(final PurchaseActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.rvAddPic = Utils.findRequiredViewAsType(source, R.id.rv_add_pic, "field 'rvAddPic'", RecyclerView.class);
    target.etFoodName = Utils.findRequiredViewAsType(source, R.id.et_food_name, "field 'etFoodName'", EditText.class);
    target.etFoodStyle = Utils.findRequiredViewAsType(source, R.id.et_food_style, "field 'etFoodStyle'", EditText.class);
    target.etFoodPrice = Utils.findRequiredViewAsType(source, R.id.et_food_price, "field 'etFoodPrice'", EditText.class);
    target.etFoodWeight = Utils.findRequiredViewAsType(source, R.id.et_food_weight, "field 'etFoodWeight'", EditText.class);
    target.etPurchaser = Utils.findRequiredViewAsType(source, R.id.et_purchaser, "field 'etPurchaser'", EditText.class);
    target.etManufactureDate = Utils.findRequiredViewAsType(source, R.id.et_manufacture_date, "field 'etManufactureDate'", EditText.class);
    target.etShelfLife = Utils.findRequiredViewAsType(source, R.id.et_shelf_life, "field 'etShelfLife'", EditText.class);
    target.etShelfLifeEnd = Utils.findRequiredViewAsType(source, R.id.et_shelf_life_end, "field 'etShelfLifeEnd'", EditText.class);
    target.etSuppliers = Utils.findRequiredViewAsType(source, R.id.et_suppliers, "field 'etSuppliers'", EditText.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_warehousing, "method 'onViewClicked'");
    view2131296317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_warehousing_and_out_treasury, "method 'onViewClicked'");
    view2131296318 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    PurchaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvAddPic = null;
    target.etFoodName = null;
    target.etFoodStyle = null;
    target.etFoodPrice = null;
    target.etFoodWeight = null;
    target.etPurchaser = null;
    target.etManufactureDate = null;
    target.etShelfLife = null;
    target.etShelfLifeEnd = null;
    target.etSuppliers = null;

    view2131296298.setOnClickListener(null);
    view2131296298 = null;
    view2131296317.setOnClickListener(null);
    view2131296317 = null;
    view2131296318.setOnClickListener(null);
    view2131296318 = null;

    super.unbind();
  }
}
