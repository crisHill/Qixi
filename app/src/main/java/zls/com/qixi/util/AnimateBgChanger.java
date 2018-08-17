package zls.com.qixi.util;

import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import zls.com.qixi.bean.AnimationParams;

/**
 * Created by oop on 2018/8/17.
 */

public class AnimateBgChanger {

    public static void exe(AnimationParams params){
        if(!params.hasBgAnimation()){
            return;
        }
        new AnimateBgChanger(params).animate();
    }

    private AnimationParams params;
    private Disposable disposable;

    public AnimateBgChanger(AnimationParams params){
        this.params = params;
    }

    public void animate(){
        int count = (int)(params.getDuration() / params.getBgAnimationInterval());
        disposable = Flowable.intervalRange(0, count, 0, params.getBgAnimationInterval(), TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
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
                })
                .doOnComplete(() -> {
                    if(disposable != null){
                        disposable.dispose();
                    }
                })
                .subscribe();
    }

}
