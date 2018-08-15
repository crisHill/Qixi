package zls.com.qixi;

import android.app.Activity;
import android.os.Bundle;

import zls.com.zlibrary.StringUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringUtil.isEmpty("d");
    }
}
