// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyCanteenActivity_ViewBinding extends BaseActivity_ViewBinding {
  private MyCanteenActivity target;

  @UiThread
  public MyCanteenActivity_ViewBinding(MyCanteenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyCanteenActivity_ViewBinding(MyCanteenActivity target, View source) {
    super(target, source);

    this.target = target;

    target.rvMyCanteen = Utils.findRequiredViewAsType(source, R.id.rv_my_canteen, "field 'rvMyCanteen'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    MyCanteenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvMyCanteen = null;

    super.unbind();
  }
}
