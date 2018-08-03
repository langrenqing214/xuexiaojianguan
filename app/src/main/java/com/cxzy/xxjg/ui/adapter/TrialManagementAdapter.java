package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ImageUtils;
import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.bean.TrialListBean;
import com.cxzy.xxjg.utils.BitmapUtil;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.utils.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 试吃管理
 * Created by demo on 2018/7/23.
 */

public class TrialManagementAdapter extends RecyclerView.Adapter<TrialManagementAdapter.TrialHolder> {
    private List<TrialListBean> data = new ArrayList<>();
    private Context mContext ;
    private DealTrialListener mListener ;

    public TrialManagementAdapter(Context mContext , DealTrialListener mListener){
        this.mContext = mContext ;
        this.mListener = mListener ;
    }

    @Override
    public TrialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trial , parent , false);
        return new TrialHolder(view);
    }

    @Override
    public void onBindViewHolder(TrialHolder holder, int position) {
        final TrialListBean info = data.get(position);
        holder.tvFoodName.setText(info.foodName);
        holder.tvTrialPerson.setText("试吃人:" + info.eatPerson);
        holder.tvTrialTime.setText("试吃时间:" + DateUtil.timeToAdviserTimeString(info.eatTime));
        holder.tvTrialDes.setText(info.remarks);
        ImageLoaderUtil.LoadImage(mContext , info.eatImage == null ? "" : info.eatImage , holder.ivFood);
        if (TextUtils.isEmpty(info.status)){//未出结果
            holder.tvTrialState.setText("未出结果");
            holder.tvTrialState.setTextColor(ContextCompat.getColor(mContext , R.color.green_text));
            holder.tvReactionTime.setVisibility(View.GONE);
            holder.btnTrial.setVisibility(View.VISIBLE);
        }else if ("NORMAL".equals(info.status)){//正常
            holder.tvTrialState.setText("正常");
            holder.tvTrialState.setTextColor(ContextCompat.getColor(mContext , R.color.green_text));
            holder.tvReactionTime.setVisibility(View.VISIBLE);
            holder.btnTrial.setVisibility(View.GONE);
            holder.tvReactionTime.setText("反应时间:" + DateUtil.timeToAdviserTimeString(info.statusTime));
        }else {//不正常
            holder.tvTrialState.setText("异常");
            holder.tvTrialState.setTextColor(ContextCompat.getColor(mContext , R.color.red_text));
            holder.tvReactionTime.setVisibility(View.VISIBLE);
            holder.btnTrial.setVisibility(View.GONE);
            holder.tvReactionTime.setText("反应时间:" + DateUtil.timeToAdviserTimeString(info.statusTime));
        }

        holder.btnTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.dealTrialListener(info);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<TrialListBean> data) {
        this.data = data;
    }

    public interface DealTrialListener {
        void dealTrialListener(TrialListBean info);
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
