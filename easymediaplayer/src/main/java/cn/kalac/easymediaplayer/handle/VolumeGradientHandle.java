package cn.kalac.easymediaplayer.handle;

import android.animation.ValueAnimator;

/**
 * 使播放暂停有音量渐变效果
 * @author kalac.
 * @date 2020/1/1 23:39
 */
public class VolumeGradientHandle extends EasyMediaHandle {
    private ValueAnimator valueAnimator;
    private int mDuration;

    public VolumeGradientHandle() {
        mDuration = 1000;
    }

    public VolumeGradientHandle(int duration) {
        mDuration = duration;
    }

    @Override
    public void start() {

        //使声音有渐变效果
        valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(mDuration);
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
        valueAnimator = ValueAnimator.ofFloat(1,0);
        valueAnimator.setDuration(mDuration);
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

    @Override
    public void release() {
        valueAnimator.cancel();
        // 快速停止播放，并释放资源
        valueAnimator = ValueAnimator.ofFloat(1,0);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float values = (float) animation.getAnimatedValue();
                mMediaPlayer.setVolume(values,values);
                if (values == 0) {
                    mMediaPlayer.pause();
                    VolumeGradientHandle.super.release();
                }
            }
        });
        valueAnimator.start();

    }
}
