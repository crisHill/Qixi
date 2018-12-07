package zls.com.qixi.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import zls.com.qixi.R;
import zls.com.qixi.msg.MsgHelper;
import zls.com.qixi.msg.MsgType;
import zls.com.zlibrary.util.ScreenUtil;

/**
 * Created by oop on 2018/2/28.
 */

public class SettingPopUp {

    private final float WIDTH_RATIO = 0.5F;
    private final int SPAN = 2;
    private String[] names = {
            "显示语音按钮", "隐藏语音按钮",
            "启动鲜花雨", "停止鲜花雨",
            "开始音乐", "停止音乐"
    };
    private MsgType[] types = {
            MsgType.SHOW_VOICER, MsgType.HIDE_VOICER,
            MsgType.START_FLOWER_RAIN, MsgType.STOP_FLOWER_RAIN,
            MsgType.START_MUSIC, MsgType.STOP_MUSIC
    };

    private Context context;
    private View popView;
    private PopupWindow popupWindow;
    private Button confirm;

    private View root;

    public static SettingPopUp generate(Context context, View root){
        return new SettingPopUp(context, root);
    }

    public void show(){
        popupWindow.showAtLocation(root, Gravity.LEFT, 0, 0);
    }

    private void hide(){
        popupWindow.dismiss();
    }

    private SettingPopUp(Context context, View root){
        this.context = context;
        this.root = root;
        initViews();
    }

    private void initViews() {

        popView = LayoutInflater.from(context).inflate(R.layout.setting_popup, null);
        popView.setFocusable(true);
        popView.setFocusableInTouchMode(true);
        popupWindow = new PopupWindow(popView, (int) (ScreenUtil.getScreenWidth(context) * WIDTH_RATIO), ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popupwindow_anim_style_from_left);

        confirm = popView.findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> hide());

        RecyclerView rv = popView.findViewById(R.id.rv);
        GridLayoutManager lm = new GridLayoutManager(context, SPAN);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if(position >= SPAN){
                    outRect.top = 60;
                }
                if(position % SPAN != 0){
                    outRect.left = 60;
                }
            }
        });
        rv.setAdapter(new MyAdapter());

    }

    class MyAdapter extends RecyclerView.Adapter<VH>{

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(context).inflate(R.layout.item_setting_popup, parent, false));
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.tv.setText(names[position]);
            holder.position = position;
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return names.length;
        }
    }

    class VH extends RecyclerView.ViewHolder{

        int position;
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
            itemView.setOnClickListener(v -> {
                int position = (int) v.getTag();
                MsgHelper.getINSTANCE().sendMsg(types[position]);
            });
        }
    }

}
