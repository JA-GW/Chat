package com.gw.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gw on 2017/2/28.
 */

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MyViewHolder>{

    private List<String> contactList;



    public MainPageAdapter(List<String> contactList) {
        this.contactList = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);


        MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvTitle.setText(contactList.get(position));

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvContent;
        ImageView ivPic;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);

        }
    }
}
