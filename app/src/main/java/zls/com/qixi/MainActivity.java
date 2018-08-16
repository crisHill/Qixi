package zls.com.qixi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import zls.com.qixi.role.Human;
import zls.com.qixi.role.Stage;


public class MainActivity extends Activity {

    FrameLayout root;
    Context context;
    Stage stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        root = findViewById(R.id.root);

        findViewById(R.id.tv).setOnClickListener(v -> {
            stage = Stage.create(context);
            root.addView(stage.getContentView(), 0);
            root.addView(Human.create(context).getContentView());
        });
        findViewById(R.id.tv2).setOnClickListener(v -> stage.getContentView().scrollBy(500, 0));
    }
}
