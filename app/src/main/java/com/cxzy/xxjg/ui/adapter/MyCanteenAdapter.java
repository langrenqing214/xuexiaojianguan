package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;

/**
 * Created by demo on 2018/7/20.
 */

public class MyCanteenAdapter extends RecyclerView.Adapter<MyCanteenAdapter.MyCanteenHolder> {

    @Override
    public MyCanteenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_canteen , parent , false);
        return new MyCanteenHolder(view);
    }

    @Override
    public void onBindViewHolder(MyCanteenHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyCanteenHolder extends RecyclerView.ViewHolder{

        TextView tvCanteenName ;
        View stateShow ;
        TextView tvStateDes ;
        TextView tvStaffName ;
        TextView tvStaffState ;
        TextView tvCheckTime ;
        TextView tvAbnormalTerm ;

        public MyCanteenHolder(View itemView) {
            super(itemView);
            tvCanteenName = itemView.findViewById(R.id.tv_canteen_name);
            stateShow = itemView.findViewById(R.id.view_state_show);
            tvStateDes = itemView.findViewById(R.id.tv_state_des);
            tvStaffName = itemView.findViewById(R.id.tv_staff_name);
            tvStaffState = itemView.findViewById(R.id.tv_staff_state);
            tvCheckTime = itemView.findViewById(R.id.tv_check_time);
            tvAbnormalTerm = itemView.findViewById(R.id.tv_abnormal_term);

        }
    }

}
