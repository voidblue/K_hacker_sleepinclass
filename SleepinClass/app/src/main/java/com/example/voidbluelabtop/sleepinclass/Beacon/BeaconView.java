package com.example.voidbluelabtop.sleepinclass.Beacon;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.CLASS.Createclass;
import com.example.voidbluelabtop.sleepinclass.MainView.MainActivity;
import com.example.voidbluelabtop.sleepinclass.R;

public class BeaconView extends AppCompatActivity {

    private String[] listmenu = {"major", "classroom", "distance"};
    Singlton_BeaconList SB;
    ListView listview;
    ImageButton btn_refresh;
    Beaconlist_Adapter adapter;
    Context mContext;
    BeaconView Instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_view);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar2));
        Instance = this;
        SB = Singlton_BeaconList.getInstance();

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
                Createclass dialog = new Createclass(Instance);
                dialog.show();


            }
        });

        //리스크뷰 갱신 버튼
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
