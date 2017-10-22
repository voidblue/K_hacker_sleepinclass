package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.Adapter.Student_Attand_Adapter;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

import java.util.Date;
import java.util.List;

public class Student_attandant extends AppCompatActivity {

    private Singleton_UserDataController UDC;
    private Activity thisinstance;
    private TextView tv_date;
    private List student;
    private Bundle bundle;
    ListView lv_attend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendant);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_studentattandant);
        setSupportActionBar(toolbar);
        toolbar.setTitle("출결 내역");
        toolbar.setTitleTextColor(0x99000000);

        bundle = getIntent().getExtras();
        UDC = Singleton_UserDataController.getInstance();

        String classocde = bundle.getString("classcode");
        thisinstance = this;


        
        tv_date = (TextView)findViewById(R.id.attand_date);
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
        String strDate = date.getYear()+1900+ "년 " + date.getMonth() + "월 " + date.getDate() +"일 " + day +"요일";
        tv_date.setText(strDate);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_datepicker DD = new Dialog_datepicker(thisinstance, tv_date);
                DD.show();
                lv_attend.setAdapter(new Student_Attand_Adapter(bundle.getString("studentcode"), tv_date.getText().toString()));
            }
        });


        TextView tv_studentname = (TextView)findViewById(R.id.tv_studentname);
        TextView tv_studentmajor = (TextView)findViewById(R.id.tv_studentmajor);
        TextView tv_studentcode = (TextView)findViewById(R.id.tv_studentcode);

        tv_studentname.setText(bundle.getString("studentname"));
        tv_studentmajor.setText(bundle.getString("studentmajor"));
        tv_studentcode.setText(bundle.getString("studentcode"));

        TextView tv_monthlyattandant = (TextView) findViewById(R.id.showMonthlyAttandant);
        tv_monthlyattandant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Student_Weekly_Attandant.class);
                i.putExtra("studentname", bundle.getString("studentname"));
                i.putExtra("studentmajor", bundle.getString("studentmajor"));
                i.putExtra("studentcode", bundle.getString("studentcode"));
                startActivity(i);
            }
        });

        lv_attend = (ListView) findViewById(R.id.lv_attand);
        lv_attend.setAdapter(new Student_Attand_Adapter(bundle.getString("studentcode"), strDate));
    }
}
