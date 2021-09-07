package cn.kalac.easymediaplayer.handle;

import android.media.MediaPlayer;

import java.util.List;

import cn.kalac.easymediaplayer.listener.EasyMediaListener;
import cn.kalac.easymediaplayer.MediaManager;
import cn.kalac.easymediaplayer.listener.LoopMediaListener;

/**
 * @author kalac.
 * @date 2020/1/1 23:37
 */
public class LoopMediaHandle extends EasyMediaHandle {

    public int mLoopCount = 3;
    public int temp = 1;

    public LoopMediaHandle(int loopCount) {
        mLoopCount = loopCount;
    }

    @Override
    public void bindManager(MediaManager mediaManager) {
        super.bindManager(mediaManager);
        mediaManager.listener(listener);
    }


    private EasyMediaListener listener = new EasyMediaListener() {
        @Override
        public void onComplete() {
            if (temp < mLoopCount) {
                mMediaPlayer.start();
                temp++;
                List<EasyMediaListener> listenerList = mMediaManager.getListenerList();
                for (EasyMediaListener listener : listenerList) {
                    if (listener instanceof LoopMediaListener) {
                        ((LoopMediaListener) listener).onComplete(mLoopCount - temp + 1);
                    }
                }
            } else {
                // 完成loop循环通知监听
                List<EasyMediaListener> listenerList = mMediaManager.getListenerList();
                for (EasyMediaListener listener : listenerList) {
                    if (listener instanceof LoopMediaListener) {
                        ((LoopMediaListener) listener).onLoopComplete();
                    }
                }
            }
        }
    };
}
