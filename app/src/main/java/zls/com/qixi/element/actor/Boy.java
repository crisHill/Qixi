package zls.com.qixi.element.actor;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.util.Property;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import zls.com.qixi.R;
import zls.com.qixi.anim.Animator;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.controll.Const;
import zls.com.qixi.controll.Vars;
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


   /* @Override
    public void move(long millis, int fromX, int toX, int fromY, int toY) {
        translate(millis, fromX, toX, fromY, toY);
        bgSchedule(millis, 300, CollectionUtil.arr2List(withoutFlowerReses), withFlowerStillRes);
    }*/

    @Override
    public void move(long millis, int fromX, int toX, int fromY, int toY) {
        Animator.exe(contentView, Const.SCRIPT1_FIND_GIRL_DURATION, 30,
                0, 500, 0, 0, CollectionUtil.arr2List(withoutFlowerReses), withoutFlowerStillRes);
    }

    @Override
    public void onReceive(MsgType type, Object... datas) {
        if(type == MsgType.SCRIPT1_FIND_GIRL){
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)this.contentView.getLayoutParams();
            move(Const.SCRIPT1_FIND_GIRL_DURATION,
                    0, (int) (Vars.stageWidth / 2 * 0.3), lp.topMargin, lp.topMargin);
        }
    }
}
