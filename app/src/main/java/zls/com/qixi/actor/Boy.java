package zls.com.qixi.actor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import zls.com.qixi.R;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.manager.Vars;
import zls.com.qixi.msg.MsgType;
import zls.com.qixi.util.AnimateBgChanger;
import zls.com.qixi.util.AnimateTranslationer;
import zls.com.qixi.util.CollectionUtil;

public class Boy extends Actor<ImageView>{

    private int withoutFlowerStillRes = R.drawable.boy_no_flower_still;
    private int withFlowerStillRes = R.drawable.boy_with_flower_still;
    private Integer[] withoutFlowerReses = {R.drawable.boy_no_flower_1, R.drawable.boy_no_flower_2};
    private Integer[] withFlowerReses = {R.drawable.boy_with_flower_1, R.drawable.boy_with_flower_2, R.drawable.boy_with_flower_3};

    public Boy(Context context) {
        super(context, 150, 290, 100, 100, new ImageView(context));
    }

    @Override
    protected void init() {
        ViewGroup.MarginLayoutParams lp = new FrameLayout.LayoutParams(width, height);
        lp.leftMargin = initLeft;
        lp.topMargin = initTop;
        this.contentView.setLayoutParams(lp);
        this.contentView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.contentView.setImageResource(R.drawable.boy_no_flower_still);//此时还在工作方法里，成员变量未完成初始化，不能使用
    }

    @Override
    public ActorType getActorType() {
        return ActorType.BOY;
    }

    @Override
    public void move(long millis, int fromX, int toX, int fromY, int toY) {
        AnimationParams params = new AnimationParams()
                .setTarget(contentView)
                .setDuration(millis)
                .setStartX(fromX)
                .setEndX(toX)
                .setStartY(fromY)
                .setEndY(toY)
                .setRes(CollectionUtil.arr2List(withoutFlowerReses))
                .setBgAnimationInterval(300);
        AnimateBgChanger.exe(params);
        AnimateTranslationer.exe(params);
    }

    @Override
    public void onReceive(MsgType type, Object... datas) {
        if(type == MsgType.SCRIPT1_FIND_GIRL){
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)this.contentView.getLayoutParams();
            /*int fromX = (int) (lp.leftMargin + Vars.stageWidth * 0.25);
            int toX = (int) (lp.leftMargin + Vars.stageWidth * 0.4);
            int fromY = (int) (lp.topMargin + Vars.stageWidth * 0.5);
            int toY = (int) (lp.leftMargin + Vars.stageWidth * 0.5);*/
            int fromX = (int) ( Vars.stageWidth * 0.25);
            int toX = (int) (Vars.stageWidth * 0.4);
            int fromY = lp.topMargin;
            int toY = lp.topMargin;
            move(4000, fromX, toX, fromY, toY);
        }
    }
}
