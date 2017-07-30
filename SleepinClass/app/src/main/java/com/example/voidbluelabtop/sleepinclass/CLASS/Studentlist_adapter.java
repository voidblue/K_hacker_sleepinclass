package com.example.voidbluelabtop.sleepinclass.CLASS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.Beacon.Singlton_BeaconList;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 29.
 */


//데이터베이스에서 받아온 값을 기초로 해야함
public class Studentlist_adapter extends BaseAdapter {
    List<Beacon> Students_list;
    String Major, classroom, distance;
    Studentlist_adapter(){
    }

    public void refresh(){
    }

    @Override
    public int getCount() {
        return 0;
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
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;


        TextView beaconID = (TextView) convertView.findViewById(R.id.TV_beaconid) ;
        TextView beacondistance = (TextView) convertView.findViewById(R.id.TV_distance) ;

        // 아이템 내 각 위젯에 데이터 반영
        //TODO 데아터베이스 이용해볼것
        String classroom = "";
//        beaconID.setText(classroom + "(" + beacon.getMajor() + ")");
//        beacondistance.setText(Float.toString(beacon.getRssi()));

        return convertView;
    }

}
