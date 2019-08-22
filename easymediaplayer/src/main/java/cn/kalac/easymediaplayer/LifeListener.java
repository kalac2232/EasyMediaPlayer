package cn.kalac.easymediaplayer;

import android.os.Bundle;

/**
 * @author kalac.
 * @date 2019/8/18 15:24
 */
public interface LifeListener {

    void onCreate(Bundle bundle);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
