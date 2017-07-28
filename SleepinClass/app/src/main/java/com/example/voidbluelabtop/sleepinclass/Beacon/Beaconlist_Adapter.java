package com.example.voidbluelabtop.sleepinclass.Beacon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 26.
 */

public class Beaconlist_Adapter extends BaseAdapter{
    Singlton_BeaconList SB;
    List<Beacon> beaconlist;
    String Major, classroom, distance;
    Beaconlist_Adapter(){
        SB = Singlton_BeaconList.getInstance();
        beaconlist = SB.get_beaconlist();
    }

    @Override
    public int getCount() {
        return beaconlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.beaconlist_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView beaconmajor = (TextView) convertView.findViewById(R.id.beaconmajor) ;
        TextView beaconclassroom = (TextView) convertView.findViewById(R.id.classroom) ;
        TextView beacondistance = (TextView) convertView.findViewById(R.id.distance) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Beacon beacon = beaconlist.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        beaconmajor.setText(Integer.toString(beacon.getMajor()));
        //TODO 데아터베이스 이용해볼것
        beaconclassroom.setText("xxxx 강의실");
        beacondistance.setText(Float.toString(beacon.getRssi()));

        return convertView;


    }

}
