// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoWallActivity_ViewBinding extends BaseActivity_ViewBinding {
  private PhotoWallActivity target;

  @UiThread
  public PhotoWallActivity_ViewBinding(PhotoWallActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoWallActivity_ViewBinding(PhotoWallActivity target, View source) {
    super(target, source);

    this.target = target;

    target.titleTV = Utils.findRequiredViewAsType(source, R.id.topbar_title_tv, "field 'titleTV'", TextView.class);
    target.backBtn = Utils.findRequiredViewAsType(source, R.id.topbar_left_btn, "field 'backBtn'", Button.class);
    target.ivLeft = Utils.findRequiredViewAsType(source, R.id.topbar_left_iv, "field 'ivLeft'", ImageView.class);
    target.confirmBtn = Utils.findRequiredViewAsType(source, R.id.topbar_right_btn, "field 'confirmBtn'", Button.class);
    target.okBtn = Utils.findRequiredViewAsType(source, R.id.photo_wall_ok, "field 'okBtn'", Button.class);
  }

  @Override
  public void unbind() {
    PhotoWallActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTV = null;
    target.backBtn = null;
    target.ivLeft = null;
    target.confirmBtn = null;
    target.okBtn = null;

    super.unbind();
  }
}
