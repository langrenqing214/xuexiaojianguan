// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PurchaseActivity_ViewBinding extends BaseActivity_ViewBinding {
  private PurchaseActivity target;

  private View view2131296418;

  private View view2131296423;

  private View view2131296433;

  private View view2131296434;

  private View view2131296300;

  private View view2131296342;

  private View view2131296343;

  private View view2131296516;

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
    view = Utils.findRequiredView(source, R.id.et_food_style, "field 'etFoodStyle' and method 'onViewClicked'");
    target.etFoodStyle = Utils.castView(view, R.id.et_food_style, "field 'etFoodStyle'", TextView.class);
    view2131296418 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etFoodPrice = Utils.findRequiredViewAsType(source, R.id.et_food_price, "field 'etFoodPrice'", EditText.class);
    target.etFoodWeight = Utils.findRequiredViewAsType(source, R.id.et_food_weight, "field 'etFoodWeight'", EditText.class);
    target.etPurchaser = Utils.findRequiredViewAsType(source, R.id.et_purchaser, "field 'etPurchaser'", EditText.class);
    view = Utils.findRequiredView(source, R.id.et_manufacture_date, "field 'etManufactureDate' and method 'onViewClicked'");
    target.etManufactureDate = Utils.castView(view, R.id.et_manufacture_date, "field 'etManufactureDate'", TextView.class);
    view2131296423 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etShelfLife = Utils.findRequiredViewAsType(source, R.id.et_shelf_life, "field 'etShelfLife'", EditText.class);
    view = Utils.findRequiredView(source, R.id.et_shelf_life_end, "field 'etShelfLifeEnd' and method 'onViewClicked'");
    target.etShelfLifeEnd = Utils.castView(view, R.id.et_shelf_life_end, "field 'etShelfLifeEnd'", TextView.class);
    view2131296433 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.et_suppliers, "field 'etSuppliers' and method 'onViewClicked'");
    target.etSuppliers = Utils.castView(view, R.id.et_suppliers, "field 'etSuppliers'", TextView.class);
    view2131296434 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.main_title_id, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_warehousing, "method 'onViewClicked'");
    view2131296342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_warehousing_and_out_treasury, "method 'onViewClicked'");
    view2131296343 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_select_canteen, "method 'onViewClicked'");
    view2131296516 = view;
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
    target.tvTitle = null;

    view2131296418.setOnClickListener(null);
    view2131296418 = null;
    view2131296423.setOnClickListener(null);
    view2131296423 = null;
    view2131296433.setOnClickListener(null);
    view2131296433 = null;
    view2131296434.setOnClickListener(null);
    view2131296434 = null;
    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296342.setOnClickListener(null);
    view2131296342 = null;
    view2131296343.setOnClickListener(null);
    view2131296343 = null;
    view2131296516.setOnClickListener(null);
    view2131296516 = null;

    super.unbind();
  }
}
