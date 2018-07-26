package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cxzy.xxjg.R;

/**
 * 留样
 * Created by demo on 2018/7/23.
 */

public class RetentionManageAdapter extends RecyclerView.Adapter<RetentionManageAdapter.RetentionHolder> {
    @Override
    public RetentionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retention , parent , false);
        return new RetentionHolder(view);
    }

    @Override
    public void onBindViewHolder(RetentionHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RetentionHolder extends RecyclerView.ViewHolder {

        TextView tvDate ;
        TextView tvFoodName ;
        TextView tvFoodState ;
        TextView tvRetentionTime ;
        TextView tvRetentionPerson ;
        TextView tvExpiryTime ;
        TextView tvHandleState ;
        Button btnIsHandle ;


        public RetentionHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_retention_date);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodState = itemView.findViewById(R.id.tv_food_state);
            tvRetentionTime = itemView.findViewById(R.id.tv_retention_time);
            tvRetentionPerson = itemView.findViewById(R.id.tv_retention_person);
            tvExpiryTime = itemView.findViewById(R.id.tv_expiry_time);
            tvHandleState = itemView.findViewById(R.id.tv_handle_state);
            btnIsHandle = itemView.findViewById(R.id.btn_is_handle);
        }
    }
}
