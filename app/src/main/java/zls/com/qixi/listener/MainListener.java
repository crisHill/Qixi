package zls.com.qixi.listener;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgType;

public class MainListener implements View.OnTouchListener{

    private float touchX;
    private final float moveLimit = 50;

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            touchX = motionEvent.getX();
            Log.e("ACTION_DOWN", "touchX = " + touchX);
        }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            Log.e("ACTION_UP", "motionEvent.getX() = " + motionEvent.getX());
            if(touchX <= moveLimit && motionEvent.getX() - touchX > moveLimit){
                MsgHelper.getINSTANCE().sendMsg(MsgType.SHOW_SETTING_UI);
            }

        }
        return true;
    }
}
