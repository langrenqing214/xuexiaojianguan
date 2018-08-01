package com.cxzy.xxjg.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 时间选择器
 * Created by demo on 2018/7/30.
 */

public class SelectTimeDialog extends DatePickerDialog {

    public SelectTimeDialog(@NonNull Context context, @Nullable OnDateSetListener listener) {
        super(context, DatePickerDialog.THEME_HOLO_LIGHT, listener, 2018, 8, 1);
    }

}
