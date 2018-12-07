package zls.com.qixi;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Map;

import zls.com.qixi.baiduvoice.IRecogListener;
import zls.com.qixi.baiduvoice.RecogResult;
import zls.com.qixi.baiduvoice.VoiceRecognizer;
import zls.com.qixi.itf.MsgReceiver;
import zls.com.qixi.listener.MainListener;
import zls.com.qixi.manager.Manager;
import zls.com.qixi.manager.MsgManager;
import zls.com.qixi.role.Human;
import zls.com.qixi.role.base.Role;
import zls.com.qixi.role.Stage;
import zls.com.qixi.role.base.RoleType;
import zls.com.qixi.view.SettingPopUp;


public class MainActivity extends Activity implements MsgReceiver{

    FrameLayout root;
    Context context;
    Stage stage;
    Role boy;

    VoiceRecognizer myRecognizer;
    private SettingPopUp popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        root = findViewById(R.id.root);

        Manager.getInstance().init(context, root);

        root.setOnTouchListener(new MainListener());
        //root.addView((View) Manager.getInstance().requestRole(RoleType.Stage).getContentView(), 0);
        //root.addView((View) Manager.getInstance().requestRole(RoleType.Boy).getContentView());

        /*findViewById(R.id.tv).setOnClickListener(v -> {
            Manager.getInstance().showDirectData();
        });*/

        popup = SettingPopUp.generate(context, root);
        MsgManager.getINSTANCE().register(MsgManager.Type.SHOW_SETTING_UI, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MsgManager.getINSTANCE().unRegister(MsgManager.Type.SHOW_SETTING_UI, this);
    }

    @Override
    public void onReceive(int type, Object obj) {
        if(type == MsgManager.Type.SHOW_SETTING_UI){
            popup.show();
        }
    }
}
