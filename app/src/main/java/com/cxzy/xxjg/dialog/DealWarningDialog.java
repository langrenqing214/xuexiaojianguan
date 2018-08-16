package com.cxzy.xxjg.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理警告
 * Created by demo on 2018/7/30.
 */

public class DealWarningDialog extends Dialog implements View.OnClickListener , SelectTrialReactionDialog.SelectTrialListener {

    private Window window = null;
    private DealWarningListener itemListener ;
    private Context mContext ;
    private EditText etPerson;
    private LinearLayout llNum;
    private EditText etNum;
    private Button btnOk;
    private TextView tvDealResult;
    private EditText tvDealDes;
    private EditText etDealStep;
    private String trialState;

    public DealWarningDialog(@NonNull Context context , DealWarningListener itemListener) {
        super(context, R.style.select_canteen_dialog);
        this.itemListener = itemListener ;
        this.mContext = context ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_deal_warning_dialog);
        etPerson = findViewById(R.id.et_deal_person);
        btnOk = findViewById(R.id.btn_ok);
        tvDealResult = findViewById(R.id.tv_deal_result);
        tvDealDes = findViewById(R.id.tv_deal_result_des);
        etDealStep = findViewById(R.id.et_deal_step);

        initListener();

        /*btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.scanResult(etPerson.getText().toString().trim() , etNum.getText().toString().trim());
                dismiss();
            }
        });*/

    }

    private void initListener(){
        tvDealResult.setOnClickListener(this);
        btnOk.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_deal_result ://处置结论
                List<String> trialList = new ArrayList<>();
                trialList.add("正常");
                trialList.add("异常");
                SelectTrialReactionDialog dialog = new SelectTrialReactionDialog(mContext , trialList , this);
                dialog.show();
                break;
            case R.id.btn_ok ://提交
                String person = etPerson.getText().toString().trim();
                String dealDes = tvDealDes.getText().toString().trim();
                String dealStep = etDealStep.getText().toString().trim();
                if (TextUtils.isEmpty(person)){
                    ToastUtil.showShort(mContext , "处理人不能为空");
                    return;
                }
                if (TextUtils.isEmpty(trialState)){
                    ToastUtil.showShort(mContext , "处置结论不能为空");
                    return;
                }
                if (TextUtils.isEmpty(dealDes)){
                    ToastUtil.showShort(mContext , "处置结论说明不能为空");
                    return;
                }
                if (TextUtils.isEmpty(dealStep)){
                    ToastUtil.showShort(mContext , "处置措施不能为空");
                    return;
                }
                itemListener.dealWarning(person , trialState , dealDes , dealStep);
                dismiss();
                break;
        }
    }

    @Override
    public void selectTrialItem(int positon, String trial) {
        if ("正常".equals(trial)){
            trialState = "NORMAL";
        }else if ("异常".equals(trial)){
            trialState = "ERROR";
        }
        tvDealResult.setText(trialState);
    }


    public interface DealWarningListener{
        /**
         *
         * @param person 处理人
         * @param trialState 处理结论
         * @param dealDes 处理结论描述
         * @param dealStep 处理措施
         */
        void dealWarning(String person, String trialState , String dealDes , String dealStep);
    }
}
