package com.example.voidbluelabtop.sleepinclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 29.
 */


//데이터베이스에서 받아온 값을 기초로 해야함
public class Studentlist_Adapter extends BaseAdapter {
    Singleton_TempModel STM;
    List<List> students;
    int code;
    //code 0 : 수강생관리
    //code 1 : 출결관리(강사용)
    public Studentlist_Adapter(int code){
        this.code = code;
        STM = Singleton_TempModel.getInstance();
        students = STM.getStudenttable();
    }

    public void refresh(){
    }

    @Override
    public int getCount() {
        return students.size();
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
            convertView = inflater.inflate(R.layout.item_studentlist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;


        TextView studentname = (TextView) convertView.findViewById(R.id.TV_studentname) ;
        TextView studentmajor = (TextView) convertView.findViewById(R.id.TV_studentmajor) ;
        TextView studentcode = (TextView) convertView.findViewById(R.id.TV_studentcode) ;

        // 아이템 내 각 위젯에 데이터 반영
        //TODO 데이터베이스 이용해볼것
        String classroom = "";
        studentname.setText((String)students.get(pos).get(0));
        studentmajor.setText((String)students.get(pos).get(1));
        studentcode.setText((String)students.get(pos).get(2));

        //수강생 관리면 또는 출결관리면


        return convertView;
    }

}
