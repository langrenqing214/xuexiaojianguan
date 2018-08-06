package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.base.ScanResultBean;
import com.cxzy.xxjg.bean.ResultItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫描结果
 * Created by demo on 2018/8/6.
 */

public class ScanResultAdapter extends BaseAdapter {

    private Context mContext ;
    private List<ResultItemBean> data = new ArrayList<>();

    public ScanResultAdapter(Context mContext , List<ResultItemBean> data){
        this.mContext = mContext ;
        this.data = data ;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ResultItemBean getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScanResultHolder holder ;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scan_result , parent , false);
            holder = new ScanResultHolder();
            holder.tvKey = convertView.findViewById(R.id.tv_scan_key);
            holder.tvValue = convertView.findViewById(R.id.tv_scan_value);
            convertView.setTag(holder);
        }else {
            holder = (ScanResultHolder) convertView.getTag();
        }

        ResultItemBean info = getItem(position);
        holder.tvValue.setText(info.value);
        holder.tvKey.setText(info.name + ":");

        return convertView;
    }

    class ScanResultHolder {
        TextView tvKey ;
        TextView tvValue ;
    }
}
