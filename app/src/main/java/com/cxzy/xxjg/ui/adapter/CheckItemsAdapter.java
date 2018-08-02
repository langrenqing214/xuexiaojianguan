package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.ItemsBean;
import com.cxzy.xxjg.ui.activitys.HealthExaminationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 卫生检查项
 * Created by demo on 2018/8/2.
 */

public class CheckItemsAdapter extends BaseAdapter {

    private Context mContext ;
    private List<ItemsBean> data = new ArrayList<>();

    public CheckItemsAdapter(Context mContext, List<ItemsBean> itemList) {
        this.mContext = mContext ;
        this.data = itemList ;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ItemsBean getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckitemHolder holder ;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.check_items , parent , false);
            holder = new CheckitemHolder();
            holder.tvCheckItem = convertView.findViewById(R.id.tv_check_item);
            convertView.setTag(holder);
        }else {
            holder = (CheckitemHolder) convertView.getTag();
        }

        ItemsBean info = getItem(position);
        holder.tvCheckItem.setText(info.typeItemName);
        return convertView;
    }

    class CheckitemHolder {
        TextView tvCheckItem ;
    }
}
