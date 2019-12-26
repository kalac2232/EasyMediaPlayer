package cn.kalac.easymediaplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * @author kalac.
 * @date 2019/8/14 22:38
 */
@Deprecated
class MediaFactory {

    /**
     * 根据资源类型的不同进行加载播放器
     * @return
     */
    public static MediaPlayer getMediaPlayer(Context context, int resId) {


        return EMediaPlayer.create(context, resId);
    }

    public static MediaPlayer getMediaPlayer(String url) {

        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mp;
    }

    public static MediaPlayer getMediaPlayer(Context context,Uri url) {

        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource(context,url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return mp;
    }

    public static MediaPlayer getMediaPlayerFromAssets(Context context,String fileName) {

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor fd = context.getAssets().openFd(fileName);

            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return mediaPlayer;
    }
}
