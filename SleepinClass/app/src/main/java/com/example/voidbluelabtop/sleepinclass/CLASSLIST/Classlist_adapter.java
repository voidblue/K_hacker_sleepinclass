package com.example.voidbluelabtop.sleepinclass.CLASSLIST;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 29.
 */


//데이터베이스에서 받아온 값을 기초로 해야함
public class Classlist_adapter extends BaseAdapter {
    Singleton_TempModel ST;
    List<List> classtable;
    String Major, classroom, distance;
    int code;
    public Classlist_adapter(){
        ST = Singleton_TempModel.getInstance();
        classtable = ST.getclass();
    }

    @Override
    public int getCount() {
        return classtable.size();
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
        int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_classlist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;


        TextView classname = (TextView) convertView.findViewById(R.id.TV_myclassname) ;
        TextView classroom = (TextView) convertView.findViewById(R.id.TV_myclassroom) ;
        TextView classtime = (TextView) convertView.findViewById(R.id.TV_myclasstime) ;


        // 아이템 내 각 위젯에 데이터 반영
        //TODO 데아터베이스 이용해볼것

        String strclassroom = "";
        classname.setText((String)(classtable.get(pos)).get(0));
        classroom.setText((String)(classtable.get(pos)).get(2));
        classtime.setText((String)(classtable.get(pos)).get(3));
        Log.d("pos", "getView: " + pos + "      " + (String)(classtable.get(pos)).get(0));
        return convertView;
    }

}
