package com.example.voidbluelabtop.sleepinclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ToggleButton;

public class Classroom_setting extends AppCompatActivity {
    Singleton_tempdata ST;
    Preference SP;
    ToggleButton[][] tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ST = Singleton_tempdata.getInstance();
        setTitle(ST.getclassromm());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_classroom_setting);

        SP = new Preference(this);

        tb = new ToggleButton[7][9];
        tb[0][0] = (ToggleButton)findViewById(R.id.tb_mon_1);
        tb[0][1] = (ToggleButton)findViewById(R.id.tb_mon_2);
        tb[0][2] = (ToggleButton)findViewById(R.id.tb_mon_3);
        tb[0][3] = (ToggleButton)findViewById(R.id.tb_mon_4);
        tb[0][4] = (ToggleButton)findViewById(R.id.tb_mon_5);
        tb[0][5] = (ToggleButton)findViewById(R.id.tb_mon_6);
        tb[0][6] = (ToggleButton)findViewById(R.id.tb_mon_7);
        tb[0][7] = (ToggleButton)findViewById(R.id.tb_mon_8);
        tb[0][8] = (ToggleButton)findViewById(R.id.tb_mon_9);

        tb[1][0] = (ToggleButton)findViewById(R.id.tb_tue_1);
        tb[1][1] = (ToggleButton)findViewById(R.id.tb_tue_2);
        tb[1][2] = (ToggleButton)findViewById(R.id.tb_tue_3);
        tb[1][3] = (ToggleButton)findViewById(R.id.tb_tue_4);
        tb[1][4] = (ToggleButton)findViewById(R.id.tb_tue_5);
        tb[1][5] = (ToggleButton)findViewById(R.id.tb_tue_6);
        tb[1][6] = (ToggleButton)findViewById(R.id.tb_tue_7);
        tb[1][7] = (ToggleButton)findViewById(R.id.tb_tue_8);
        tb[1][8] = (ToggleButton)findViewById(R.id.tb_tue_9);

        tb[2][0] = (ToggleButton)findViewById(R.id.tb_wen_1);
        tb[2][1] = (ToggleButton)findViewById(R.id.tb_wen_2);
        tb[2][2] = (ToggleButton)findViewById(R.id.tb_wen_3);
        tb[2][3] = (ToggleButton)findViewById(R.id.tb_wen_4);
        tb[2][4] = (ToggleButton)findViewById(R.id.tb_wen_5);
        tb[2][5] = (ToggleButton)findViewById(R.id.tb_wen_6);
        tb[2][6] = (ToggleButton)findViewById(R.id.tb_wen_7);
        tb[2][7] = (ToggleButton)findViewById(R.id.tb_wen_8);
        tb[2][8] = (ToggleButton)findViewById(R.id.tb_wen_9);

        tb[3][0] = (ToggleButton)findViewById(R.id.tb_thu_1);
        tb[3][1] = (ToggleButton)findViewById(R.id.tb_thu_2);
        tb[3][2] = (ToggleButton)findViewById(R.id.tb_thu_3);
        tb[3][3] = (ToggleButton)findViewById(R.id.tb_thu_4);
        tb[3][4] = (ToggleButton)findViewById(R.id.tb_thu_5);
        tb[3][5] = (ToggleButton)findViewById(R.id.tb_thu_6);
        tb[3][6] = (ToggleButton)findViewById(R.id.tb_thu_7);
        tb[3][7] = (ToggleButton)findViewById(R.id.tb_thu_8);
        tb[3][8] = (ToggleButton)findViewById(R.id.tb_thu_9);

        tb[4][0] = (ToggleButton)findViewById(R.id.tb_fri_1);
        tb[4][1] = (ToggleButton)findViewById(R.id.tb_fri_2);
        tb[4][2] = (ToggleButton)findViewById(R.id.tb_fri_3);
        tb[4][3] = (ToggleButton)findViewById(R.id.tb_fri_4);
        tb[4][4] = (ToggleButton)findViewById(R.id.tb_fri_5);
        tb[4][5] = (ToggleButton)findViewById(R.id.tb_fri_6);
        tb[4][6] = (ToggleButton)findViewById(R.id.tb_fri_7);
        tb[4][7] = (ToggleButton)findViewById(R.id.tb_fri_8);
        tb[4][8] = (ToggleButton)findViewById(R.id.tb_fri_9);

        tb[5][0] = (ToggleButton)findViewById(R.id.tb_sat_1);
        tb[5][1] = (ToggleButton)findViewById(R.id.tb_sat_2);
        tb[5][2] = (ToggleButton)findViewById(R.id.tb_sat_3);
        tb[5][3] = (ToggleButton)findViewById(R.id.tb_sat_4);
        tb[5][4] = (ToggleButton)findViewById(R.id.tb_sat_5);
        tb[5][5] = (ToggleButton)findViewById(R.id.tb_sat_6);
        tb[5][6] = (ToggleButton)findViewById(R.id.tb_sat_7);
        tb[5][7] = (ToggleButton)findViewById(R.id.tb_sat_8);
        tb[5][8] = (ToggleButton)findViewById(R.id.tb_sat_9);

        tb[6][0] = (ToggleButton)findViewById(R.id.tb_sun_1);
        tb[6][1] = (ToggleButton)findViewById(R.id.tb_sun_2);
        tb[6][2] = (ToggleButton)findViewById(R.id.tb_sun_3);
        tb[6][3] = (ToggleButton)findViewById(R.id.tb_sun_4);
        tb[6][4] = (ToggleButton)findViewById(R.id.tb_sun_5);
        tb[6][5] = (ToggleButton)findViewById(R.id.tb_sun_6);
        tb[6][6] = (ToggleButton)findViewById(R.id.tb_sun_7);
        tb[6][7] = (ToggleButton)findViewById(R.id.tb_sun_8);
        tb[6][8] = (ToggleButton)findViewById(R.id.tb_sun_9);


        for (int i = 0; i < 7 ; i++){
            for (int j = 0; j < 7 ; j++){
                tb[i][j].setChecked(SP.getBoolean("week"+i+"time"+j));
                tb[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for(int i = 0 ; i < 7; i++){
                            for (int j = 0; j < 9 ; j++){
                                if(view.getId() == tb[i][j].getId() && tb[i][j].isChecked()){
                                    SP.putData("week"+i+"time"+j, true);
                                }
                                else if(view.getId() == tb[i][j].getId() && !tb[i][j].isChecked()){
                                    SP.putData("week"+i+"time"+j, false);
                                }

                            }
                        }

                    }
                });
            }
        }

    }




}
