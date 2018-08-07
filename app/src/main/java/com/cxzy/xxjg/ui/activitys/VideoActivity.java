package com.cxzy.xxjg.ui.activitys;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.bean.VideoBean;
import com.cxzy.xxjg.di.component.AppComponent;
import com.cxzy.xxjg.di.component.DaggerHttpComponent;
import com.cxzy.xxjg.dialog.SelectCanteenDialog;
import com.cxzy.xxjg.dialog.SelectPosintionDialog;
import com.cxzy.xxjg.ui.test.presenter.VideoPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 视频
 */
public class VideoActivity extends BaseActivity<VideoPresenterImpl> implements SelectCanteenDialog.SelectCanteenItemListener, SelectPosintionDialog.SelectCanteenPositionListener {

    @BindView(R.id.vv_video)
    VideoView mVideoView;
    @BindView(R.id.ll_canteen_select)
    LinearLayout llCanteenSelect;
    @BindView(R.id.tv_canteen_show)
    TextView tvCanteenShow;
    @BindView(R.id.ll_time_select)
    LinearLayout llTimeSelect;
    @BindView(R.id.tv_time_show)
    TextView tvTimeShow;
    private String canteenId;
    private ArrayList<SchoolCanteenBean> dataList = new ArrayList<>();
    private String videoUrl = "";
    private List<VideoBean> beanList;


    @Override
    public int getContentLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initInjector(AppComponent appComponent) {
        DaggerHttpComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setStatusBarColor(ContextCompat.getColor(mContext, R.color.main_style_color));
        dataList = (ArrayList<SchoolCanteenBean>) getIntent().getSerializableExtra("canteenList");
        canteenId = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).id;
        String canteenName = dataList == null || dataList.size() == 0 ? "" : dataList.get(0).name;
        tvCanteenShow.setText(canteenName);
        mPresenter.getVideoList(canteenId);
    }

    @Override
    public void initData() {
        setupVideo();
//        mVideoView.setVideoURI(Uri.parse("http://cache.utovr.com/201508270528174780.m3u8"));

        // 开始播放视频
//        mVideoView.start();
    }

    @Override
    public void refreshView(Object mData) {
        try {
            if (mData != null) {
                beanList = (List<VideoBean>) mData;
                tvTimeShow.setText(beanList.get(0).position);
                videoUrl = beanList.get(0).videoUrl ;
                stopPlaybackVideo();
                setupVideo();
            }
        }catch (Exception e){}


    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

    }

    @OnClick({R.id.back_btn_id, R.id.ll_canteen_select , R.id.ll_time_select})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.back_btn_id://返回
                finish();
                break;
            case R.id.ll_select_canteen ://选择食堂
                SelectCanteenDialog canteenDialog = new SelectCanteenDialog(this, dataList, this);
                canteenDialog.show();
                break;
            case R.id.ll_time_select://选择位置
                SelectPosintionDialog posintionDialog = new SelectPosintionDialog(this , beanList , this);
                posintionDialog.show();
                break;
        }
    }

    private void setupVideo() {
        //设置视频控制器
        mVideoView.setMediaController(new MediaController(this));

        //播放完成回调
//        mVideoView.setOnCompletionListener( new MyPlayerOnCompletionListener());
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaybackVideo();
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                stopPlaybackVideo();
                return true;
            }
        });

        try {
//            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.test_video);
//            mVideoView.setVideoURI(uri);
            mVideoView.setVideoPath(videoUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mVideoView.isPlaying()) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView.canPause()) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaybackVideo();
    }

    private void stopPlaybackVideo() {
        try {
            mVideoView.stopPlayback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectCanteenItem(int positon, String canteenName, String canteenId) {
        this.canteenId = canteenId ;
        tvCanteenShow.setText(canteenName);
        mPresenter.getVideoList(canteenId);
    }

    @Override
    public void selectCanteenPositionItem(int positon, String canteenPosition, String videoUrl) {
        this.videoUrl = videoUrl ;
        tvTimeShow.setText(canteenPosition);
        setupVideo();
        setupVideo();
    }
}
