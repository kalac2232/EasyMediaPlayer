package cn.kalac.easymediaplayer.listener;

/**
 * @author kalac.
 * @date 2019/9/2 21:29
 */
public abstract class EasyMediaListener {

    public void onPrepare() {

    }

    public void onStart() {

    }

    public abstract void onComplete();

    public void onError(String errorMessage) {

    }
}
