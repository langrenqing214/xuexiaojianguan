package com.cxzy.xxjg.ui.activitys;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.BaseActivity;
import com.cxzy.xxjg.di.component.AppComponent;

import butterknife.BindView;

/**
 * 视频
 */
public class VideoActivity extends BaseActivity {

    @BindView(R.id.vv_video)
    VideoView mVideoView ;

    @Override
    public int getContentLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

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

    }

    @Override
    public void refreshFaild() {

    }

    @Override
    public void onRetry() {

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
            mVideoView.setVideoPath("http://cache.utovr.com/201508270528174780.m3u8 ");
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
}
