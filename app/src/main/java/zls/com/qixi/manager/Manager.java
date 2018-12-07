package zls.com.qixi.manager;

import android.content.Context;
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

    public void init(Context context, ViewGroup root){
        this.context = context;
        this.root = root;

        MsgHelper.getINSTANCE().register(this);

        stage = new Stage(context);
        boy = new Boy(context);
        girl = new Girl(context);

        root.addView(stage.getContentView());
        root.addView(boy.getContentView());
        ((ViewGroup)stage.getContentView().getChildAt(0)).addView(girl.getContentView());
        root.addView(Voicer.create(context, 200));
    }

    @Override
    public void onReceive(MsgType type, Object ... datas) {

        switch (type){
            /*case MsgManager.Type.ASK_TO_FIND_GIRL:
                roles.get(RoleType.Stage).move(5000, 0, -ScreenUtil.getScreenWidth(context), 0, 0);

                break;*/
        }

    }

    public Context getContext(){
        return context;
    }

    public void showDirectData(){
        //new DirectDate().start();
    }

}
