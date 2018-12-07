package zls.com.qixi.actor;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oop on 2018/8/16.
 */

public abstract class Actor<T extends View> {

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
        init();
    }

    protected abstract void init();
    public abstract ActorType getActorType();

    public T getContentView(){
        return contentView;
    }

    public abstract void move(long millis, int fromX, int toX, int fromY, int toY);

}
