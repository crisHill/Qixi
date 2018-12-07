package zls.com.qixi.manager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import zls.com.qixi.itf.MsgReceiver;
import zls.com.qixi.role.RoleFactory;
import zls.com.qixi.role.base.Role;
import zls.com.qixi.role.Human;
import zls.com.qixi.role.Stage;
import zls.com.qixi.role.base.RoleType;
import zls.com.qixi.script.DirectDate;
import zls.com.zlibrary.util.ScreenUtil;
import zls.com.zlibrary.view.Voicer;

/**
 * Created by oop on 2018/8/17.
 */

public class Manager implements MsgReceiver{

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
    private Map<RoleType, Role> roles = new HashMap<>();

    public void init(Context context, ViewGroup root){
        this.context = context;
        this.root = root;

        MsgManager.getINSTANCE().register(MsgManager.Type.ASK_TO_FIND_GIRL, this);

        root.addView((View) requestRole(RoleType.Stage).getContentView(), 0);
        root.addView((View) requestRole(RoleType.Boy).getContentView());
        ((ViewGroup)((ViewGroup)root.getChildAt(0)).getChildAt(1)).addView((View) requestRole(RoleType.GIRL).getContentView());
        root.addView(Voicer.create(context, 200));
    }

    @Override
    public void onReceive(int type, Object obj) {

        switch (type){
            case MsgManager.Type.ASK_TO_FIND_GIRL:
                roles.get(RoleType.Stage).move(5000, 0, -ScreenUtil.getScreenWidth(context), 0, 0);

                break;
        }

    }

    public Context getContext(){
        return context;
    }

    public Role requestRole(RoleType type){
        if(roles.get(type) == null){
            roles.put(type, RoleFactory.create(context, type));
        }
        return roles.get(type);
    }

    public void showDirectData(){
        new DirectDate().start();
    }

}
