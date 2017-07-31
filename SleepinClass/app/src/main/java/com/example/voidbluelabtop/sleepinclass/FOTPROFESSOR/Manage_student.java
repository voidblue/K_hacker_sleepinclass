package com.example.voidbluelabtop.sleepinclass.FOTPROFESSOR;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.CLASSLIST.Classlist_adapter;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.STUDENTLIST.Studentlist_adapter;

public class Manage_student extends AppCompatActivity {

    Classlist_adapter CA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        CA = new Classlist_adapter();


        ListView listview = (ListView) findViewById(R.id.studentlist);
        listview.setAdapter(CA);
    }



}
