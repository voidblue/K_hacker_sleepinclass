package com.example.voidbluelabtop.sleepinclass.FORSTUDENT;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.voidbluelabtop.sleepinclass.R;

public class Enroll_class extends Dialog {

    public Enroll_class(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_enroll_class);
        Button btn_accept = findViewById(R.id.btn_password_accept);

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

}
