package com.cxzy.xxjg.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.PersonsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianwei on 2018/8/2.
 */

public class PersonLabelAdapter extends RecyclerView.Adapter<PersonLabelAdapter.PersonHolder>  {

    private List<PersonsBean> data = new ArrayList<>();

    public PersonLabelAdapter(List<PersonsBean> data){
        this.data = data ;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_label , parent , false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {
        PersonsBean info = data.get(position);
        holder.tvPersonName.setText(info.personName);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PersonHolder extends RecyclerView.ViewHolder {
        TextView tvPersonName ;
        public PersonHolder(View itemView) {
            super(itemView);
            tvPersonName = itemView.findViewById(R.id.tv_person_name);
        }
    }
}
