package cn.kalac.easymediaplayer.handle;

import cn.kalac.easymediaplayer.EasyMediaHandle;
import cn.kalac.easymediaplayer.MediaManager;

/**
 * @author kalac.
 * @date 2020/1/1 23:37
 */
public class LoopMediaHandle implements EasyMediaHandle {

    public int loopCount = 1;

    @Override
    public void bindManager(MediaManager mediaManager) {

    }
}
