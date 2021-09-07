package cn.kalac.easymediaplayer;

import android.os.Bundle;
import android.util.Log;

/**
 * @author ghn
 * @date 2019/10/17 17:59
 */
class MediaLifeListener implements LifeListener{

    private MediaManager mediaManager;

    public MediaLifeListener(MediaManager mediaManager) {
        this.mediaManager = mediaManager;
    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

        if (mediaManager != null) {
            mediaManager.releasePlayer();
            //清除manager 避免内存泄漏
            mediaManager.clear();
        }

    }
}