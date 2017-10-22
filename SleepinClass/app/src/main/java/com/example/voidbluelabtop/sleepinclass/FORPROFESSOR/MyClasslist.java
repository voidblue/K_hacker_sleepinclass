package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.Adapter.Classlist_Adapter;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

import java.util.HashMap;

public class MyClasslist extends AppCompatActivity {
    MyClasslist Instance;
    Context thiscontext;
    Classlist_Adapter CA;
    Singleton_Tempdata ST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classlist);
        Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar_myclasslist);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0x99000000);
        ST = Singleton_Tempdata.getInstance();
        thiscontext = this.getApplicationContext();
        Instance = this;
        CA = new Classlist_Adapter();


        final ListView listview = (ListView) findViewById(R.id.myclasslist);
        listview.setAdapter(CA);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(thiscontext, Student_attandant.class);
                intent.putExtra("classCode", (String)((HashMap)(CA.getItem(i))).get("classcode"));
                startActivity(intent);
            }
        });

    }

}
