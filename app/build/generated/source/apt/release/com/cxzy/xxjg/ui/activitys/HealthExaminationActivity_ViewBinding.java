// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HealthExaminationActivity_ViewBinding extends BaseActivity_ViewBinding {
  private HealthExaminationActivity target;

  private View view2131296516;

  private View view2131296512;

  private View view2131296300;

  private View view2131296518;

  private View view2131296327;

  private View view2131296338;

  private View view2131296325;

  private View view2131296337;

  @UiThread
  public HealthExaminationActivity_ViewBinding(HealthExaminationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HealthExaminationActivity_ViewBinding(final HealthExaminationActivity target,
      View source) {
    super(target, source);

    this.target = target;

    View view;
    target.rgCheck = Utils.findRequiredViewAsType(source, R.id.rg_check, "field 'rgCheck'", RadioGroup.class);
    target.tvCheckDes = Utils.findRequiredViewAsType(source, R.id.tv_check_des, "field 'tvCheckDes'", TextView.class);
    target.lvCheckItems = Utils.findRequiredViewAsType(source, R.id.lv_check_items, "field 'lvCheckItems'", ListView.class);
    target.llMorningCheck = Utils.findRequiredViewAsType(source, R.id.ll_morning_check, "field 'llMorningCheck'", LinearLayout.class);
    target.rvThroughPerson = Utils.findRequiredViewAsType(source, R.id.rv_through_person, "field 'rvThroughPerson'", RecyclerView.class);
    target.rvNoThroughPerson = Utils.findRequiredViewAsType(source, R.id.rv_no_through_person, "field 'rvNoThroughPerson'", RecyclerView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.main_title_id, "field 'tvTitle'", TextView.class);
    target.tvCheckName = Utils.findRequiredViewAsType(source, R.id.tv_check_name, "field 'tvCheckName'", TextView.class);
    target.llEnvironmentalCheck = Utils.findRequiredViewAsType(source, R.id.ll_environmental_check, "field 'llEnvironmentalCheck'", LinearLayout.class);
    target.rvAddPic = Utils.findRequiredViewAsType(source, R.id.rv_add_pic, "field 'rvAddPic'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ll_nomor, "field 'llNomor' and method 'onViewClicked'");
    target.llNomor = Utils.castView(view, R.id.ll_nomor, "field 'llNomor'", LinearLayout.class);
    view2131296516 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivNormal = Utils.findRequiredViewAsType(source, R.id.iv_normal, "field 'ivNormal'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_error, "field 'llError' and method 'onViewClicked'");
    target.llError = Utils.castView(view, R.id.ll_error, "field 'llError'", LinearLayout.class);
    view2131296512 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivError = Utils.findRequiredViewAsType(source, R.id.iv_error, "field 'ivError'", ImageView.class);
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
    view = Utils.findRequiredView(source, R.id.btn_add_through_person, "method 'onViewClicked'");
    view2131296327 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_save_morningcheck, "method 'onViewClicked'");
    view2131296338 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_add_no_through_person, "method 'onViewClicked'");
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_save_environmental_check, "method 'onViewClicked'");
    view2131296337 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    HealthExaminationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rgCheck = null;
    target.tvCheckDes = null;
    target.lvCheckItems = null;
    target.llMorningCheck = null;
    target.rvThroughPerson = null;
    target.rvNoThroughPerson = null;
    target.tvTitle = null;
    target.tvCheckName = null;
    target.llEnvironmentalCheck = null;
    target.rvAddPic = null;
    target.llNomor = null;
    target.ivNormal = null;
    target.llError = null;
    target.ivError = null;

    view2131296516.setOnClickListener(null);
    view2131296516 = null;
    view2131296512.setOnClickListener(null);
    view2131296512 = null;
    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296518.setOnClickListener(null);
    view2131296518 = null;
    view2131296327.setOnClickListener(null);
    view2131296327 = null;
    view2131296338.setOnClickListener(null);
    view2131296338 = null;
    view2131296325.setOnClickListener(null);
    view2131296325 = null;
    view2131296337.setOnClickListener(null);
    view2131296337 = null;

    super.unbind();
  }
}
