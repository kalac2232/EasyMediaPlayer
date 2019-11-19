package cn.kalac.easymediaplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ghn
 * @date 2019/11/19 17:21
 */
public class EMediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private static final String TAG = "EMediaPlayer";
    private final Context mContext;
    private List<EasyMediaListener> mListeners;
    private boolean isPrepared = false;
    private boolean autoPlayAfterPrepared = false;

    public EMediaPlayer(Context context, List<EasyMediaListener> listeners) {
        mContext = context;
        mListeners = listeners;
        setOnCompletionListener(this);
        setOnPreparedListener(this);
        setOnErrorListener(this);
    }

    @Override
    public void start() {
        if (!isPrepared) {
            autoPlayAfterPrepared = true;
        }

        super.start();
        for (EasyMediaListener mListener : mListeners) {
            mListener.onStart();
        }
    }

    public void setDataSource(@RawRes int resId)  {
        AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(resId);
        if (afd == null) {
            return;
        }
        try {
            setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }
        try {
            afd.close();
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }
    }

    @Override
    public void setDataSource(String path) {
        try {
            super.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }
    }

    @Override
    public void setDataSource(@NonNull Context context, @NonNull Uri uri) {
        try {
            super.setDataSource(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }
    }

    public void setAssetsDataSource(String fileName) {
        AssetFileDescriptor fd = null;
        try {
            fd = mContext.getAssets().openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }

        try {
            if (fd != null) {
                setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            }
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }

        try {
            if (fd != null) {
                fd.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            for (EasyMediaListener mListener : mListeners) {
                mListener.onError(e.getMessage());
            }
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        for (EasyMediaListener mListener : mListeners) {
            mListener.onComplete();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPrepared = true;
        for (EasyMediaListener mListener : mListeners) {
            mListener.onPrepare();
        }

        if (autoPlayAfterPrepared) {
            start();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        String whatErrorMsg;
        switch (what) {
            case -1004:
                whatErrorMsg = "MEDIA_ERROR_IO";
                Log.d(TAG, whatErrorMsg);
                break;
            case -1007:
                whatErrorMsg = "MEDIA_ERROR_MALFORMED";
                Log.d(TAG, whatErrorMsg);
                break;
            case 200:
                whatErrorMsg = "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK";
                Log.d(TAG, whatErrorMsg);
                break;
            case 100:
                whatErrorMsg = "MEDIA_ERROR_SERVER_DIED";
                Log.d(TAG, whatErrorMsg);
                break;
            case -110:
                whatErrorMsg = "MEDIA_ERROR_TIMED_OUT";
                Log.d(TAG, whatErrorMsg);
                break;
            case -1010:
                whatErrorMsg = "MEDIA_ERROR_UNSUPPORTED";
                Log.d(TAG, whatErrorMsg);
                break;
                default:
                    whatErrorMsg = "MEDIA_ERROR_UNKNOWN";
                    Log.d(TAG, whatErrorMsg);
                    break;
        }
        String extraErrorMsg;
        switch (extra) {
            case 800:
                extraErrorMsg = "MEDIA_INFO_BAD_INTERLEAVING";
                Log.d(TAG, extraErrorMsg);
                break;
            case 702:
                extraErrorMsg = "MEDIA_INFO_BUFFERING_END";
                Log.d(TAG, extraErrorMsg);
                break;
            case 701:
                extraErrorMsg = "MEDIA_INFO_METADATA_UPDATE";
                Log.d(TAG, extraErrorMsg);
                break;
            case 802:
                extraErrorMsg = "MEDIA_INFO_METADATA_UPDATE ";
                Log.d(TAG, extraErrorMsg);
                break;
            case 801:
                extraErrorMsg = "MEDIA_INFO_NOT_SEEKABLE";
                Log.d(TAG, extraErrorMsg);
                break;
            case 3:
                extraErrorMsg = "MEDIA_INFO_VIDEO_RENDERING_START";
                Log.d(TAG, extraErrorMsg);
                break;
            case 700:
                extraErrorMsg = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                Log.d(TAG, extraErrorMsg);
                break;
            default:
                extraErrorMsg = "MEDIA_INFO_UNKNOWN";
                Log.d(TAG, extraErrorMsg);
                break;
        }

        for (EasyMediaListener mListener : mListeners) {
            mListener.onError(whatErrorMsg + "---" + extraErrorMsg);
        }

        return false;
    }
}
