package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.STUDENTLIST.Studentlist_adapter;

public class Manage_student extends AppCompatActivity {

    Studentlist_adapter SA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_managestudent);
        toolbar.setTitle("출결 캡스");
        toolbar.setTitleTextColor(0x99000000);
        setSupportActionBar(toolbar);
        SA = new Studentlist_adapter(0);

        ListView listview = (ListView) findViewById(R.id.studentlist);
        listview.setAdapter(SA);
    }



}
