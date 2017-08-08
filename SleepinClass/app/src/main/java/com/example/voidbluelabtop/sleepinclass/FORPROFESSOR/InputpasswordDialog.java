package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;

public class InputpasswordDialog extends Dialog {
    EditText ET_password;
    Context thiscontext;
    Activity parent;
    Singleton_Tempdata ST;
    public InputpasswordDialog(@NonNull Context context, Activity parent) {
        super(context);
        thiscontext = this.getContext();
        this.parent = parent;
        ST = Singleton_Tempdata.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_intputpassword);

        ET_password = (EditText) findViewById(R.id.ET_inputpassword);
        Button btn_accept = (Button) findViewById(R.id.btn_password_accept);
        Button btn_cancel = (Button) findViewById(R.id.btn_password_cancel);


        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable edit_password = ET_password.getText();
                String password = edit_password.toString();
                if(password != null){
                    //TODO 패스워드가 강의실 패스워드가 일치한다면
                    if (ST.getmanagemode() == 0){
                        Intent i = new Intent(thiscontext, Manage_student.class);
                        dismiss();
                        parent.startActivity(i);
                    }
                    else if(ST.getmanagemode() == 1) {

                        Intent i = new Intent(thiscontext, Attantant_Manager.class);
                        dismiss();
                        parent.startActivity(i);
                    }
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
