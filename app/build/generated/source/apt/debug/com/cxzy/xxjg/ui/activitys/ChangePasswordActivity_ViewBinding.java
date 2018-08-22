// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ChangePasswordActivity target;

  private View view2131296306;

  private View view2131296335;

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.etOldPwd = Utils.findRequiredViewAsType(source, R.id.et_old_pwd, "field 'etOldPwd'", EditText.class);
    target.etNewPwd = Utils.findRequiredViewAsType(source, R.id.et_new_pwd, "field 'etNewPwd'", EditText.class);
    target.etAgainPwd = Utils.findRequiredViewAsType(source, R.id.et_again_pwd, "field 'etAgainPwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_change_pwd, "method 'onViewClicked'");
    view2131296335 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    ChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etOldPwd = null;
    target.etNewPwd = null;
    target.etAgainPwd = null;

    view2131296306.setOnClickListener(null);
    view2131296306 = null;
    view2131296335.setOnClickListener(null);
    view2131296335 = null;

    super.unbind();
  }
}
