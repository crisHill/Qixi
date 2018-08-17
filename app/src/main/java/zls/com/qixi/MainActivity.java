package zls.com.qixi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import zls.com.qixi.manager.Manager;
import zls.com.qixi.role.Human;
import zls.com.qixi.role.base.Role;
import zls.com.qixi.role.Stage;
import zls.com.qixi.role.base.RoleType;


public class MainActivity extends Activity {

    FrameLayout root;
    Context context;
    Stage stage;
    Role boy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Manager.getInstance().registerActivityContext(context);

        root = findViewById(R.id.root);
        root.addView((View) Manager.getInstance().requestRole(RoleType.Stage).getContentView(), 0);
        root.addView((View) Manager.getInstance().requestRole(RoleType.Boy).getContentView());

        findViewById(R.id.tv).setOnClickListener(v -> {
            Manager.getInstance().showDirectData();
        });

    }
}
