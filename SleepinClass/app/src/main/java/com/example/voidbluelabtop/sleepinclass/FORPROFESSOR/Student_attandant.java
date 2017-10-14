package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voidbluelabtop.sleepinclass.Adapter.Student_Attand_Adapter;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

import java.util.List;

public class Student_attandant extends AppCompatActivity {
    Singleton_TempModel STM;
    Singleton_Tempdata STD;
    Activity thisinstance;
    TextView tv_date;
    List student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attandant);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_studentattandant);
        setSupportActionBar(toolbar);
        toolbar.setTitle("출결 내역");
        toolbar.setTitleTextColor(0x99000000);


        STM = Singleton_TempModel.getInstance();
        STD = Singleton_Tempdata.getInstance();
        thisinstance = this;


        
        tv_date = (TextView)findViewById(R.id.attand_date);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_datepicker DD = new Dialog_datepicker(thisinstance, tv_date);
                DD.show();
            }
        });


        student = (List)STM.getStudenttable().get(STD.getStudentlistpos());
        TextView tv_studentname = (TextView)findViewById(R.id.tv_studentname);
        TextView tv_studentmajor = (TextView)findViewById(R.id.tv_studentmajor);
        TextView tv_studentcode = (TextView)findViewById(R.id.tv_studentcode);

        tv_studentname.setText((String)student.get(0));
        tv_studentmajor.setText((String)student.get(1));
        tv_studentcode.setText((String)student.get(2));

        TextView tv_monthlyattandant = (TextView) findViewById(R.id.showMonthlyAttandant);
        tv_monthlyattandant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Student_monthly_attandant.class);
                i.putExtra("name", (String)student.get(0));
                i.putExtra("major", (String)student.get(1));
                i.putExtra("name", (String)student.get(2));
                startActivity(i);
            }
        });

        ListView lv_attand = (ListView) findViewById(R.id.lv_attand);
        lv_attand.setAdapter(new Student_Attand_Adapter());
    }
}
