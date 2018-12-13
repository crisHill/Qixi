package zls.com.qixi.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

import zls.com.qixi.R;
import zls.com.qixi.manager.Const;
import zls.com.qixi.manager.Vars;

/**
 * Created by oop on 2018/2/12.
 */

public class Flower {

    private int[] res = {
            R.drawable.snowflake1,
            R.drawable.snowflake2,
            R.drawable.snowflake3,
            R.drawable.snowflake4,
            R.drawable.snowflake5,
            R.drawable.snowflake6};

    private Random random;
    private Context context;
    private ViewGroup container;
    private int sleepTime;
    private ImageView ui;
    private boolean moveRight;

    public Flower(Context context, ViewGroup container){
        this.random = new Random();
        this.context = context;
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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Const.MsgWhat.FLOWER_MOVE){
                ((Flower)msg.obj).moveMe();
            }else if(msg.what == Const.MsgWhat.CLEAR_FLOWER){
                ((Flower)msg.obj).clearMe();
            }
        }
    };

    public void moveMe(){
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

    public void clearMe(){
        container.removeView(ui);
        ui = null;
    }

    private boolean moveable(){
        return ui.getY() < Vars.stageHeight;
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (moveable()){
                    try {
                        Thread.sleep(sleepTime);
                        Message msg = Message.obtain();
                        msg.what = Const.MsgWhat.FLOWER_MOVE;
                        msg.obj = Flower.this;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = Message.obtain();
                msg.what = Const.MsgWhat.CLEAR_FLOWER;
                msg.obj = Flower.this;
                handler.sendMessage(msg);
            }
        }).start();
    }



}
