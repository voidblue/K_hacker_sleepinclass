package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_dropdown_item;

public class Settimedialog extends Dialog{
    private String day;
    private Dialog Owner;
    TimePicker starttime, endtime;

    public Settimedialog(@NonNull Context context, Dialog Owner) {
        super(context);
        this.Owner = Owner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_timepicker);
        final Button btn_timeaccept, btn_timecancel;
        final Spinner setday;
        setday = (Spinner) findViewById(R.id.SP_setday);

        starttime = (TimePicker) findViewById(R.id.TP_starttime);
        endtime = (TimePicker) findViewById(R.id.TP_endtime);

        List week = new ArrayList<String>();
        week.add("[요일선택]");week.add("월요일");week.add("화요일");
        week.add("수요일");week.add("목요일");week.add("금요일");
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), simple_spinner_dropdown_item, week);
        setday.setAdapter(adapter);

        btn_timeaccept = findViewById(R.id.btn_timeaccept);
        btn_timeaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 시작 시간, 종료 시간, 두번 받을수 있게해야함
                if (setday.getSelectedItem().equals("[요일선택]")){
                    Toast.makeText(getContext(),"요일을 선택해주세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    day = (String)setday.getSelectedItem();
                    String hour, minute;
                    if (starttime.getHour() < 10){
                        hour = "0" + Integer.toString(starttime.getHour());
                    }
                    else{
                        hour = Integer.toString(starttime.getHour());
                    }
                    if (starttime.getMinute() < 10){
                        minute = "0" + Integer.toString(starttime.getMinute());
                    }
                    else{
                        minute = Integer.toString(starttime.getMinute());
                    }
                    String start = hour + " : " + minute;
                    if (endtime.getHour() < 10){
                        hour = "0" + Integer.toString(endtime.getHour());
                    }
                    else{
                        hour = Integer.toString(endtime.getHour());
                    }
                    if (endtime.getMinute() < 10){
                        minute = "0" + Integer.toString(endtime.getMinute());
                    }
                    else{
                        minute = Integer.toString(endtime.getMinute());
                    }
                    String end = hour + " : " + minute;
                    ((Inform_class_Dialog)Owner).settime(day, start, end);
                    dismiss();
                }
            }
        });
        btn_timecancel = findViewById(R.id.btn_timecancel);
        btn_timecancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }



}
