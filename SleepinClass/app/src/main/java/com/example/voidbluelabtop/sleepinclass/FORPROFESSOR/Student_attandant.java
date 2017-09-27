package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

import java.util.List;

public class Student_attandant extends AppCompatActivity {
    Singleton_TempModel STM;
    Singleton_Tempdata STD;
    Activity thisinstance;
    TextView tv_date;
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


        
        final TextView tv_date = (TextView)findViewById(R.id.attand_date);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_datepicker DD = new Dialog_datepicker(thisinstance, tv_date);
                DD.show();
            }
        });


        List student = (List)STM.getStudenttable().get(STD.getStudentlistpos());
        TextView tv_studentname = (TextView)findViewById(R.id.tv_studentname);
        TextView tv_studentmajor = (TextView)findViewById(R.id.tv_studentmajor);
        TextView tv_studentcode = (TextView)findViewById(R.id.tv_studentcode);

        tv_studentname.setText((String)student.get(0));
        tv_studentmajor.setText((String)student.get(1));
        tv_studentcode.setText((String)student.get(2));
    }
}
