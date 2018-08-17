package zls.com.qixi.manager;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import zls.com.qixi.role.RoleFactory;
import zls.com.qixi.role.base.Role;
import zls.com.qixi.role.Human;
import zls.com.qixi.role.Stage;
import zls.com.qixi.role.base.RoleType;
import zls.com.qixi.script.DirectDate;

/**
 * Created by oop on 2018/8/17.
 */

public class Manager {

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
    private Map<RoleType, Role> roles = new HashMap<>();

    public void registerActivityContext(Context context){
        this.context = context;
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
