package zls.com.qixi.role;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import zls.com.qixi.R;
import zls.com.zlibrary.util.ScreenUtil;

/**
 * Created by oop on 2018/8/16.
 */

public class Human extends Role<ImageView> {

    public static Human create(Context context){
        return new Human(context, 150, 290);
    }


    public Human(Context context, int width, int height) {
        super(context, width, height, new ImageView(context), R.mipmap.bg1);
        init();
    }

    private void init() {
        ImageView root = getContentView();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        root.setLayoutParams(lp);
        root.setScaleType(ImageView.ScaleType.FIT_XY);
        root.setImageResource(drawableResList.get(0));
    }

}
