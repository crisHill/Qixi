package zls.com.qixi.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;

import zls.com.qixi.bean.Flower;
import zls.com.qixi.manager.Const;
import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgReceiver;
import zls.com.qixi.msg.MsgType;

/**
 * Created by oop on 2018/2/12.
 */

public class FlowerManager implements MsgReceiver {

    private static FlowerManager INSTANCE;
    public static FlowerManager getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new FlowerManager();
        }
        return INSTANCE;
    }

    private FlowerManager(){
    }

    public void init(ViewGroup root, Context context){
        this.root = root;
        this.context = context;
        MsgHelper.getINSTANCE().register(this);
    }


    private static boolean keep_create = false;

    private ViewGroup root;
    private Context context;



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Const.MsgWhat.CREATE_FLOWER){
                create();
            }
        }
    };
    private void create(){
        new Flower(context, root).start();
    }
    public void stop(){
        keep_create = false;
    }
    public void start(){
        keep_create = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (keep_create){
                    try {
                        Thread.sleep(Const.FLOWER_CREATE_TIME);
                        handler.sendEmptyMessage(Const.MsgWhat.CREATE_FLOWER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onReceive(MsgType type, Object ... datas){
        if(type == MsgType.START_FLOWER_RAIN){
            start();
        }if(type == MsgType.STOP_FLOWER_RAIN){
            stop();
        }
    }
}
