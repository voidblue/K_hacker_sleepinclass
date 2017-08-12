package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;

public class Inform_class_Dialog extends Dialog {
    private Context thiscontext;
    private Singleton_TempModel ST;
    private String alltime, major;
    Inform_class_Dialog Instance;
    TextView timetable;
    public Inform_class_Dialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_createclass);
        ST = Singleton_TempModel.getInstance();
        thiscontext = this.getContext();
        Log.d("", "onCreate: " + thiscontext);
        Instance = this;
        alltime = "";

        Button btn_classtime, btn_accept, btn_cancel;
        timetable = (TextView)findViewById(R.id.TV_userinput_classtime);

        btn_accept = (Button) findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ET_classname, ET_classroom, ET_classpassword;
                ET_classname = findViewById(R.id.ET_classname);
                ET_classpassword = findViewById(R.id.ET_classpassword);
                ET_classroom = findViewById(R.id.ET_classroom);

                String classname, password, place;
                classname = new String(ET_classname.getText().toString());
                password = new String(ET_classpassword.getText().toString());
                place = new String(ET_classroom.getText().toString());
                String classcode = Integer.toString(Integer.parseInt(major) + classname.hashCode());
                //클래스 네임을 클래스 코드로 바꿀것
                ST.addclass(classname, alltime, place, major, classcode ,password);
                Toast.makeText(getContext(), "강의 코드는 " + classcode + "입니다.",Toast.LENGTH_LONG).show();
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
                Settimedialog settimedialog = new Settimedialog(thiscontext, Instance);
                settimedialog.show();
            }
        });
    }

    public void settime(String day, String start, String end){
        String time = day.charAt(0) + " " + start + " ~ " + end;

        if(timetable.getText().equals("")){
            timetable.setText(time);
            alltime = time;
        }
        else{
            timetable.setText(timetable.getText() + "\n" + time);
            alltime += "\n" + time;
        }

    }

    public void setMajor(int major){
        this.major = Integer.toString(major);
    }
}
