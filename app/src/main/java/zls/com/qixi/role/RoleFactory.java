package zls.com.qixi.role;

import android.content.Context;

import zls.com.qixi.role.base.Role;
import zls.com.qixi.role.base.RoleType;
import zls.com.zlibrary.util.ScreenUtil;

/**
 * Created by oop on 2018/8/17.
 */

public class RoleFactory {

    public static Role create(Context context, RoleType type){
        switch (type){
            case Stage:
                return new Stage(context, ScreenUtil.getScreenWidth(context), ScreenUtil.getScreenHeight(context));
            case Boy:
                return new Human(context, 150, 290);
            case GIRL:
                return new Human(context, 150, 290);
        }
        return null;
    }

}
