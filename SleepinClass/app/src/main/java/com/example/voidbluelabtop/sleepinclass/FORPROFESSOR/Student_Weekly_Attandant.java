package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.Adapter.Weekly_Attand_Adapter;
import com.example.voidbluelabtop.sleepinclass.R;

/**
 * Created by voidbluelabtop on 17. 9. 19.
 */

public class Student_Weekly_Attandant extends Activity{
    String studentName, studentMajor, studentCode;
    @Override
    public void onCreate(Bundle saveInitInstance) {
        super.onCreate(saveInitInstance);
        setContentView(R.layout.activity_weekly_attandant);
        Bundle bundle = getIntent().getExtras();

        studentName = bundle.getString("name");
        studentMajor = bundle.getString("major");
        studentCode = bundle.getString("code");


        TextView tv_stName = (TextView) findViewById(R.id.tv_studentname_m);
        TextView tv_stMajor = (TextView) findViewById(R.id.tv_studentmajor_m);
        TextView tv_stCode = (TextView) findViewById(R.id.tv_studentcode_m);

        tv_stName.setText(studentName);
        tv_stMajor.setText(studentMajor);
        tv_stCode.setText(studentCode);

        ListView lv_Weekly_Attendant = (ListView) findViewById(R.id.lv_weeks);
        lv_Weekly_Attendant.setAdapter(new Weekly_Attand_Adapter());
    }


}
