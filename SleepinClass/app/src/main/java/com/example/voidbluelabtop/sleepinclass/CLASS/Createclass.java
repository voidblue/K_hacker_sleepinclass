package com.example.voidbluelabtop.sleepinclass.CLASS;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.voidbluelabtop.sleepinclass.R;

public class Createclass extends Dialog {
    private Context Instance;

    public Createclass(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_createclass);
        Instance = this.getContext();
        Log.d("", "onCreate: " + Instance);

        Button btn_classtime, btn_accept, btn_cancel;


        btn_accept = (Button) findViewById(R.id.btn_accept );
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ET_classname, ET_classroom, ET_classpassword;
                ET_classname = findViewById(R.id.ET_classname);
                ET_classpassword = findViewById(R.id.ET_classpassword);
                ET_classroom = findViewById(R.id.ET_classroom);

                String classname, password, place;
                classname = ET_classname.getText().toString();
                password = ET_classpassword.getText().toString();
                place = ET_classroom.getText().toString();

                //TODO 데이터 전부 전달해야함, 강의코드도 계산해서 반환하기
                dismiss();
            }
        });

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btn_classtime = (Button) findViewById(R.id.btn_settime);
        btn_classtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //시작할 떄 처럼 위크뷰 다시 불러올려고 했는데 다이얼로그라 그런지 잘 안됨
                //TODO 타임픽커를 통해 해야할듯
                Settimedialog settimedialog = new Settimedialog(Instance);
                settimedialog.show();
            }
        });
    }
}
