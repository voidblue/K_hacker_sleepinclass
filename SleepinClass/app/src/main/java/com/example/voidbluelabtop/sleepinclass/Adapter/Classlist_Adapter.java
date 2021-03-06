package com.example.voidbluelabtop.sleepinclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 9. 19.
 */

public class Classlist_Adapter extends BaseAdapter {
    List<HashMap<String, String>> classtable;
    String Major, classroom, distance;
    Singleton_UserDataController UDC;
    int mode;
    //mode==0 학생관리, 1이면 출결내역
    public Classlist_Adapter(){
        UDC = Singleton_UserDataController.getInstance();
        if (GlobalVariables.userauth == 1) {
            UDC.processProfessorClass(GlobalVariables.userCode);
            classtable = UDC.getProfessorclasses();
        }else if (GlobalVariables.userauth == 0){
            UDC.processClass(GlobalVariables.userCode);
            classtable = UDC.getMyClasses();
        }

    }

    @Override
    public int getCount() {
        return classtable.size();
    }

    @Override
    public Object getItem(int i) {
        return classtable.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int pos = position;
        final Context context = parent.getContext();

        HashMap classdata = classtable.get(position);

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
        String strdate =  (String)classdata.get("date");
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        try {
            date = SDF.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = "";
        if (date.getDay() == 0){
            day  = "일";
        }else if(date.getDay() == 1){
            day = "월";
        }else if(date.getDay() == 2){
            day = "화";
        }else if(date.getDay() == 3){
            day = "수";
        }else if(date.getDay() == 4){
            day = "목";
        }else if(date.getDay() == 5){
            day = "금";
        }else if(date.getDay() == 6){
            day = "토";
        }else{

        }
        int duration = Integer.parseInt((String)classdata.get("duration"));
        String timetext = day + date.getHours() + " : " + date.getMinutes() + " ~ " +
                (date.getHours() + duration) + " : " + date.getMinutes();
        classname.setText((String)classdata.get("classname"));
        classtime.setText(timetext);
        classroom.setText((String)classdata.get("classroom"));

        return convertView;
    }
}
