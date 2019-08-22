package cn.kalac.easymediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.util.List;

/**
 * @author kalac.
 * @date 2019/8/14 22:09
 */
public class MediaManager {


    private static MediaPlayer mMediaPlayer;

    private Context mContext;
    private MediaOperator mMediaOperator;

    public MediaManager(Context context) {
        mContext = context;
    }

    /**
     * 从资源文件中进行加载
     * @param resId
     */
    public MediaOperator load(int resId) {
        mMediaPlayer = MediaFactory.getMediaPlayer(mContext,resId);
        return new MediaOperator(mMediaPlayer);
    }

    /**
     * 从网络地址中进行加载
     * @param url
     */
    public MediaOperator load(String url) {
        mMediaPlayer = MediaFactory.getMediaPlayer(url);
        return new MediaOperator(mMediaPlayer);
    }

    public MediaOperator load(Uri uri) {
        mMediaPlayer = MediaFactory.getMediaPlayer(mContext,uri);
        return new MediaOperator(mMediaPlayer);
    }


    public void load(List<String> list) {

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

    public void setMediaCompleteListener(final MediaCompleteListener mediaCompleteListener) {

        if (mMediaPlayer != null && mediaCompleteListener != null) {
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaCompleteListener.onComplete();
                }
            });
        }
    }
}
