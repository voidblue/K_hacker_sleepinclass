package com.example.voidbluelabtop.sleepinclass.FOTPROFESSOR;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.STUDENTLIST.Studentlist_adapter;

public class Attantant_Manager extends AppCompatActivity {

    Studentlist_adapter  SA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        SA = new Studentlist_adapter(1);


        ListView listview = (ListView) findViewById(R.id.studentlist);
        listview.setAdapter(SA);
    }
}
