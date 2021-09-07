package cn.kalac.easymediaplayer;

import android.media.MediaPlayer;

/**
 * @author ghn
 * @date 2019/10/17 17:56
 */
public abstract class EasyMediaHandle {

    protected MediaPlayer mMediaPlayer;

    public void bindPlayer(MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;
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
