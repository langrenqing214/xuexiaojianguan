package com.cxzy.xxjg.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxzy.xxjg.R;

/**
 * Created by demo on 2018/7/30.
 */

public class SelectCanteenAdapter extends BaseAdapter {

    public SelectCanteenAdapter(){

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CanteenHolder holder ;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_canteen , parent , false);
            holder = new CanteenHolder();
            holder.tvCanteen = convertView.findViewById(R.id.tv_select_canteen_name);
            convertView.setTag(holder);
        }else {
            holder = (CanteenHolder) convertView.getTag();
        }

        return convertView;
    }

    static class CanteenHolder{
        TextView tvCanteen ;
    }
}
