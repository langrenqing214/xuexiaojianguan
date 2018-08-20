// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegulatoryInformationActivity_ViewBinding extends BaseActivity_ViewBinding {
  private RegulatoryInformationActivity target;

  @UiThread
  public RegulatoryInformationActivity_ViewBinding(RegulatoryInformationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegulatoryInformationActivity_ViewBinding(RegulatoryInformationActivity target,
      View source) {
    super(target, source);

    this.target = target;

    target.mWebView = Utils.findRequiredViewAsType(source, R.id.wv_regulatory, "field 'mWebView'", WebView.class);
  }

  @Override
  public void unbind() {
    RegulatoryInformationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebView = null;

    super.unbind();
  }
}
