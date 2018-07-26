package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;

/**
 * 菜谱
 * Created by demo on 2018/7/20.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu , parent , false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        if (position == 0 ){
            holder.shadow.setVisibility(View.VISIBLE);
        }else {
            holder.shadow.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MenuHolder extends RecyclerView.ViewHolder {
        ImageView ivTimeShow ;
        TextView tvMenuTime ;
        LinearLayout llEditMenu ;
        TextView tvMorningMenu ;
        TextView tvNoonMenu ;
        TextView tvNightMenu ;
        View shadow ;

        public MenuHolder(View itemView) {
            super(itemView);
            ivTimeShow = itemView.findViewById(R.id.iv_time_show);
            tvMenuTime = itemView.findViewById(R.id.tv_menu_time);
            llEditMenu = itemView.findViewById(R.id.ll_edit_menu);
            tvMorningMenu = itemView.findViewById(R.id.tv_morning_menu);
            tvNoonMenu  = itemView.findViewById(R.id.tv_noon_menu);
            tvNightMenu = itemView.findViewById(R.id.tv_night_menu);
            shadow = itemView.findViewById(R.id.view_shadow);
        }
    }
}
