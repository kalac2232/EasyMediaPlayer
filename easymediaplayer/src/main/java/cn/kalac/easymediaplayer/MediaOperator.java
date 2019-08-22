package cn.kalac.easymediaplayer;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
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
        try {
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pause() {
        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("mediaPlayer is null");
        }

        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

}
