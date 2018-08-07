package com.cxzy.xxjg.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.ui.adapter.SelectTrialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫一扫
 * Created by demo on 2018/7/30.
 */

public class ScanResultDialog extends Dialog {

    private Window window = null;
    private ScanResultListener itemListener ;
    private Context mContext ;
    private int type = 0 ; //0 :出库 1 ://销样 2 ://取出
    private EditText etPerson;
    private LinearLayout llNum;
    private EditText etNum;
    private Button btnOk;

    public ScanResultDialog(@NonNull Context context ,int type , ScanResultListener itemListener) {
        super(context, R.style.select_canteen_dialog);
        this.itemListener = itemListener ;
        this.mContext = context ;
        this.type = type ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_scan_rasult);
        etPerson = findViewById(R.id.et_deal_person);
        llNum = findViewById(R.id.ll_num);
        etNum = findViewById(R.id.et_num);
        btnOk = findViewById(R.id.btn_ok);
        if (type == 0){
            llNum.setVisibility(View.VISIBLE);
        }else {
            llNum.setVisibility(View.GONE);
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.scanResult(etPerson.getText().toString().trim() , etNum.getText().toString().trim());
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

    public interface ScanResultListener{
        void scanResult(String person , String num);
    }
}
