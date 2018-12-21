package zls.com.qixi.anim;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Animator {

    private static int getStep(int count, int from, int to){
        int step = (to - from) / count;
        if(step == 0){
            if(to > from){
                step = 1;
            }else if(to < from){
                step = -1;
            }
        }
        return step;
    }

    @SuppressLint("CheckResult")
    public static void exe(View view, long duration, long interval, int fromX, int toX, int fromY, int toY,
                           List<Integer> images, Integer endImage){

        if(interval < 10){
            interval = 10;
        }

        if(duration < interval){
            duration = interval;
        }

        int count = (int) (duration / interval);
        int stepX = getStep(count, fromX, toX);
        int stepY = getStep(count, fromY, toY);

        Observable
                .interval(0, interval, TimeUnit.MILLISECONDS)
                .take(count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(index ->
                        {
                            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                            lp.leftMargin += stepX;
                            lp.topMargin += stepY;
                            view.setLayoutParams(lp);
                            setRes(view, images, (int)index.longValue());
                            },
                        throwable -> {},
                        () -> {
                            setRes(view, endImage);
                        });

    }

    private static void setRes(View contentView, Integer res){
        if(res == null){
            return;
        }

        if(contentView instanceof ImageView){
            ((ImageView)contentView).setImageResource(res);
        }else {
            contentView.setBackgroundResource(res);
        }
    }


    private static void setRes(View contentView, List<Integer> images, int index){
        if(images == null || images.size() == 0){
            return;
        }

        int imageIndex = index % images.size();

        if(contentView instanceof ImageView){
            ((ImageView)contentView).setImageResource(images.get(imageIndex));
        }else {
            contentView.setBackgroundResource(images.get(imageIndex));
        }
    }

}
