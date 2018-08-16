package zls.com.qixi.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zls.com.qixi.bean.AnimationParams;

/**
 * Created by oop on 2018/8/16.
 */

public class Animationer {

    private AnimationParams params;
    private Disposable disposable;

    public Animationer(AnimationParams params){
        this.params = params;
    }

    public void exe(){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(params.getTarget(), "translationX", params.getStartX(), params.getEndX(), params.getEndX());
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(params.getTarget(), "translationY", params.getStartY(), params.getEndY(), params.getEndY());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        set.setDuration(params.getDuration());

        if(params.hasBgAnimation()){
            set.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationStart(Animator animation) {
                    exeBgAnimation();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    endAnimation();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    endAnimation();
                }

            });
        }

        set.start();
    }

    private void exeBgAnimation(){
        disposable = Observable.interval(params.getBgAnimationInterval(), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    int resIndex = params.getCurResIndex();
                    int res = (int) params.getRes().get(resIndex);
                    if(params.getTarget() instanceof ImageView){
                        ((ImageView)params.getTarget()).setImageResource(res);
                    }else {
                        params.getTarget().setBackgroundResource(res);
                    }
                    resIndex ++;
                    if(resIndex >= params.getRes().size()){
                        resIndex = 0;
                    }
                    params.setCurResIndex(resIndex);
                });
    }

    private void endAnimation() {
        if(disposable != null){
            disposable.dispose();
        }
    }

}
