package zls.com.qixi.manager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import zls.com.qixi.actor.Boy;
import zls.com.qixi.actor.Girl;
import zls.com.qixi.actor.Stage;
import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgReceiver;
import zls.com.qixi.msg.MsgType;
import zls.com.zlibrary.view.Voicer;

/**
 * Created by oop on 2018/8/17.
 */

public class Manager implements MsgReceiver {

    private static Manager instance;

    public static Manager getInstance(){
        if(instance == null){
            synchronized (Manager.class){
                if(instance == null){
                    instance = new Manager();
                }
            }
        }
        return instance;
    }

    private Manager(){

    }

    private Context context;
    private ViewGroup root;
    private Stage stage;
    private Boy boy;
    private Girl girl;
    private Voicer voicer;

    public void init(Context context, ViewGroup root){
        this.context = context;
        this.root = root;

        MsgHelper.getINSTANCE().register(this);

        stage = new Stage(context);
        boy = new Boy(context);
        girl = new Girl(context);
        voicer = Voicer.create(context, 200);

        root.addView(stage.getContentView());
        root.addView(boy.getContentView());
        ((ViewGroup)stage.getContentView().getChildAt(1)).addView(girl.getContentView());
        root.addView(voicer);
    }

    @Override
    public void onReceive(MsgType type, Object ... datas) {

        if(type == MsgType.SHOW_VOICER){
            voicer.setVisibility(View.VISIBLE);
        }else if(type == MsgType.HIDE_VOICER){
            voicer.setVisibility(View.GONE);
        }

    }

    public Context getContext(){
        return context;
    }

    public void showDirectData(){
        //new DirectDate().start();
    }

}
