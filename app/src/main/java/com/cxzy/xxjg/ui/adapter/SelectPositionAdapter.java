package com.cxzy.xxjg.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.SchoolCanteenBean;
import com.cxzy.xxjg.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/7/30.
 */

public class SelectPositionAdapter extends BaseAdapter {

    private List<VideoBean> canteenList = new ArrayList<>();

    public SelectPositionAdapter(List<VideoBean> canteenList) {
        this.canteenList = canteenList ;
    }

    @Override
    public int getCount() {
        return canteenList == null ? 0 : canteenList.size();
    }

    @Override
    public VideoBean getItem(int i) {
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

        VideoBean info = getItem(position);
        holder.tvCanteen.setText(info.position);

        return convertView;
    }

    static class CanteenHolder{
        TextView tvCanteen ;
    }
}
