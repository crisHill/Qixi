package zls.com.qixi.anim;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CompoundAnimator {

    /*public static void exe(long duration, long interval, int fromX, int toX, int fromY, int toY,
                           List<Integer> actReses, Integer endRes){
        if(interval < 10){
            interval = 10;
        }

        if(duration < interval){
            duration = interval;
        }

        Observable
                .interval(0, interval, TimeUnit.MILLISECONDS)
                .take(duration / interval)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(emmitCount -> setRes(images.get((int) (emmitCount % images.size()))),
                        throwable -> {}, () ->setRes(endImage));
    }*/

}
