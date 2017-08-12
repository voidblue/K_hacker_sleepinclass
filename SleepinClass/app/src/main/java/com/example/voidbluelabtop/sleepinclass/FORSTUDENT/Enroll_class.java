package com.example.voidbluelabtop.sleepinclass.FORSTUDENT;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.voidbluelabtop.sleepinclass.R;

public class Enroll_class extends Dialog {

    public Enroll_class(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_class);



    }

}
