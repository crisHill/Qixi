package zls.com.qixi.role;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import zls.com.qixi.R;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.role.base.Role;
import zls.com.qixi.util.AnimateBgChanger;
import zls.com.qixi.util.AnimateTranslationer;
import zls.com.qixi.util.CollectionUtil;

/**
 * Created by oop on 2018/8/16.
 */

public class Stage extends Role<LinearLayout> {

    public Stage(Context context, int width, int height) {
        super(context,width * 2, height, new LinearLayout(context), R.mipmap.bg1, R.mipmap.bg2);
        init();
    }

    private void init() {
        LinearLayout root = getContentView();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        root.setLayoutParams(lp);
        root.setOrientation(LinearLayout.HORIZONTAL);

        addChild();
        addChild();
    }

    private void addChild(){
        if(getContentView().getChildCount() >= 2){
            return;
        }
        RelativeLayout child = new RelativeLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width / 2, height);
        child.setLayoutParams(lp);
        child.setBackgroundResource(drawableResList.get(getContentView().getChildCount()));
        getContentView().addView(child);
    }

    @Override
    public void move(long millis, int fromX, int toX, int fromY, int toY) {
        AnimationParams params = new AnimationParams()
                .setTarget(contentView)
                .setDuration(millis)
                .setStartX(fromX)
                .setEndX(toX)
                .setStartY(fromY)
                .setEndY(toY);
        AnimateTranslationer.exe(params);
    }
}
