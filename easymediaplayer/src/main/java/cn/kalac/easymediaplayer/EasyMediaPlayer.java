package cn.kalac.easymediaplayer;

import android.app.Activity;
import android.content.Context;

/**
 * @author kalac.
 * @date 2019/8/14 21:22
 */
public class EasyMediaPlayer {


    public static MediaManager with(Activity activity) {

        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        return new MediaManager();
    }

    public static void with(Context context) {

    }

}
