// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegulatoryInformationActivity_ViewBinding extends BaseActivity_ViewBinding {
  private RegulatoryInformationActivity target;

  private View view2131296300;

  @UiThread
  public RegulatoryInformationActivity_ViewBinding(RegulatoryInformationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegulatoryInformationActivity_ViewBinding(final RegulatoryInformationActivity target,
      View source) {
    super(target, source);

    this.target = target;

    View view;
    target.mWebView = Utils.findRequiredViewAsType(source, R.id.wv_regulatory, "field 'mWebView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    RegulatoryInformationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebView = null;

    view2131296300.setOnClickListener(null);
    view2131296300 = null;

    super.unbind();
  }
}
