package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxzy.xxjg.R;

/**
 * 试吃管理
 * Created by demo on 2018/7/23.
 */

public class TrialManagementAdapter extends RecyclerView.Adapter<TrialManagementAdapter.TrialHolder> {
    @Override
    public TrialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trial , parent , false);
        return new TrialHolder(view);
    }

    @Override
    public void onBindViewHolder(TrialHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class TrialHolder extends RecyclerView.ViewHolder {
        ImageView ivFood ;
        TextView tvTrialDate ;
        TextView tvFoodName ;
        TextView tvTrialState ;
        TextView tvTrialDes ;
        TextView tvTrialPerson ;
        TextView tvTrialTime ;
        TextView tvReactionTime ;
        Button btnTrial ;


        public TrialHolder(View itemView) {
            super(itemView);
            ivFood = itemView.findViewById(R.id.iv_food);
            tvTrialDate = itemView.findViewById(R.id.tv_trial_date);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvTrialState = itemView.findViewById(R.id.tv_trial_state);
            tvTrialDes = itemView.findViewById(R.id.tv_trial_des);
            tvTrialPerson = itemView.findViewById(R.id.tv_trial_person);
            tvTrialTime = itemView.findViewById(R.id.tv_trial_time);
            tvReactionTime = itemView.findViewById(R.id.tv_reaction_time);
            btnTrial = itemView.findViewById(R.id.btn_trial);
        }
    }
}
