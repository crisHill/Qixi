package zls.com.qixi.role;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import zls.com.qixi.R;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.role.base.Role;
import zls.com.qixi.util.AnimateBgChanger;
import zls.com.qixi.util.AnimateTranslationer;
import zls.com.qixi.util.CollectionUtil;

/**
 * Created by oop on 2018/8/16.
 */

public class Human extends Role<ImageView> {

    int noFlowerStill = R.mipmap.boy_no_flower_still;
    Integer[] noFlowerBoyRes = {R.mipmap.boy_no_flower_1, R.mipmap.boy_no_flower_2};

    public Human(Context context, int width, int height) {
        super(context, width, height, new ImageView(context), R.mipmap.boy_no_flower_still);
        init();
    }

    private void init() {
        ImageView root = getContentView();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        root.setLayoutParams(lp);
        root.setScaleType(ImageView.ScaleType.FIT_XY);
        root.setImageResource(drawableResList.get(0));
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
                .setRes(CollectionUtil.arr2List(noFlowerBoyRes))
                .setBgAnimationInterval(300);
        AnimateBgChanger.exe(params);
        AnimateTranslationer.exe(params);
    }
}
