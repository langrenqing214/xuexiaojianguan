// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.wideget.SimpleMultiStateView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseActivity_ViewBinding implements Unbinder {
  private BaseActivity target;

  @UiThread
  public BaseActivity_ViewBinding(BaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseActivity_ViewBinding(BaseActivity target, View source) {
    this.target = target;

    target.titleTv = Utils.findOptionalViewAsType(source, R.id.actionbar_title, "field 'titleTv'", TextView.class);
    target.mSimpleMultiStateView = Utils.findOptionalViewAsType(source, R.id.SimpleMultiStateView, "field 'mSimpleMultiStateView'", SimpleMultiStateView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTv = null;
    target.mSimpleMultiStateView = null;
  }
}
