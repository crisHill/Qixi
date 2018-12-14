package zls.com.qixi.element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import zls.com.qixi.R;
import zls.com.qixi.controll.Const;
import zls.com.qixi.controll.Manager;
import zls.com.qixi.controll.Vars;

/**
 * Created by oop on 2018/2/12.
 */

public class Flower {

    private static int[] res = {
            R.drawable.snowflake1,
            R.drawable.snowflake2,
            R.drawable.snowflake3,
            R.drawable.snowflake4,
            R.drawable.snowflake5,
            R.drawable.snowflake6
    };
    private static boolean raining = false;

    public static void start(ViewGroup container){
        if(raining){
            return;
        }
        raining = true;
        Observable
                .interval(Const.FLOWER_CREATE_TIME, TimeUnit.MILLISECONDS)
                .takeUntil(aLong -> !raining)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext -> new Flower(Manager.getInstance().getContext(), container).start());
    }
    public static void stop(){
        raining = false;
    }



    private Random random;
    private ViewGroup container;
    private int sleepTime;
    private ImageView ui;
    private boolean moveRight;

    private Flower(Context context, ViewGroup container){
        this.random = new Random();
        this.container = container;
        this.sleepTime = (random.nextInt(4) + 1) * 20 + 100;

        Bitmap bg = BitmapFactory.decodeResource(context.getResources(), res[random.nextInt(res.length)]);
        int width = bg.getWidth();
        int height = bg.getHeight();

        ui = new ImageView(context);
        ui.setImageBitmap(bg);
        ui.setAlpha((float) (((random.nextInt(80) + 10) * 1.0) / 100));
        ui.setX(random.nextInt(Vars.stageWidth / 2 - 1 - width) + 1);
        ui.setY(0);
        ui.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        moveRight = random.nextBoolean();

        container.addView(ui);
    }

    private void moveMe(){
        int index = random.nextInt(Const.FLOWER_DIRECTION_CHANGE);
        if(index == 0){//X分之一的概率改变方向
            moveRight = !moveRight;
        }
        if(ui.getX() <= 1){
            moveRight = true;
        }else if(ui.getX() + ui.getWidth() >= Vars.stageWidth / 2 - 1){
            moveRight = false;
        }

        int stepX = moveRight ? Const.FLOWER_STEP_X : 0 - Const.FLOWER_STEP_X;
        int stepY = Const.FLOWER_STEP_Y;
        ui.setX(ui.getX() + stepX);
        ui.setY(ui.getY() + stepY);

        ui.setPivotX(ui.getWidth()/2);
        ui.setPivotY(ui.getHeight()/2);//支点在图片中心
        ui.setRotation(ui.getRotation() + Const.FLOWER_PIVOT_ANGLE);
    }

    private void clearMe(){
        container.removeView(ui);
        ui = null;
        container = null;
    }

    private void start(){
        Observable
                .interval(sleepTime, TimeUnit.MILLISECONDS)
                .takeUntil(aLong -> ui.getY() >= Vars.stageHeight)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext -> moveMe(), onError -> clearMe(), () -> clearMe());
    }



}
