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

    private boolean isReadyPlay = false;

    public MediaOperator(MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;

        setOnPreparedListener();
        mMediaPlayer.prepareAsync();
    }

    private void setOnPreparedListener() {
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isReadyPlay = true;
            }
        });
    }

    public void start() {
        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("You must load res first");
        }

        if (isReadyPlay) {
            startPlay();
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

    private void startPlay() {
        mMediaPlayer.start();
    }

}
