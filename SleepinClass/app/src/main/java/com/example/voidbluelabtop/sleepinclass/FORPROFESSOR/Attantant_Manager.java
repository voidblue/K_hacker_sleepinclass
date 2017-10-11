package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.Adapter.Studentlist_adapter;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

public class Attantant_Manager extends AppCompatActivity {

    Studentlist_adapter  SA;
    Singleton_Tempdata ST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_managestudent);
        toolbar.setTitle("출결 관리(강사용)");
        toolbar.setTitleTextColor(0x99000000);
        SA = new Studentlist_adapter(1);
        ST = Singleton_Tempdata.getInstance();

        ListView listview = (ListView) findViewById(R.id.studentlist);
        listview.setAdapter(SA);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ST.setStudentlistpos(i);
                Intent intent = new Intent(getApplicationContext(), Student_attandant.class);
                startActivity(intent);
            }
        });
    }
}
