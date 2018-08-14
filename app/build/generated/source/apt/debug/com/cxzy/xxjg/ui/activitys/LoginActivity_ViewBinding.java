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

public class LoginActivity_ViewBinding extends BaseActivity_ViewBinding {
  private LoginActivity target;

  private View view2131296341;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.etUserName = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'etUserName'", EditText.class);
    target.etPassWord = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassWord'", EditText.class);
    target.etMainUrl = Utils.findRequiredViewAsType(source, R.id.et_main_url, "field 'etMainUrl'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'onViewClicked'");
    view2131296341 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUserName = null;
    target.etPassWord = null;
    target.etMainUrl = null;

    view2131296341.setOnClickListener(null);
    view2131296341 = null;

    super.unbind();
  }
}
