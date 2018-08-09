package com.cxzy.xxjg.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * 时间选择器
 * Created by demo on 2018/7/30.
 */

public class SelectTimeDialog extends DatePickerDialog {
    private static Calendar startcal = Calendar.getInstance();

    public SelectTimeDialog(@NonNull Context context, @Nullable OnDateSetListener listener) {
        super(context, DatePickerDialog.THEME_HOLO_LIGHT, listener, startcal.get(Calendar.YEAR), startcal.get(Calendar.MONTH), startcal.get(Calendar.DAY_OF_MONTH));
    }

}
