package cn.kalac.easymediaplayer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author kalac.
 * @date 2019/8/14 22:09
 */
public class MediaManager {

    private EMediaPlayer mMediaPlayer;

    private Context mContext;

    /**
     * 一个播放资源对应的监听
     */
    private HashMap<Object,List<EasyMediaListener>> mResListenerMap;

    private static WeakHashMap<Context,MediaManager> mMediaManagerMap;

    private EasyMediaListener mEasyMediaListener;

    private MediaManager(Context context) {
        mContext = context;
        mResListenerMap = new HashMap<>();
        mMediaPlayer = new EMediaPlayer(context,new ManagerListener());
    }

    public static MediaManager newInstance(Context context) {

        if (mMediaManagerMap == null) {
            mMediaManagerMap = new WeakHashMap<>();
            MediaManager mediaManager = new MediaManager(context);
            mMediaManagerMap.put(context,mediaManager);
            return mediaManager;
        } else {
            MediaManager mediaManager = mMediaManagerMap.get(context);
            if (mediaManager == null) {
                mediaManager = new MediaManager(context);
                mMediaManagerMap.put(context,mediaManager);
            }

            return mediaManager;
        }

    }

    /**
     * 从资源文件中进行加载
     * @param resId
     */
    public MediaOperator load(int resId) {
        if (resId < 0) {
            throw new IllegalStateException("check your resId");
        }
        addListener(resId);

        mMediaPlayer.setDataSource(resId);
        mMediaPlayer.prepareAsync();
        addOptions();

        return new MediaOperator(mMediaPlayer);
    }

    /**
     * 将资源与监听对象对应
     * @param o
     */
    private void addListener(Object o) {
        if (mEasyMediaListener != null) {
            List<EasyMediaListener> list = mResListenerMap.get(0);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(mEasyMediaListener);
            mResListenerMap.put(o,list);
        }
    }


    /**
     * 从网络地址中进行加载
     * @param url
     */
    public MediaOperator load(String url) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalStateException("check your url");
        }
        addListener(url);

        mMediaPlayer.setDataSource(url);
        mMediaPlayer.prepareAsync();
        addOptions();
        return new MediaOperator(mMediaPlayer);
    }

    public MediaOperator load(Uri uri) {
        if (uri == null) {
            throw new IllegalStateException("check your uri");
        }
        addListener(uri);

        mMediaPlayer.setDataSource(mContext,uri);
        mMediaPlayer.prepareAsync();
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

        addListener(fileName);

        mMediaPlayer.setAssetsDataSource(fileName);

        return new MediaOperator(mMediaPlayer);
    }

    /**
     * 返回一个还没有设置资源的播放器操作对象
     * @return
     */
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

        return this;
    }


    private void addOptions() {

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
        }


        public void onPrepare(Object res) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onPrepare();
                }
            }
        }


        public void onStart(Object res) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onStart();
                }
            }
        }


        public void onError(Object res,String errorMessage) {
            List<EasyMediaListener> listeners = mResListenerMap.get(res);
            if (listeners != null) {
                for (EasyMediaListener listener : listeners) {
                    listener.onError(errorMessage);
                }
            }
        }
    }
}
