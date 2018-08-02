package com.cxzy.xxjg.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.PersonsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/8/2.
 */

public class SelectPersonAdapter extends BaseAdapter {

    private List<PersonsBean> data = new ArrayList<>();

    public SelectPersonAdapter(List<PersonsBean> data) {
        this.data = data ;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PersonsBean getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonHolder holder ;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_person, parent, false);
            holder = new PersonHolder();
            holder.tvPerson = convertView.findViewById(R.id.tv_person_name);
            holder.ivIsCheck = convertView.findViewById(R.id.iv_is_check);
            convertView.setTag(holder);
        }else {
            holder = (PersonHolder) convertView.getTag();
        }

        PersonsBean info = getItem(position);
        holder.tvPerson.setText(info.personName);
        if (TextUtils.isEmpty(info.personState)){
            holder.ivIsCheck.setVisibility(View.GONE);
        }else {
            holder.ivIsCheck.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class PersonHolder {
        TextView tvPerson ;
        ImageView ivIsCheck ;
    }
}
