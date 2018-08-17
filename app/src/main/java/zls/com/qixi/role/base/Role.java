package zls.com.qixi.role.base;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oop on 2018/8/16.
 */

public abstract class Role<T> {

    //背景图片
    protected Context context;
    protected int width, height;
    protected List<Integer> drawableResList = new ArrayList<>();
    protected T contentView;

    public Role(Context context, int width, int height, T contentView, int ... reses){
        this.context = context;
        this.width = width;
        this.height = height;
        this.contentView = contentView;

        for (int res : reses){
            drawableResList.add(res);
        }
    }

    public T getContentView(){
        return contentView;
    }

    public abstract void move(long millis, int fromX, int toX, int fromY, int toY);

}
