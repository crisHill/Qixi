package zls.com.qixi.element.actor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import zls.com.qixi.R;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.msg.MsgType;
import zls.com.qixi.util.AnimateBgChanger;
import zls.com.qixi.util.AnimateTranslationer;
import zls.com.qixi.util.CollectionUtil;

public class Girl extends Actor<ImageView>{

    private int stillRes = R.drawable.girl_still;
    private Integer[] turningReses = {R.drawable.girl_turn_1, R.drawable.girl_turn_2, R.drawable.girl_turn_3, R.drawable.girl_turn_4};

    public Girl(Context context) {
        super(context, 150, 290, 500, 200, new ImageView(context));
    }

    @Override
    protected void init() {
        ViewGroup.MarginLayoutParams lp = new RelativeLayout.LayoutParams(width, height);
        lp.leftMargin = initLeft;
        lp.topMargin = initTop;
        this.contentView.setLayoutParams(lp);
        this.contentView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.contentView.setImageResource(R.drawable.girl_still);
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
                .setRes(CollectionUtil.arr2List(turningReses))
                .setBgAnimationInterval(300);
        AnimateBgChanger.exe(params);
        AnimateTranslationer.exe(params);
    }

    @Override
    public void onReceive(MsgType type, Object... datas) {

    }
}
