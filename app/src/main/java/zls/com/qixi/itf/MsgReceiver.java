package zls.com.qixi.itf;

/**
 * Created by oop on 2018/3/2.
 */

public interface MsgReceiver {

    void onReceive(int type, Object obj);

}
