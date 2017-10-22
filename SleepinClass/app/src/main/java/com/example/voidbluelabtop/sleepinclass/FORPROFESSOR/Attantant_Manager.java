package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.Adapter.Studentlist_Adapter;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

import java.util.HashMap;

public class Attantant_Manager extends AppCompatActivity {
//    Singleton_UserDataController UDC;
    Studentlist_Adapter SA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_managestudent);
        toolbar.setTitle("출결 관리(강사용)");
        toolbar.setTitleTextColor(0x99000000);
        Bundle bundle = getIntent().getExtras();
        final String classcode = bundle.getString("classcode");
//        UDC.processAttend(classcode);
        SA = new Studentlist_Adapter(1, classcode);
        ListView listview = (ListView) findViewById(R.id.studentlist);
        listview.setAdapter(SA);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap student = (HashMap)SA.getItem(i);
                Intent intent = new Intent(getApplicationContext(), Student_attandant.class);
                intent.putExtra("classcode", classcode);
                intent.putExtra("studentname", (String)student.get("studentname"));
                intent.putExtra("studentcode", (String)student.get("studentcode"));
                intent.putExtra("studentmajor", (String)student.get("studentmajor"));
                startActivity(intent);
            }
        });
    }
}
