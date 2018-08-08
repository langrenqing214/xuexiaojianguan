package com.cxzy.xxjg.wideget.videoview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.utils.ContextUtils;

/**
 * 播放器
 * Created by demo on 2018/8/8.
 */

public class MyVideoViewController extends RelativeLayout implements MediaControllerInterface, View.OnClickListener {

    private Context mContext ;
    //抬头
    private LinearLayout ll_top;
    //中间播放按钮
    private ImageView iv_center_play;
    //播放底部
    private LinearLayout ll_bottom;
    //左下角播放
    private ImageView iv_play;
    //播放时间
    private TextView tv_watched;
    //进度条
    private SeekBar sb_progress;
    //视频总时间
    private TextView tv_total;
    //全屏按钮
    private ImageView iv_fullscreen;
    private boolean isPlaying = false;

    private VideoView myVideo;
    private String videoUrl = "" ;
    private int sv_height;//记录非全屏状态时，surfaceView的高度，以便退出全屏时，设置回来
    private RelativeLayout rlMyVideo;
    private int rlHeight;
    private boolean isFullScreen;
    private Activity mActivity ;

   /* public MyVideoViewController(@NonNull Context context) {
        super(context);
        this.mContext = context ;
    }*/

    public MyVideoViewController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    /*public MyVideoViewController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, Context mContext) {
        super(context, attrs, defStyleAttr);
        this.mContext = mContext;
    }

    public MyVideoViewController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes, Context mContext) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = mContext;
    }*/

    public void setVideoUrl(Activity mContext , String url){
        this.mActivity = mContext ;
        this.videoUrl = url ;
        myVideo.setVideoPath(videoUrl);
    }

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_controller , this , true);
        ll_top =  view.findViewById(R.id.ll_top);
        iv_center_play =  view.findViewById(R.id.iv_center_play);
        ll_bottom =  view.findViewById(R.id.ll_bottom);
        iv_play =  view.findViewById(R.id.iv_play);
        tv_watched =  view.findViewById(R.id.tv_watched);
        sb_progress =  view.findViewById(R.id.sb_progress);
        tv_total =  view.findViewById(R.id.tv_total);
        iv_fullscreen = view.findViewById(R.id.iv_fullscreen);
        myVideo = view.findViewById(R.id.vv_my_video);
        rlMyVideo = view.findViewById(R.id.rl_my_video);
        initData();
        initLister();
    }

    private void initData(){
        iv_center_play.setBackgroundResource(R.drawable.icon_k_play);
        rlMyVideo.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rlHeight = ContextUtils.px2Dp(mContext , rlMyVideo.getHeight());
            }
        });
    }

    private void initLister(){
        iv_play.setOnClickListener(this);
        iv_center_play.setOnClickListener(this);
        iv_fullscreen.setOnClickListener(this);
    }

    //显示
    @Override
    public void show() {
        isPlaying = true ;
    }

    //显示
    @Override
    public void show(int time) {
        iv_center_play.setVisibility(GONE);
    }

    //隐藏
    @Override
    public void hide() {
        isPlaying = false ;
    }

    //是否显示
    @Override
    public boolean isShowing() {
        return false;
    }

    @Override
    public void setSeekBarEnabled(boolean var1) {

    }

    //设置已经播放完成了
    @Override
    public void isCompleted() {

    }

    //改变宽高
    @Override
    public void To_change_screen(int w, int h) {
        RelativeLayout.LayoutParams frameParams = new RelativeLayout.LayoutParams(w,h);
        rlMyVideo.setLayoutParams(frameParams);
    }

    @Override
    public void onClick(View view) {
        iv_play.setOnClickListener(this);
        iv_center_play.setOnClickListener(this);
        iv_fullscreen.setOnClickListener(this);
        switch (view.getId()){
            case R.id.iv_play ://播放

                break;
            case R.id.iv_center_play ://中间播放
                if (isPlaying){
                    myVideo.pause();
                    isPlaying = false ;
                    iv_center_play.setBackgroundResource(R.drawable.icon_k_play);
                }else {
                    myVideo.start();
                    isPlaying = true ;
                    iv_center_play.setBackgroundResource(R.drawable.icon_k_play);
                }
                break;
            case R.id.iv_fullscreen ://全屏播放
                if (isFullScreen) {
                    //退出全屏
                    Exit_full_screen();
                } else {
                    To_full_screen();
                }
                break;
        }
    }

    //进入全屏的操作
    public void To_full_screen() {
        isFullScreen = true;
        rlMyVideo.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        myVideo.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置activity横屏
        mActivity.getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);//隐藏状态栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.To_change_screen(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    //退出全屏的操作
    public void Exit_full_screen() {
        isFullScreen = false;
        rlMyVideo.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                sv_height));
        myVideo.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, sv_height));
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置activity竖屏
        mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//显示状态栏
        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.To_change_screen(RelativeLayout.LayoutParams.MATCH_PARENT, sv_height);
    }
}
