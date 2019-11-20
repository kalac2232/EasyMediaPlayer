package cn.kalac.easymediaplayer;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * 音频操作类对象
 * 功能：播放、暂停、快进等
 * @author kalac.
 * @date 2019/8/18 15:06
 */
public class MediaOperator {

    private static MediaPlayer mMediaPlayer;

    public MediaOperator(MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;
    }

    public void start() {
        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("You must load res first");
        }

        mMediaPlayer.start();

    }

    public void pause() {
        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("mediaPlayer is null");
        }

        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void volume(float volume) {
        volume(volume,volume);
    }

    public void volume(float left,float right) {
        mMediaPlayer.setVolume(left,right);
    }

}
