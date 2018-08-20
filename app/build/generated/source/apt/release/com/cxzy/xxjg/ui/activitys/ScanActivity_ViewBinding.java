// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ScanActivity target;

  private View view2131296300;

  @UiThread
  public ScanActivity_ViewBinding(ScanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanActivity_ViewBinding(final ScanActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.mDBV = Utils.findRequiredViewAsType(source, R.id.dbv, "field 'mDBV'", DecoratedBarcodeView.class);
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
    ScanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDBV = null;

    view2131296300.setOnClickListener(null);
    view2131296300 = null;

    super.unbind();
  }
}
