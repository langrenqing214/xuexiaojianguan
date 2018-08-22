// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.internal.Utils;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import com.cxzy.xxjg.wideget.BottomBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding extends BaseActivity_ViewBinding {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    super(target, source);

    this.target = target;

    target.mContentContainer = Utils.findRequiredViewAsType(source, R.id.contentContainer, "field 'mContentContainer'", FrameLayout.class);
    target.mBottomBar = Utils.findRequiredViewAsType(source, R.id.bottomBar, "field 'mBottomBar'", BottomBar.class);
  }

  @Override
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mContentContainer = null;
    target.mBottomBar = null;

    super.unbind();
  }
}
