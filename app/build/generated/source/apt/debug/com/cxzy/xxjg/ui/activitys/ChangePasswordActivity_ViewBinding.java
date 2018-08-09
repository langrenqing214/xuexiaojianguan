// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ChangePasswordActivity target;

  private View viewSource;

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity target, View source) {
    super(target, source);

    this.target = target;

    viewSource = source;
    source.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    viewSource.setOnClickListener(null);
    viewSource = null;

    super.unbind();
  }
}
