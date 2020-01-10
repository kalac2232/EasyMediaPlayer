package cn.kalac.easymediaplayer.handle;

import android.animation.ValueAnimator;

import cn.kalac.easymediaplayer.EasyMediaHandle;
import cn.kalac.easymediaplayer.MediaManager;

/**
 * 使播放暂停有音量渐变效果
 * @author kalac.
 * @date 2020/1/1 23:39
 */
public class VolumeGradientHandle extends EasyMediaHandle {

    @Override
    public void start() {

        //使声音有渐变效果
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float values = (float) animation.getAnimatedValue();

                mMediaPlayer.setVolume(values,values);

            }
        });
        valueAnimator.start();
        super.start();
    }

    @Override
    public void pause() {

        //使声音有渐变效果
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1,0);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float values = (float) animation.getAnimatedValue();
                mMediaPlayer.setVolume(values,values);
                if (values == 0) {
                    mMediaPlayer.pause();
                }
            }
        });
        valueAnimator.start();

    }
}
