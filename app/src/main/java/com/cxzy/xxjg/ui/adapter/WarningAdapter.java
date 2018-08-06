package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.WarningItemBean;
import com.cxzy.xxjg.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史告警
 * Created by demo on 2018/7/20.
 */

public class WarningAdapter extends RecyclerView.Adapter<WarningAdapter.WarningHolder> {

    private List<WarningItemBean> data = new ArrayList<>();
    private DealItemClickListener mListener ;

    public WarningAdapter(DealItemClickListener mListener){
        this.mListener = mListener ;
    }


    @Override
    public WarningHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_warning , parent , false);
        return new WarningHolder(view);
    }

    @Override
    public void onBindViewHolder(WarningHolder holder, final int position) {
        WarningItemBean info = data.get(position);
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
        holder.tvStateDes.setText(info.configDesc);
        holder.tvCheckTime.setText(DateUtil.date2NYRSF(DateUtil.string2Date(info.dealDate == null ? "" : info.dealDate , "yyyy-MM-dd")));
        holder.tvAbnormalTerm.setText(info.remarks);
        holder.stateShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.dealItemClickListener(position);
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
        void dealItemClickListener(int position);
    }

    class WarningHolder extends RecyclerView.ViewHolder{

        View stateShow ;
        TextView tvStateDes ;
        TextView tvCheckTime ;
        TextView tvAbnormalTerm ;

        public WarningHolder(View itemView) {
            super(itemView);
            stateShow = itemView.findViewById(R.id.view_state_show);
            tvStateDes = itemView.findViewById(R.id.tv_state_des);
            tvCheckTime = itemView.findViewById(R.id.tv_check_time);
            tvAbnormalTerm = itemView.findViewById(R.id.tv_abnormal_term);

        }
    }

}
