package com.cxzy.xxjg.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.PurchaseBean;
import com.cxzy.xxjg.ui.adapter.SelectSupplierAdapter;
import com.cxzy.xxjg.ui.adapter.SelectTrialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择供应商
 * Created by demo on 2018/7/30.
 */

public class SelectSupplierDialog extends Dialog {
    ListView lvSelectCanteen ;

    private Window window = null;
    private SelectSupplierListener itemListener ;
    private Context mContext ;
    private List<PurchaseBean> canteenList = new ArrayList<>();

    public SelectSupplierDialog(@NonNull Context context , List<PurchaseBean> canteenList , SelectSupplierListener itemListener) {
        super(context, R.style.select_canteen_dialog);
        this.itemListener = itemListener ;
        this.mContext = context ;
        this.canteenList = canteenList ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_canteen_dialog);
        lvSelectCanteen = findViewById(R.id.lv_select_canteen);
        SelectSupplierAdapter mAdapter = new SelectSupplierAdapter(canteenList);
        lvSelectCanteen.setAdapter(mAdapter);
        lvSelectCanteen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemListener.selectSupplierItem(i , canteenList.get(i));
                dismiss();
            }
        });
    }

    @Override
    public void show() {

//        windowDeploy();

        // 设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
        super.show();
    }

    // 设置窗口显示
    public void windowDeploy() {
        window = getWindow(); // 得到对话框
        window.setWindowAnimations(R.style.BottomMenuAnim); // 设置窗口弹出动画
        // window.setBackgroundDrawableResource(R.color.vifrification);
        // //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        // 根据x，y坐标设置窗口需要显示的位置
        // wl.x = x; // x小于0左移，大于0右移
        // wl.y = y; // y小于0上移，大于0下移
        wl.x = 0;
        wl.y = window.getWindowManager().getDefaultDisplay().getHeight();
        // wl.alpha = 0.6f; //设置透明度
//        wl.gravity = Gravity.BOTTOM; //设置重力
        window.setAttributes(wl);
    }

    public interface SelectSupplierListener{
        void selectSupplierItem(int positon, PurchaseBean info);
    }
}
