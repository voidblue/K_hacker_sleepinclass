package com.example.voidbluelabtop.sleepinclass.FORSTUDENT;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.Adapter.Student_Attand_Adapter;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.FORPROFESSOR.Dialog_datepicker;
import com.example.voidbluelabtop.sleepinclass.FORPROFESSOR.Student_Weekly_Attandant;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.util.Date;

public class Attendant extends AppCompatActivity {
    Singleton_UserDataController UDC;
    ListView lv_attend;
    Attendant thisinstance;
    TextView tv_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendent);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_attendant);
        setSupportActionBar(toolbar);
        toolbar.setTitle("출결 내역");
        toolbar.setTitleTextColor(0x99000000);

        thisinstance = this;
        UDC = Singleton_UserDataController.getInstance();
        tv_date = (TextView)findViewById(R.id.attand_date_st);

        Date date = new Date();
        String day;
        if(date.getDay() == 0 ){
            day = "일";
        }else if(date.getDay() == 1){
            day = "월";
        }else if(date.getDay() == 2){
            day = "화";
        }else if(date.getDay() == 3){
            day = "수";
        }else if(date.getDay() == 4){
            day = "목";
        }else if(date.getDay() == 5){
            day = "금";
        }else{
            day = "토";
        }

        lv_attend = (ListView)findViewById(R.id.lv_attand_st);
        String strDate = date.getYear()+1900+ "년 " + date.getMonth() + "월 " + date.getDate() +"일 " + day +"요일";
        tv_date.setText(strDate);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_datepicker DD = new Dialog_datepicker(thisinstance, tv_date);
                DD.show();
                lv_attend.setAdapter(new Student_Attand_Adapter(GlobalVariables.userCode, tv_date.getText().toString()));
            }
        });


        TextView tv_studentname = (TextView)findViewById(R.id.tv_studentname_st);
        TextView tv_studentmajor = (TextView)findViewById(R.id.tv_studentmajor_st);
        TextView tv_studentcode = (TextView)findViewById(R.id.tv_studentcode_st);

        tv_studentname.setText("김재윤");
        tv_studentmajor.setText("컴퓨터공학");
        tv_studentcode.setText("2013108181");

        TextView tv_monthlyattandant = (TextView) findViewById(R.id.showMonthlyAttandant_st);
        tv_monthlyattandant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Student_Weekly_Attandant.class);
                i.putExtra("studentname", "김재윤");
                i.putExtra("studentmajor", "컴퓨터공학");
                i.putExtra("studentcode", "2013108181");
                startActivity(i);
            }
        });

        lv_attend = (ListView) findViewById(R.id.lv_attand_st);
        lv_attend.setAdapter(new Student_Attand_Adapter("NETWORK", strDate));
    }
}
