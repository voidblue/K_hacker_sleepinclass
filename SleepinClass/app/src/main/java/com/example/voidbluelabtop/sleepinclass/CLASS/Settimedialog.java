package com.example.voidbluelabtop.sleepinclass.CLASS;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.voidbluelabtop.sleepinclass.R;

public class Settimedialog extends Dialog{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_timepicker);
        final Button btn_day, btn_timeaccept, btn_timecancel;

        btn_day = findViewById(R.id.btn_setday);
        btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 요일 설정할수 있게 뭔가 만들어볼것
                btn_day.setText("바뀐요일");
            }
        });

        btn_timeaccept = findViewById(R.id.btn_timeaccept);
        btn_timeaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 시작 시간, 종료 시간, 두번 받을수 있게해야함
                dismiss();
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

    public Settimedialog(@NonNull Context context) {
        super(context);
    }
}
