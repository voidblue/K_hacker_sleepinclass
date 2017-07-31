package com.example.voidbluelabtop.sleepinclass.FOTPROFESSOR;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.CLASSLIST.Classlist_adapter;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.STUDENTLIST.Studentlist_adapter;

public class MyClasslist extends AppCompatActivity {
    Classlist_adapter CA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classlist);

        CA = new Classlist_adapter();


        ListView listview = (ListView) findViewById(R.id.myclasslist);
        listview.setAdapter(CA);

    }

}
