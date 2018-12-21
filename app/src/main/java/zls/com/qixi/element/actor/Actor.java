package zls.com.qixi.element.actor;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgReceiver;

/**
 * Created by oop on 2018/8/16.
 */

public abstract class Actor<T extends View> implements MsgReceiver{

    protected T contentView;
    protected int width, height, initLeft, initTop;
    protected Context context;

    public Actor(Context context, int width, int height, int initLeft, int initTop, T contentView){
        this.context = context;
        this.width = width;
        this.height = height;
        this.initLeft = initLeft;
        this.initTop = initTop;
        this.contentView = contentView;
        MsgHelper.getINSTANCE().register(this);
        init();
    }

    protected abstract void init();

    public T getContentView(){
        return contentView;
    }

    public abstract void move(long millis, int fromX, int toX, int fromY, int toY);

    public void afterDestroy(){
        MsgHelper.getINSTANCE().unRegister(this);
    }

    protected void translate(long duration, int fromX, int toX, int fromY, int toY){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(contentView, "translationX", fromX, toX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(contentView, "translationY", fromY, toY);

        animatorX.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                Log.e("Interpolation " + Actor.this.getClass().getSimpleName(),"input = " + input);
                return input;
            }
        });
        animatorY.setInterpolator(new LinearInterpolator());

        animatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("onAnimationUpdate", "AnimatedFraction() = " + animation.getAnimatedFraction());
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        set.setDuration(duration);
        set.start();
    }

    protected void setRes(int res){
        if(contentView instanceof ImageView){
            ((ImageView)contentView).setImageResource(res);
        }else {
            contentView.setBackgroundResource(res);
        }
    }

    @SuppressLint("CheckResult")
    protected void bgSchedule(long duration, long interval, List<Integer> images, int endImage){
        Observable
                .interval(interval, interval, TimeUnit.MILLISECONDS)
                .take((duration - interval) / interval)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(emmitCount -> setRes(images.get((int) (emmitCount % images.size()))),
                        throwable -> {}, () ->setRes(endImage));
    }

}
