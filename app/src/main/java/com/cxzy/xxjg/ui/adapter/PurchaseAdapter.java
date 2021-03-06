package com.cxzy.xxjg.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.app.MyApp;
import com.cxzy.xxjg.utils.BitmapUtil;
import com.cxzy.xxjg.utils.ToastUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by demo on 2018/7/18.
 */

public class PurchaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ADD_PIC = 0;//添加图标
    private final int SHOW_PIC = 1;//展示图片
    private List<String> datas = new ArrayList<>(5);
    private RecyclerViewItemClickListener mClickListener ;
    private DeletePicItemListener deleteListener ;
    private Context mContext ;
    private int maxNum ;


    public PurchaseAdapter(Context mContext , List<String> datas , int maxNum){
        this.datas = datas ;
        this.mContext = mContext ;
        this.maxNum = maxNum ;
    }

    public void setDatas(List<String> datas){
        this.datas = datas ;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()){
            return ADD_PIC ;
        }else {
            return SHOW_PIC;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ADD_PIC) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_pic, parent, false);
            PurchaseHolder holder = new PurchaseHolder(view);
            return holder ;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_pic, parent, false);
            return new ShowPicHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PurchaseHolder){
            PurchaseHolder purchaseHolder = (PurchaseHolder) holder;
            purchaseHolder.llAddPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (datas.size() >= maxNum){
                        ToastUtil.showShort(mContext , "不能再添加图片了");
                    }else {
                        mClickListener.onItemClick(position);
                    }
                }
            });
        }else {
            ShowPicHolder showPicHolder = (ShowPicHolder) holder;
            showPicHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteListener.onDeletePicItem(position);
                }
            });
            try {
                Bitmap newBitmap = BitmapUtil.getBitmapByPath(datas.get(position), BitmapUtil.getOptions(datas.get(position)), MyApp.width, MyApp.height);
                showPicHolder.ivShowPic.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    class PurchaseHolder extends RecyclerView.ViewHolder{
        LinearLayout llAddPic ;

        public PurchaseHolder(View itemView) {
            super(itemView);
            llAddPic = itemView.findViewById(R.id.ll_add_pic);
        }
    }

    class ShowPicHolder extends RecyclerView.ViewHolder{
        ImageView ivShowPic ;
        ImageView ivDelete ;
        public ShowPicHolder(View itemView) {
            super(itemView);
            ivShowPic = itemView.findViewById(R.id.iv_show_pic);
            ivDelete = itemView.findViewById(R.id.iv_delete_pic);
        }
    }

    public interface RecyclerViewItemClickListener{
        void onItemClick(int position);
    }

    public interface DeletePicItemListener {
        void onDeletePicItem(int position);
    }

    public void setItemClickListener(RecyclerViewItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }

    public void setDeletePicItemListener(DeletePicItemListener deleteListener){
        this.deleteListener = deleteListener ;
    }
}
