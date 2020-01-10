package cn.kalac.easymediaplayer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author kalac.
 * @date 2019/8/14 22:09
 */
public class MediaManager {
    private static final String TAG = "MediaManager";

    private EMediaPlayer mMediaPlayer;

    private Context mContext;

    private EasyMediaHandle mEasyMediaHandle;

    /**
     * 一个播放资源对应的所有监听器对象,内部使用
     */
    private HashMap<Object,List<EasyMediaListener>> mResListenerMap;

    /**
     * 想法是：一个页面对应一个音乐播放器，在一个界面中新播放会中断正在播放的音频
     */
    private static WeakHashMap<Context,MediaManager> mMediaManagerMap;

    private EasyMediaListener mEasyMediaListener;

    private MediaManager(Context context) {
        mContext = context;
        mResListenerMap = new HashMap<>();
        mMediaPlayer = new EMediaPlayer(context,new ManagerListener());
    }

    public static MediaManager newInstance(Context context) {
        MediaManager mediaManager;
        if (mMediaManagerMap == null) {
            mMediaManagerMap = new WeakHashMap<>();
            mediaManager = new MediaManager(context);
            mMediaManagerMap.put(context,mediaManager);
        } else {
            mediaManager = mMediaManagerMap.get(context);
            if (mediaManager == null) {
                mediaManager = new MediaManager(context);
                mMediaManagerMap.put(context,mediaManager);
            }

        }


        mediaManager.initStates();
        return mediaManager;

    }

    private void initStates() {
        mEasyMediaListener = null;
    }

    /**
     * 从资源文件中进行加载
     * @param resId
     */
    public MediaManager load(int resId) {
        if (resId < 0) {
            throw new IllegalStateException("check your resId");
        }

        mMediaPlayer.setDataSource(resId);
        mMediaPlayer.prepareAsync();

        return this;
    }

    /**
     * 将资源与监听对象对应
     * @param o
     */
    private void addListener(Object o) {
        if (mEasyMediaListener != null) {
            List<EasyMediaListener> list = mResListenerMap.get(o);
            if (list == null) {
                list = new ArrayList<>();
            }

            if (!list.contains(mEasyMediaListener)) {
                list.add(mEasyMediaListener);
                mResListenerMap.put(o,list);
            }

        }
    }


    /**
     * 从网络地址中进行加载
     * @param url
     */
    public MediaManager load(String url) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalStateException("check your url");
        }

        mMediaPlayer.setDataSource(url);
        mMediaPlayer.prepareAsync();
        return this;
    }

    public MediaManager load(Uri uri) {
        if (uri == null) {
            throw new IllegalStateException("check your uri");
        }

        mMediaPlayer.setDataSource(mContext,uri);
        mMediaPlayer.prepareAsync();
        return this;
    }


    public MediaManager load(List list) {
        return this;
    }

    /**
     * 从asset中加载资源
     * @param fileName
     */
    public MediaManager loadAssets(String fileName) {

        mMediaPlayer.setAssetsDataSource(fileName);

        return this;
    }

    /**
     * 返回一个还没有设置资源的播放器操作对象
     * @return
     */
    @Deprecated
    public MediaOperator createOperator() {

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
        return this;
    }

    /**
     * 对整个过程进行加工处理 如：多次循环，声音变化
     * @param easyMediaHandle
     * @return
     */
    public MediaManager handle(final EasyMediaHandle easyMediaHandle) {
        mEasyMediaHandle = easyMediaHandle;
        mEasyMediaHandle.bindPlayer(mMediaPlayer);
        return this;
    }


    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public void start() {
        //Log.i(TAG, "start: ");
        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("You must load res first");
        }

        if (mEasyMediaHandle != null) {
            mEasyMediaHandle.start();
        } else {
            mMediaPlayer.start();
        }
    }

    public void pause() {

        if (mMediaPlayer == null) {
            throw new IllegalArgumentException("mediaPlayer is null");
        }

        if (mMediaPlayer.isPlaying()) {
            //Log.i(TAG, "pause: ");
            if (mEasyMediaHandle != null) {
                mEasyMediaHandle.pause();
            } else {
                mMediaPlayer.pause();
            }
        }
    }

    public void volume(float volume) {
        volume(volume,volume);
    }

    public void volume(float left,float right) {
        mMediaPlayer.setVolume(left,right);
    }

    public void seekTo(int msec) {
        mMediaPlayer.seekTo(msec);
    }


    public void reset() {
        mMediaPlayer.reset();
    }


    /**
     * 功能1：监听的分发
     */
    class ManagerListener {


        public void onComplete(Object res) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onComplete();
                }
            }

            if (mEasyMediaListener != null) {
                mEasyMediaListener.onComplete();
            }
        }


        public void onPrepare(Object res) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onPrepare();
                }
            }

            if (mEasyMediaListener != null) {
                mEasyMediaListener.onPrepare();
            }
        }


        public void onStart(Object res) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onStart();
                }
            }

            if (mEasyMediaListener != null) {
                mEasyMediaListener.onStart();
            }
        }


        public void onError(Object res,String errorMessage) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onError(errorMessage);
                }
            }

            if (mEasyMediaListener != null) {
                mEasyMediaListener.onError(errorMessage);
            }
        }
    }
}
