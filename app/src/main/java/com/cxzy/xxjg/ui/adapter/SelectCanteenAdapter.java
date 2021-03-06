package com.cxzy.xxjg.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.SchoolCanteenBean;

import java.util.ArrayList;

/**
 * Created by demo on 2018/7/30.
 */

public class SelectCanteenAdapter extends BaseAdapter {

    private ArrayList<SchoolCanteenBean> canteenList = new ArrayList<>();

    public SelectCanteenAdapter(ArrayList<SchoolCanteenBean> canteenList) {
        this.canteenList = canteenList ;
    }

    @Override
    public int getCount() {
        return canteenList == null ? 0 : canteenList.size();
    }

    @Override
    public SchoolCanteenBean getItem(int i) {
        return canteenList.get(i);
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

        SchoolCanteenBean info = getItem(position);
        holder.tvCanteen.setText(info.name);

        return convertView;
    }

    static class CanteenHolder{
        TextView tvCanteen ;
    }
}
