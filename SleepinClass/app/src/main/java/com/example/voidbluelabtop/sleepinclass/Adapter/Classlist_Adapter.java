package com.example.voidbluelabtop.sleepinclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 9. 19.
 */

public class Classlist_Adapter extends BaseAdapter {
    Singleton_TempModel ST;
    List<List> classtable;
    String Major, classroom, distance;
    int mode;
    //mode==0 학생관리, 1이면 출결내역
    public Classlist_Adapter(){
        ST = Singleton_TempModel.getInstance();
        classtable = ST.getclass();
//        this.mode = mode;
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

        List classdata = classtable.get(position);

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_classlist, parent, false);
        }

        // 화면에 표시될 View(Layouto이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;


        TextView classname = (TextView) convertView.findViewById(R.id.TV_myclassname) ;
        TextView classtime = (TextView) convertView.findViewById(R.id.TV_myclasstime) ;
        TextView classroom = (TextView) convertView.findViewById(R.id.TV_myclassroom) ;

        classname.setText((String)classdata.get(0));
        classtime.setText((String)classdata.get(1));
        classroom.setText((String)classdata.get(2));

        return convertView;
    }
}
