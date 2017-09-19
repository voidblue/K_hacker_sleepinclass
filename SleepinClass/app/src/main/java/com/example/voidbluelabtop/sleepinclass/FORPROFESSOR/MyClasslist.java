package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.CLASSLIST.Classlist_adapter;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

public class MyClasslist extends AppCompatActivity {
    MyClasslist Instance;
    Context thiscontext;
    Classlist_adapter CA;
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
        CA = new Classlist_adapter();


        final ListView listview = (ListView) findViewById(R.id.myclasslist);
        listview.setAdapter(CA);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ST.setClasslistpos(i);
                InputpasswordDialog dialog = new InputpasswordDialog(Instance, Instance);
                dialog.show();
            }
        });

    }

}
