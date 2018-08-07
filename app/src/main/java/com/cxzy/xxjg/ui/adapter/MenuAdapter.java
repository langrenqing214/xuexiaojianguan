package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.MenuItemBean;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.utils.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 菜谱
 * Created by demo on 2018/7/20.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private List<MenuItemBean> data = new ArrayList<>();
    private Context mContext ;
    private EditMenuClickListener editMenuClickListener ;

    public MenuAdapter(Context mContext , EditMenuClickListener editMenuClickListener){
        this.mContext = mContext ;
        this.editMenuClickListener = editMenuClickListener ;
    }


    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu , parent , false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, final int position) {
        MenuItemBean info = data.get(position);
        if (position == 0 ){
            holder.shadow.setVisibility(View.VISIBLE);
        }else {
            holder.shadow.setVisibility(View.GONE);
        }

        Long localTime = Calendar.getInstance().getTimeInMillis();
        Long releaseTime =  TextUtils.isEmpty(info.releaseTime) ? 0 : Long.valueOf(info.releaseTime);
        if (localTime - releaseTime > 3*24*60*60*1000){
            holder.ivTimeShow.setBackgroundResource(R.drawable.ico_time_gray);
            holder.llEditMenu.setVisibility(View.INVISIBLE);
        }else {
            holder.ivTimeShow.setBackgroundResource(R.drawable.ico_time);
            holder.llEditMenu.setVisibility(View.VISIBLE);
        }
        holder.tvMorningMenu.setText(info.breakfast);
        holder.tvNoonMenu.setText(info.lunch);
        holder.tvNightMenu.setText(info.dinner);
        holder.tvMenuTime.setText(DateUtil.date2yyyyMMddWeek(DateUtil.string2Date(info.releaseTime == null ? "" : info.releaseTime , "yyyy-MM-dd")));

        holder.llEditMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMenuClickListener.editMenuClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<MenuItemBean> list) {
        this.data = list ;
    }

    public interface EditMenuClickListener{
        void editMenuClickListener(int position);
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
