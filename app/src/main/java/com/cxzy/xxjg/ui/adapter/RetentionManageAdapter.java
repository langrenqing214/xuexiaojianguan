package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.RetentionBean;
import com.cxzy.xxjg.bean.RetentionItemBean;
import com.cxzy.xxjg.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 留样
 * Created by demo on 2018/7/23.
 */

public class RetentionManageAdapter extends RecyclerView.Adapter<RetentionManageAdapter.RetentionHolder> {

    private List<RetentionItemBean> data = new ArrayList<>();
    private Context mContext ;

    public RetentionManageAdapter(Context mContext){
        this.mContext = mContext ;
    }


    @Override
    public RetentionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retention , parent , false);
        return new RetentionHolder(view);
    }

    @Override
    public void onBindViewHolder(RetentionHolder holder, int position) {
        RetentionItemBean info = data.get(position);
//        holder.tvDate.setText(DateUtil.date2Week(DateUtil.string2Date(info.statusTime == null ? "" : info.statusTime , "yyyy-MM-dd HH:mm")));
        holder.tvFoodName.setText(info.foodName);
        holder.tvRetentionTime.setText("留样时间:" + DateUtil.timeToSeckillTimeString(info.reservedTime == null ? "" : info.reservedTime ));
        holder.tvRetentionPerson.setText("留样人:" + info.reservedPerson);
        Long statusTime = TextUtils.isEmpty(info.reservedTime) ? 0 : Long.valueOf(info.reservedTime);
        Long expiryTime = TextUtils.isEmpty(info.expiryTime) ? 0 : Long.valueOf(info.expiryTime);
        if (statusTime - expiryTime >= 0){//正常
            holder.tvFoodState.setText("正常");
            holder.tvFoodState.setTextColor(ContextCompat.getColor(mContext , R.color.green_text));
            holder.tvExpiryTime.setText("到期时间:" + DateUtil.timeToSeckillTimeString(info.expiryTime == null ? "" : info.expiryTime ));
            holder.tvExpiryTime.setTextColor(ContextCompat.getColor(mContext , R.color.green_text));
        }else if (statusTime - expiryTime < 0){
            holder.tvFoodState.setText("已过期");
            holder.tvFoodState.setTextColor(ContextCompat.getColor(mContext , R.color.red_text));
            holder.tvExpiryTime.setText("到期时间:" + DateUtil.timeToSeckillTimeString(info.expiryTime == null ? "" : info.expiryTime));
            holder.tvExpiryTime.setTextColor(ContextCompat.getColor(mContext , R.color.red_text));
            holder.tvHandleState.setTextColor(ContextCompat.getColor(mContext , R.color.red_text));
        }

        if (info.status == 0){//未处理
            holder.tvHandleState.setText("未处理");
            holder.tvHandleState.setTextColor(ContextCompat.getColor(mContext , R.color.red_text));
            holder.btnIsHandle.setVisibility(View.VISIBLE);
        }else {
            holder.tvHandleState.setText("留样人:" + info.reservedPerson);
            holder.tvHandleState.setTextColor(ContextCompat.getColor(mContext , R.color.text_gray_color));
            holder.btnIsHandle.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<RetentionItemBean> list) {
        this.data.clear();
        this.data = list ;
    }

    class RetentionHolder extends RecyclerView.ViewHolder {

//        TextView tvDate ;
        TextView tvFoodName ;
        TextView tvFoodState ;
        TextView tvRetentionTime ;
        TextView tvRetentionPerson ;
        TextView tvExpiryTime ;
        TextView tvHandleState ;
        Button btnIsHandle ;


        public RetentionHolder(View itemView) {
            super(itemView);
//            tvDate = itemView.findViewById(R.id.tv_retention_date);
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
