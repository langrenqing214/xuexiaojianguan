package com.cxzy.xxjg.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.cxzy.xxjg.R;
import com.cxzy.xxjg.bean.PersonsBean;
import com.cxzy.xxjg.ui.adapter.SelectPersonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择人
 * Created by demo on 2018/8/2.
 */

public class SelectPersonDialog extends Dialog {

    private ListView lvSelectPerson;
    private SelectPersonAdapter mApapter;
    private SelectPersonClickLister clickLister ;
    private List<PersonsBean> data = new ArrayList<>();
    private List<PersonsBean> needReturn = new ArrayList<>();
    private Button btnoK;
    private int type ; // 0通过  1未通过
    private PersonsBean bean = new PersonsBean();

    public SelectPersonDialog(@NonNull Context context , int type ,List<PersonsBean> data , SelectPersonClickLister clickLister) {
        super(context ,  R.style.select_canteen_dialog);
        this.clickLister = clickLister ;
        this.data = data ;
        this.type = type ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_person_dialog);
        lvSelectPerson = findViewById(R.id.lv_select_person);
        btnoK = findViewById(R.id.btn_is_ok);
        mApapter = new SelectPersonAdapter(data);
        lvSelectPerson.setAdapter(mApapter);
        lvSelectPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (TextUtils.isEmpty(data.get(i).personState)) {
                    if (type == 0) {
                        data.get(i).personState = "NORMAL" ;
                    }else {
                        data.get(i).personState = "ERROR" ;
                    }
                    needReturn.add(data.get(i));
                }else {
                    data.get(i).personState = "";
                    needReturn.remove(data.get(i));
                }
                bean = data.get(i);
                mApapter.notifyDataSetChanged();
            }
        });

        btnoK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLister.selectItemPerson(needReturn);
                dismiss();
            }
        });
        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                for (PersonsBean info : needReturn){
                    info.personState = "";
                }
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

    public interface SelectPersonClickLister{
        void selectItemPerson(List<PersonsBean> infoList);
    }
}
