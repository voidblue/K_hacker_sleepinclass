package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.R;

import java.util.Calendar;

/**
 * Created by voidbluelabtop on 17. 9. 19.
 */

public class Dialog_datepicker extends Dialog {
    DatePicker datepicker;
    TextView date;

    public Dialog_datepicker(@NonNull Context context, TextView date) {
        super(context);
        this.date = date;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle saveinitInstance) {
        setContentView(R.layout.dialog_datepicker);

        datepicker = (DatePicker) findViewById(R.id.datePicker);

        datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                int week = cal.get(Calendar.DAY_OF_WEEK);



                Log.d("dasd", week + "dydlf");
                char dayofweek;
                if (week == 1){
                    dayofweek = '일';
                } else if (week == 2){
                    dayofweek = '월';
                }else if (week == 3){
                    dayofweek = '화';
                }else if (week == 4){
                    dayofweek = '수';
                }else if (week == 5){
                    dayofweek = '목';
                }else if (week == 6){
                    dayofweek = '금';
                }else{
                    dayofweek = '토';
                }
                date.setText(year + "년 " + month + "월 " + day + "일 " + dayofweek + "요일");
                dismiss();
            }
        });
    }
}