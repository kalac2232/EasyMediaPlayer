package cn.kalac.easymediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.RawRes;

import java.util.List;

/**
 * @author kalac.
 * @date 2019/8/14 22:09
 */
public class MediaManager {


    private MediaPlayer mMediaPlayer;

    private Context mContext;
    private MediaOperator mMediaOperator;

    private EasyMediaListener mEasyMediaListener;

    public MediaManager(Context context) {
        mContext = context;
    }

    /**
     * 从资源文件中进行加载
     * @param resId
     */
    public MediaOperator load(@RawRes int resId) {
        mMediaPlayer = MediaFactory.getMediaPlayer(mContext,resId);
        addOptions();

        return new MediaOperator(mMediaPlayer);
    }


    /**
     * 从网络地址中进行加载
     * @param url
     */
    public MediaOperator load(String url) {
        mMediaPlayer = MediaFactory.getMediaPlayer(url);
        addOptions();
        return new MediaOperator(mMediaPlayer);
    }

    public MediaOperator load(Uri uri) {
        mMediaPlayer = MediaFactory.getMediaPlayer(mContext,uri);
        addOptions();
        return new MediaOperator(mMediaPlayer);
    }


    public MediaOperator load(List list) {
        addOptions();
        return new MediaOperator(mMediaPlayer);
    }

    /**
     * 从asset中加载资源
     * @param fileName
     */
    public MediaOperator loadAssets(String fileName) {

        mMediaPlayer = MediaFactory.getMediaPlayerFromAssets(mContext,fileName);
        return new MediaOperator(mMediaPlayer);
    }

    public void releasePlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();

            //关键语句
            mMediaPlayer.reset();

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public MediaManager listener(final EasyMediaListener easyMediaListener) {
        mEasyMediaListener = easyMediaListener;
        addListener();
        return this;
    }

    /**
     * 添加额外的参数信息
     * @param easyMediaOptions
     * @return
     */
    public MediaManager options(final EasyMediaOptions easyMediaOptions) {

        return this;
    }


    private void addOptions() {

    }

    private void addListener() {
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mEasyMediaListener != null) {
                    mEasyMediaListener.onComplete();
                }
            }
        });

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mEasyMediaListener != null) {
                    mEasyMediaListener.onPrepare();
                }
            }
        });

    }
}
