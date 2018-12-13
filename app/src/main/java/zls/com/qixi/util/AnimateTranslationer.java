package zls.com.qixi.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;

import zls.com.qixi.bean.AnimationParams;

/**
 * Created by oop on 2018/8/17.
 */

public class AnimateTranslationer {

    public static void exe(AnimationParams params){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(params.getTarget(), "translationX", params.getStartX(), params.getEndX());
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(params.getTarget(), "translationY", params.getStartY(), params.getEndY());

        animatorX.setInterpolator(new LinearInterpolator());
        animatorY.setInterpolator(new LinearInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        set.setDuration(params.getDuration());
        set.start();
    }

}
