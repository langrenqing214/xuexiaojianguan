// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanResultActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ScanResultActivity target;

  private View view2131296344;

  private View view2131296306;

  @UiThread
  public ScanResultActivity_ViewBinding(ScanResultActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanResultActivity_ViewBinding(final ScanResultActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.lvScanResutl = Utils.findRequiredViewAsType(source, R.id.lv_scan_result, "field 'lvScanResutl'", ListView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.main_title_id, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_scan_result, "field 'btnScanResult' and method 'onViewClicked'");
    target.btnScanResult = Utils.castView(view, R.id.btn_scan_result, "field 'btnScanResult'", Button.class);
    view2131296344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back_btn_id, "method 'onViewClicked'");
    view2131296306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  public void unbind() {
    ScanResultActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lvScanResutl = null;
    target.tvTitle = null;
    target.btnScanResult = null;

    view2131296344.setOnClickListener(null);
    view2131296344 = null;
    view2131296306.setOnClickListener(null);
    view2131296306 = null;

    super.unbind();
  }
}
