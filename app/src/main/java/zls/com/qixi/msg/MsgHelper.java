package zls.com.qixi.msg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MsgHelper {

    private static MsgHelper INSTANCE;
    public static MsgHelper getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new MsgHelper();
        }
        return INSTANCE;
    }

    private MsgHelper(){}

    private List<MsgReceiver> msgReceivers = new LinkedList();

    public void register(MsgReceiver receiver){
        if(receiver == null){
            return;
        }

        if(msgReceivers.contains(receiver)){
            return;
        }

        msgReceivers.add(receiver);
    }

    public void unRegister(MsgReceiver receiver){
        if(receiver == null){
            return;
        }

        if(!msgReceivers.contains(receiver)){
            return;
        }

        msgReceivers.remove(receiver);
    }

    public void sendMsg(MsgType type, Object ... datas){
        for (MsgReceiver receiver : msgReceivers){
            receiver.onReceive(type, datas);
        }
    }

}
