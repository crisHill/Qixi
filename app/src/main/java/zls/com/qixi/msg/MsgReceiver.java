package zls.com.qixi.msg;

public interface MsgReceiver {

    void onReceive(MsgType type, Object ... datas);

}
