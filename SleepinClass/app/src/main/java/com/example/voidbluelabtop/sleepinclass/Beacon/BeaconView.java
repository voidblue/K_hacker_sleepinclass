package com.example.voidbluelabtop.sleepinclass.Beacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.R;

public class BeaconView extends AppCompatActivity {

    private String[] listmenu = {"major", "classroom", "distance"};
    Singlton_BeaconList SB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_view);
        SB = Singlton_BeaconList.getInstance();

        Beaconlist_Adapter adapter = new Beaconlist_Adapter();

        ListView listview = (ListView) findViewById(R.id.beaconlist);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Todo 다이얼로그 띄워서 강의 개설할 수 있게 하기
                //수업시간대, 강의개설비밀번호, 강의실 입력하고 강의코드를 서버에서 계산하고 받아와야함
                String strText = (String) adapterView.getItemAtPosition(i);
            }
        });
    }
}
