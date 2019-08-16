package cn.kalac.easymediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;

import java.util.List;

/**
 * @author kalac.
 * @date 2019/8/14 22:09
 */
public class MediaManager {


    private static MediaPlayer mMediaPlayer;

    /**
     * 从资源文件中进行加载
     * @param resId
     */
    public void load(int resId) {
        mMediaPlayer = MediaFactory.getMediaPlayer();
    }

    /**
     * 从网络地址中进行加载
     * @param url
     */
    public void load(String url) {

    }

    public void load(Uri uri) {

    }


    public void load(List<String> list) {



    }

    public void start() {
        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("You must load res first");
        }

        mMediaPlayer.start();
    }


}
