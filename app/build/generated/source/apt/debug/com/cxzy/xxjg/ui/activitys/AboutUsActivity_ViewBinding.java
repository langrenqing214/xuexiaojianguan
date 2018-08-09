// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutUsActivity_ViewBinding extends BaseActivity_ViewBinding {
  private AboutUsActivity target;

  private View viewSource;

  @UiThread
  public AboutUsActivity_ViewBinding(AboutUsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutUsActivity_ViewBinding(final AboutUsActivity target, View source) {
    super(target, source);

    this.target = target;

    target.tvVsersion = Utils.findRequiredViewAsType(source, R.id.vserion, "field 'tvVsersion'", TextView.class);
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
    AboutUsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvVsersion = null;

    viewSource.setOnClickListener(null);
    viewSource = null;

    super.unbind();
  }
}
