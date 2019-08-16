package cn.kalac.easymediaplayer;

import android.media.MediaPlayer;

/**
 * @author kalac.
 * @date 2019/8/14 22:38
 */
public class MediaFactory {

    /**
     * 根据资源类型的不同进行加载播放器
     * @return
     */
    public static MediaPlayer getMediaPlayer() {
        return new MediaPlayer();
    }
}
