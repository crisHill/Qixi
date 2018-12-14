package zls.com.qixi.element.actor;

import android.content.Context;
import android.view.View;

import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgReceiver;

/**
 * Created by oop on 2018/8/16.
 */

public abstract class Actor<T extends View> implements MsgReceiver{

    protected T contentView;
    protected int width, height, initLeft, initTop;
    protected Context context;

    public Actor(Context context, int width, int height, int initLeft, int initTop, T contentView){
        this.context = context;
        this.width = width;
        this.height = height;
        this.initLeft = initLeft;
        this.initTop = initTop;
        this.contentView = contentView;
        MsgHelper.getINSTANCE().register(this);
        init();
    }

    protected abstract void init();

    public T getContentView(){
        return contentView;
    }

    public abstract void move(long millis, int fromX, int toX, int fromY, int toY);

    public void afterDestroy(){
        MsgHelper.getINSTANCE().unRegister(this);
    }

}
