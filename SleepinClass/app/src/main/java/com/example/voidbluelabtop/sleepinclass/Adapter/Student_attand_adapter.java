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
 * Created by voidbluelabtop on 17. 7. 29.
 */


//데이터베이스에서 받아온 값을 기초로 해야함
public class Student_attand_adapter extends BaseAdapter {
    Singleton_TempModel ST;
    List<List> classtable;
    String Major, classroom, distance;
    int mode;
    //mode==0 학생관리, 1이면 출결내역
    public Student_attand_adapter(){
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

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_classlist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;


        TextView classorder = (TextView) convertView.findViewById(R.id.tv_classorder) ;
        TextView starttime = (TextView) convertView.findViewById(R.id.tv_starttime) ;
        TextView attandtime = (TextView) convertView.findViewById(R.id.tv_attandtime) ;
        TextView attand = (TextView) convertView.findViewById(R.id.tv_attand);
        TextView late = (TextView) convertView.findViewById(R.id.tv_late);
        TextView absent = (TextView) convertView.findViewById(R.id.tv_absent);

        //TODO 이제 텍스트 처리하는거 넣어야되는데 귀찮다 ㅁㄴ엄ㄴ허내ㅑ눙맹뉴냐

        return convertView;
    }



}
