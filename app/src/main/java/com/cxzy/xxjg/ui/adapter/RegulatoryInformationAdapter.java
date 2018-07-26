package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cxzy.xxjg.R;

/**
 * 监管信息
 * Created by demo on 2018/7/20.
 */

public class RegulatoryInformationAdapter extends RecyclerView.Adapter<RegulatoryInformationAdapter.RegulatoryInformationHolder> {

    @Override
    public RegulatoryInformationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_regulatory_information , parent , false);
        return new RegulatoryInformationHolder(view);
    }

    @Override
    public void onBindViewHolder(RegulatoryInformationHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RegulatoryInformationHolder extends RecyclerView.ViewHolder {

        TextView tvRegulatoryTitle ;
        Button btnIsRead ;
        TextView tvReleaseDepartment ;
        TextView tvReleaseTime ;
        TextView tvReleaseDes ;

        public RegulatoryInformationHolder(View itemView) {
            super(itemView);
            tvRegulatoryTitle = itemView.findViewById(R.id.tv_regulatory_title);
            btnIsRead = itemView.findViewById(R.id.btn_is_read);
            tvReleaseDepartment = itemView.findViewById(R.id.tv_release_department);
            tvReleaseTime = itemView.findViewById(R.id.tv_release_time);
            tvReleaseDes = itemView.findViewById(R.id.tv_release_des);
        }
    }

}
