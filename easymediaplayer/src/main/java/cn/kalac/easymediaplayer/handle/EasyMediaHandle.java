package cn.kalac.easymediaplayer.handle;

import android.media.MediaPlayer;

import cn.kalac.easymediaplayer.MediaManager;

/**
 * @author ghn
 * @date 2019/10/17 17:56
 */
public abstract class EasyMediaHandle {

    protected MediaManager mMediaManager;
    protected MediaPlayer mMediaPlayer;

    public void bindManager(MediaManager mediaManager) {
        mMediaManager = mediaManager;
        mMediaPlayer = mediaManager.getPlayer();
    }

    public void start() {
        mMediaPlayer.start();
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    public void release() {
        mMediaPlayer.stop();

        //关键语句
        mMediaPlayer.reset();

        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}
