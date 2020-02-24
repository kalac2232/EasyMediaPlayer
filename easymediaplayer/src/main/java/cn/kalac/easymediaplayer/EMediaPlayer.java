package cn.kalac.easymediaplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;


import java.io.IOException;

/**
 * @author ghn
 * @date 2019/11/19 17:21
 */
class EMediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private static final String TAG = "EMediaPlayer";
    private final Context mContext;

    MediaManager.ManagerListener mManagerListener;
    private boolean isPrepared = false;
    private boolean autoPlayAfterPrepared = false;
    private Object mPlayingRes;

    public EMediaPlayer(Context context, MediaManager.ManagerListener managerListener) {
        mContext = context;
        mManagerListener = managerListener;
        setOnCompletionListener(this);
        setOnPreparedListener(this);
        setOnErrorListener(this);
    }

    @Override
    public void start() {

        if (!isPrepared) {
            autoPlayAfterPrepared = true;
            return;
        }
        super.start();
        if (mManagerListener != null) {
            mManagerListener.onStart(mPlayingRes);
        }
    }

    public void setDataSource(int resId) {
        calibratePlayStatus();
        reset();
        AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(resId);
        if (afd == null) {
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,"res open error!");
            }

            return;
        }
        try {
            setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mPlayingRes = resId;

        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }
        try {
            afd.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }
    }

    /**
     * 判断是否符合播放条件
     */
    private void calibratePlayStatus() {
        if (isPlaying()) {
            stop();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,"MediaPlayer is interrupt");
            }
        }
    }


    @Override
    public void setDataSource(String path) {
        calibratePlayStatus();
        reset();
        try {
            super.setDataSource(path);
            mPlayingRes = path;

        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }
    }


    @Override
    public void setDataSource(Context context, Uri uri) {
        calibratePlayStatus();
        reset();
        try {
            super.setDataSource(context, uri);
            mPlayingRes = uri;

        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }
    }

    public void setAssetsDataSource(String fileName) {
        calibratePlayStatus();
        AssetFileDescriptor fd = null;
        try {
            fd = mContext.getAssets().openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }

        try {
            if (fd != null) {
                setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                mPlayingRes = fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }

        try {
            if (fd != null) {
                fd.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (mManagerListener != null) {
                mManagerListener.onError(mPlayingRes,e.getMessage());
            }
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mManagerListener != null) {
            mManagerListener.onComplete(mPlayingRes);
        }
        reset();

    }

    @Override
    public void reset() {
        super.reset();
        isPrepared = false;
        autoPlayAfterPrepared = false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPrepared = true;
        if (mManagerListener != null) {
            mManagerListener.onPrepare(mPlayingRes);
        }

        if (autoPlayAfterPrepared) {
            start();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {

        //clearStatus();

        String whatErrorMsg;
        switch (what) {
            case -1004:
                whatErrorMsg = "MEDIA_ERROR_IO";
                //Log.d(TAG, whatErrorMsg);
                break;
            case -1007:
                whatErrorMsg = "MEDIA_ERROR_MALFORMED";
                //Log.d(TAG, whatErrorMsg);
                break;
            case 200:
                whatErrorMsg = "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK";
                //Log.d(TAG, whatErrorMsg);
                break;
            case 100:
                whatErrorMsg = "MEDIA_ERROR_SERVER_DIED";
                //Log.d(TAG, whatErrorMsg);
                break;
            case -110:
                whatErrorMsg = "MEDIA_ERROR_TIMED_OUT";
                //Log.d(TAG, whatErrorMsg);
                break;
            case -1010:
                whatErrorMsg = "MEDIA_ERROR_UNSUPPORTED";
                //Log.d(TAG, whatErrorMsg);
                break;
            default:
                whatErrorMsg = "MEDIA_ERROR_UNKNOWN: " + what;
                //Log.d(TAG, whatErrorMsg);
                break;
        }
        String extraErrorMsg;
        switch (extra) {
            case 800:
                extraErrorMsg = "MEDIA_INFO_BAD_INTERLEAVING";
                //Log.d(TAG, extraErrorMsg);
                break;
            case 702:
                extraErrorMsg = "MEDIA_INFO_BUFFERING_END";
                //Log.d(TAG, extraErrorMsg);
                break;
            case 701:
                extraErrorMsg = "MEDIA_INFO_METADATA_UPDATE";
                //Log.d(TAG, extraErrorMsg);
                break;
            case 802:
                extraErrorMsg = "MEDIA_INFO_METADATA_UPDATE ";
                //Log.d(TAG, extraErrorMsg);
                break;
            case 801:
                extraErrorMsg = "MEDIA_INFO_NOT_SEEKABLE";
                //Log.d(TAG, extraErrorMsg);
                break;
            case 3:
                extraErrorMsg = "MEDIA_INFO_VIDEO_RENDERING_START";
                //Log.d(TAG, extraErrorMsg);
                break;
            case 700:
                extraErrorMsg = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                //Log.d(TAG, extraErrorMsg);
                break;
            default:
                extraErrorMsg = "MEDIA_INFO_UNKNOWN: " + extra;
                //Log.d(TAG, extraErrorMsg);
                break;
        }

        if (mManagerListener != null) {
            mManagerListener.onError(mPlayingRes,whatErrorMsg + "---" + extraErrorMsg);
        }
        //处理错误,否则会调用onComplete();
        return true;
    }
}
