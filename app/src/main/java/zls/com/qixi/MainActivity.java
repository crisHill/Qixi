package zls.com.qixi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import zls.com.qixi.role.Human;
import zls.com.qixi.role.Role;
import zls.com.qixi.role.Stage;


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

        root = findViewById(R.id.root);

        findViewById(R.id.tv).setOnClickListener(v -> {
            stage = Stage.create(context);
            root.addView(stage.getContentView(), 0);

            boy = Human.create(context);
            root.addView(((Human)boy).getContentView());
        });
        findViewById(R.id.tv2).setOnClickListener(v -> {
            //stage.getContentView().scrollBy(500, 0);
            boy.move(3000, 300, 800, 100, 200);
        });
    }
}
