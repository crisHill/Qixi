package zls.com.qixi.element.actor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import zls.com.qixi.R;
import zls.com.qixi.anim.Animator;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.controll.Const;
import zls.com.qixi.controll.Vars;
import zls.com.qixi.msg.MsgType;
import zls.com.qixi.util.AnimateTranslationer;
import zls.com.qixi.util.CollectionUtil;
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
        Vars.stageWidth = width;
        Vars.stageHeight = height;
        ViewGroup.MarginLayoutParams lp = new FrameLayout.LayoutParams(width, height);
        this.contentView.setLayoutParams(lp);
        this.contentView.setOrientation(LinearLayout.HORIZONTAL);
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
        child.setBackgroundResource(this.contentView.getChildCount() == 0 ? R.drawable.bg1 : R.drawable.bg2);
        this.contentView.addView(child);
    }

    /*@Override
    public void move(long millis, int fromX, int toX, int fromY, int toY) {
        translate(millis, fromX, toX, fromY, toY);
    }*/
    @Override
    public void move(long millis, int fromX, int toX, int fromY, int toY) {
        Animator.exe(contentView, Const.SCRIPT1_FIND_GIRL_DURATION, 30, 0, -1000, 0, 0, null, null);
    }

    @Override
    public void onReceive(MsgType type, Object... datas) {
        if(type == MsgType.SCRIPT1_FIND_GIRL){
            move(Const.SCRIPT1_FIND_GIRL_DURATION, 0, 0 - Vars.stageWidth / 2, 0, 0);
        }
    }
}
