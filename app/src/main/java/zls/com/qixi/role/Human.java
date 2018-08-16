package zls.com.qixi.role;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import zls.com.qixi.R;
import zls.com.qixi.bean.AnimationParams;
import zls.com.qixi.util.Animationer;
import zls.com.qixi.util.CollectionUtil;

/**
 * Created by oop on 2018/8/16.
 */

public class Human extends Role<ImageView> {

    Integer[] noFlowerBoyRes = {R.mipmap.boy_no_flower_1, R.mipmap.boy_no_flower_2, R.mipmap.boy_no_flower_3};
    int index = 0;

    public static Human create(Context context){
        return new Human(context, 150, 290);
    }


    public Human(Context context, int width, int height) {
        super(context, width, height, new ImageView(context), R.mipmap.boy_no_flower_1);
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
        new Animationer(new AnimationParams(contentView, 2000, 100, 500, 100, 200, CollectionUtil.arr2List(noFlowerBoyRes), 300)).exe();
    }
}
