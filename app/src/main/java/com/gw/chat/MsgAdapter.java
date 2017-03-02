package com.gw.chat;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gw.chat.bean.Msg;

import java.util.List;

/**
 * Created by gw on 2017/1/1.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> msgList;

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if(msg.getType() == Msg.TYPE_RECEIVED){
            holder.ll_left.setVisibility(View.VISIBLE);
            holder.ll_right.setVisibility(View.GONE);
            holder.tv_left.setText(msg.getContent());
        }else if(msg.getType() == Msg.TYPE_SEND){
            holder.ll_left.setVisibility(View.GONE);
            holder.ll_right.setVisibility(View.VISIBLE);
            holder.tv_right.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout ll_left;
        LinearLayout ll_right;
        TextView tv_left;
        TextView tv_right;
        public ViewHolder(View view){
            super(view);
            ll_left = (LinearLayout) view.findViewById(R.id.ll_left);
            ll_right = (LinearLayout) view.findViewById(R.id.ll_right);
            tv_left = (TextView) view.findViewById(R.id.tv_left);
            tv_right = (TextView) view.findViewById(R.id.tv_right);
        }
    }
}
