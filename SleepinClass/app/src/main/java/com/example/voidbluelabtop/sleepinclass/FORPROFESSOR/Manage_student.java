package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.Adapter.Studentlist_Adapter;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

public class Manage_student extends AppCompatActivity {

    Studentlist_Adapter SA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_managestudent);
        toolbar.setTitle("수강생 관리");
        toolbar.setTitleTextColor(0x99000000);
        setSupportActionBar(toolbar);
        SA = new Studentlist_Adapter(0, GlobalVariables.userCode);

        ListView listview = (ListView) findViewById(R.id.studentlist);
        listview.setAdapter(SA);
    }



}
