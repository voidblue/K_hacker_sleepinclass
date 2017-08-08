package com.example.voidbluelabtop.sleepinclass.FORPROFESSOR;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.voidbluelabtop.sleepinclass.BeaconList.Beaconlist_Adapter;
import com.example.voidbluelabtop.sleepinclass.R;

public class CreateClass extends AppCompatActivity {

    private String[] listmenu = {"major", "classroom", "distance"};
    ListView listview;
    ImageButton btn_refresh;
    Beaconlist_Adapter adapter;
    Context mContext;
    CreateClass Instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_view);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_createclass);
        toolbar.setTitle("출결 캡스");
        toolbar.setTitleTextColor(0x99000000);
        setSupportActionBar(toolbar);
        Instance = this;

        adapter = new Beaconlist_Adapter();

        //강의개설 다이얼로그
        mContext = getApplicationContext();


        listview = (ListView) findViewById(R.id.beaconlist);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Todo 다이얼로그 띄워서 강의 개설할 수 있게 하기
                //수업시간대, 강의개설비밀번호, 강의실 입력하고 강의코드를 서버에서 계산하고 받아와야함

                Inform_class_Dialog dialog = new Inform_class_Dialog(Instance);
                int major = adapter.getMajor(i);
                dialog.setMajor(major);
                dialog.show();


            }
        });

        //리스트뷰 갱신 버튼
        btn_refresh = (ImageButton) findViewById(R.id.btn_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.refresh();
                listview.setAdapter(adapter);
            }
        });

    }

}
