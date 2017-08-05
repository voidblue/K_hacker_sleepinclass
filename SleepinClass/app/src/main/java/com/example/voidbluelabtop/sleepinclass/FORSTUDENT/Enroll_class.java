package com.example.voidbluelabtop.sleepinclass.FORSTUDENT;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.voidbluelabtop.sleepinclass.R;

public class Enroll_class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_enrollclass);
        toolbar.setTitleTextColor(0x99000000);
        setSupportActionBar(toolbar);


    }

}
