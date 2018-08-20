package com.cxzy.xxjg.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.WarningItemBean;
import com.cxzy.xxjg.utils.DateUtil;
import com.cxzy.xxjg.wideget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史告警
 * Created by demo on 2018/7/20.
 */

public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.WarningHolder> {

    private List<WarningItemBean> data = new ArrayList<>();
    private DealItemClickListener mListener ;

    public WarningAdapter(DealItemClickListener mListener, List<WarningItemBean> itemList){
        this.mListener = mListener ;
        this.data = itemList ;
    }


    @Override
    public WarningHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warning , parent , false);
        return new WarningHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(WarningHolder holder, final int position) {
        final WarningItemBean info = data.get(position);
        switch (info.level){
            case "ALARM" ://警告
                holder.stateShow.setBackgroundResource(R.drawable.circle_red);
                break;
            case "WARN"://警戒
                holder.stateShow.setBackgroundResource(R.drawable.circle_orange);
                break;
            case "COMPLETE"://处理
                holder.stateShow.setBackgroundResource(R.drawable.circle_green);
                break;
        }

        holder.stateShow.setImageResource(Color.parseColor(info.levelColor.trim()));

        if ("DEAL_END".equals(info.dealState)){//已处理
            holder.btnDealWarning.setVisibility(View.GONE);
        }else {//未处理
            holder.btnDealWarning.setVisibility(View.VISIBLE);
        }

        holder.tvStateDes.setText(info.configDesc);
        holder.tvCheckTime.setText(DateUtil.date2NYRSF(DateUtil.string2Date(info.dealDate == null ? "" : info.dealDate , "yyyy-MM-dd HH:mm:ss")));
        holder.tvAbnormalTerm.setText(info.remarks);
        holder.btnDealWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.dealItemClickListener(position , info);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<WarningItemBean> list) {
        this.data = list ;
    }

    public interface DealItemClickListener{
        void dealItemClickListener(int position , WarningItemBean info);
    }

    class WarningHolder extends RecyclerView.ViewHolder{

        CircleImageView stateShow ;
        TextView tvStateDes ;
        TextView tvCheckTime ;
        TextView tvAbnormalTerm ;
        Button btnDealWarning ;

        public WarningHolder(View itemView) {
            super(itemView);
            stateShow = itemView.findViewById(R.id.civ_deal);
            tvStateDes = itemView.findViewById(R.id.tv_state_des);
            tvCheckTime = itemView.findViewById(R.id.tv_check_time);
            tvAbnormalTerm = itemView.findViewById(R.id.tv_abnormal_term);
            btnDealWarning = itemView.findViewById(R.id.btn_deal_warning);
        }
    }

}
