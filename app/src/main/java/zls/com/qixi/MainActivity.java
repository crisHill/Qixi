package zls.com.qixi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import zls.com.qixi.listener.MainListener;
import zls.com.qixi.manager.Manager;
import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgReceiver;
import zls.com.qixi.msg.MsgType;
import zls.com.qixi.view.SettingPopUp;


public class MainActivity extends Activity implements MsgReceiver {

    FrameLayout root;
    Context context;

    private SettingPopUp popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        root = findViewById(R.id.root);

        Manager.getInstance().init(context, root);

        root.setOnTouchListener(new MainListener());

        popup = SettingPopUp.generate(context, root);
        MsgHelper.getINSTANCE().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MsgHelper.getINSTANCE().unRegister(this);
    }

    @Override
    public void onReceive(MsgType type, Object ... datas) {
        if(type == MsgType.SHOW_SETTING_UI){
            popup.show();
        }
    }
}
