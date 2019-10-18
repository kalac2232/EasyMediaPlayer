package cn.kalac.easymediaplayer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


/**
 * @author kalac.
 * @date 2019/8/14 21:22
 */
public class EasyMediaPlayer {

    private static final String TAG = "EasyMediaPlayer";

    private static EasyMediaPlayer mEasyMediaPlayer;


    public static MediaManager with(Context context) {

        if (context == null) {
            throw new NullPointerException("context is null");
        }

        MediaManager mediaManager = new MediaManager(context);

        if (context instanceof Activity) {
            addLifeListener((Activity) context,mediaManager);
        }

        return mediaManager;
    }

    private static void addLifeListener(Activity activity, MediaManager mediaManager) {


        LifeListenerFragment fragment = getLifeListenerFragment(activity);
        fragment.addLifeListener(new MediaLifeListener(mediaManager));

    }


    private static LifeListenerFragment getLifeListenerFragment(Activity activity) {

        FragmentManager fragmentManager = activity.getFragmentManager();
        return getLifeListenerFragment(fragmentManager);
    }

    /**
     * 添加空白fragment
     * @param manager
     * @return
     */
    private static LifeListenerFragment getLifeListenerFragment(FragmentManager manager) {
        LifeListenerFragment fragment = (LifeListenerFragment) manager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new LifeListenerFragment();
            manager.beginTransaction().add(fragment, TAG).commitAllowingStateLoss();
        }

        return fragment;
    }



}
