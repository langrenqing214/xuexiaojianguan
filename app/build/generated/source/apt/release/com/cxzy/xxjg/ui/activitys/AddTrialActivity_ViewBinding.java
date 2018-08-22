// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddTrialActivity_ViewBinding extends BaseActivity_ViewBinding {
  private AddTrialActivity target;

  private View view2131296736;

  private View view2131296751;

  private View view2131296438;

  private View view2131296740;

  private View view2131296476;

  private View view2131296725;

  private View view2131296300;

  private View view2131296328;

  @UiThread
  public AddTrialActivity_ViewBinding(AddTrialActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddTrialActivity_ViewBinding(final AddTrialActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_select_canteen, "field 'tvSelectCanteen' and method 'onViewClicked'");
    target.tvSelectCanteen = Utils.castView(view, R.id.tv_select_canteen, "field 'tvSelectCanteen'", TextView.class);
    view2131296736 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etFoodName = Utils.findRequiredViewAsType(source, R.id.et_put_food_name, "field 'etFoodName'", EditText.class);
    target.etTrialPerson = Utils.findRequiredViewAsType(source, R.id.et_trial_person, "field 'etTrialPerson'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_trial_time, "field 'tvTrialTime' and method 'onViewClicked'");
    target.tvTrialTime = Utils.castView(view, R.id.tv_trial_time, "field 'tvTrialTime'", TextView.class);
    view2131296751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.et_trial_time_interval, "field 'etTimeInterval' and method 'onViewClicked'");
    target.etTimeInterval = Utils.castView(view, R.id.et_trial_time_interval, "field 'etTimeInterval'", TextView.class);
    view2131296438 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_select_trial_reaction, "field 'tvSelectTrialReaction' and method 'onViewClicked'");
    target.tvSelectTrialReaction = Utils.castView(view, R.id.tv_select_trial_reaction, "field 'tvSelectTrialReaction'", TextView.class);
    view2131296740 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etTrialDes = Utils.findRequiredViewAsType(source, R.id.et_trial_des, "field 'etTrialDes'", EditText.class);
    view = Utils.findRequiredView(source, R.id.iv_add_trial_pic, "field 'ivAddTrialPic' and method 'onViewClicked'");
    target.ivAddTrialPic = Utils.castView(view, R.id.iv_add_trial_pic, "field 'ivAddTrialPic'", ImageView.class);
    view2131296476 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reaction_time, "field 'tvReactionTime' and method 'onViewClicked'");
    target.tvReactionTime = Utils.castView(view, R.id.tv_reaction_time, "field 'tvReactionTime'", TextView.class);
    view2131296725 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivAddPicLabel = Utils.findRequiredViewAsType(source, R.id.iv_add_pic, "field 'ivAddPicLabel'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_add_trial, "method 'onViewClicked'");
    view2131296328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    AddTrialActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvSelectCanteen = null;
    target.etFoodName = null;
    target.etTrialPerson = null;
    target.tvTrialTime = null;
    target.etTimeInterval = null;
    target.tvSelectTrialReaction = null;
    target.etTrialDes = null;
    target.ivAddTrialPic = null;
    target.tvReactionTime = null;
    target.ivAddPicLabel = null;

    view2131296736.setOnClickListener(null);
    view2131296736 = null;
    view2131296751.setOnClickListener(null);
    view2131296751 = null;
    view2131296438.setOnClickListener(null);
    view2131296438 = null;
    view2131296740.setOnClickListener(null);
    view2131296740 = null;
    view2131296476.setOnClickListener(null);
    view2131296476 = null;
    view2131296725.setOnClickListener(null);
    view2131296725 = null;
    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296328.setOnClickListener(null);
    view2131296328 = null;

    super.unbind();
  }
}
