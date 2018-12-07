package zls.com.qixi.actor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import zls.com.qixi.R;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.util.AnimateTranslationer;
import zls.com.zlibrary.util.ScreenUtil;

/**
 * Created by oop on 2018/8/16.
 */

public class Stage extends Actor<LinearLayout> {

    private int bg1 = R.drawable.bg1;
    private int bg2 = R.drawable.bg2;

    public Stage(Context context) {
        super(context, ScreenUtil.getScreenWidth(context) * 2, ScreenUtil.getScreenHeight(context), 0, 0, new LinearLayout(context));
    }

    @Override
    protected void init() {
        ViewGroup.MarginLayoutParams lp = new FrameLayout.LayoutParams(width, height);
        this.contentView.setLayoutParams(lp);
        this.contentView.setOrientation(LinearLayout.HORIZONTAL);
        addChild();
        addChild();
    }

    @Override
    public ActorType getActorType() {
        return ActorType.STAGE;
    }

    private void addChild(){
        if(getContentView().getChildCount() >= 2){
            return;
        }
        RelativeLayout child = new RelativeLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width / 2, height);
        child.setLayoutParams(lp);
        child.setBackgroundResource(this.contentView.getChildCount() == 0 ? R.drawable.bg1 : R.drawable.bg2);
        this.contentView.addView(child);
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
