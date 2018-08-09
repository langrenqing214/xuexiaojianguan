// Generated code from Butter Knife. Do not modify!
package com.cxzy.xxjg.ui.activitys;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.jzvd.JZVideoPlayerStandard;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoActivity_ViewBinding extends BaseActivity_ViewBinding {
  private VideoActivity target;

  private View view2131296501;

  private View view2131296513;

  private View view2131296306;

  @UiThread
  public VideoActivity_ViewBinding(VideoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoActivity_ViewBinding(final VideoActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.mVideoView = Utils.findRequiredViewAsType(source, R.id.videoplayer, "field 'mVideoView'", JZVideoPlayerStandard.class);
    view = Utils.findRequiredView(source, R.id.ll_canteen_select, "field 'llCanteenSelect' and method 'onViewClicked'");
    target.llCanteenSelect = Utils.castView(view, R.id.ll_canteen_select, "field 'llCanteenSelect'", LinearLayout.class);
    view2131296501 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCanteenShow = Utils.findRequiredViewAsType(source, R.id.tv_canteen_show, "field 'tvCanteenShow'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_time_select, "field 'llTimeSelect' and method 'onViewClicked'");
    target.llTimeSelect = Utils.castView(view, R.id.ll_time_select, "field 'llTimeSelect'", LinearLayout.class);
    view2131296513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTimeShow = Utils.findRequiredViewAsType(source, R.id.tv_time_show, "field 'tvTimeShow'", TextView.class);
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
    VideoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mVideoView = null;
    target.llCanteenSelect = null;
    target.tvCanteenShow = null;
    target.llTimeSelect = null;
    target.tvTimeShow = null;

    view2131296501.setOnClickListener(null);
    view2131296501 = null;
    view2131296513.setOnClickListener(null);
    view2131296513 = null;
    view2131296306.setOnClickListener(null);
    view2131296306 = null;

    super.unbind();
  }
}
