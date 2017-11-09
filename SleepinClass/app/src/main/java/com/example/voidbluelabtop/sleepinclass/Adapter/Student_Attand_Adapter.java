package com.example.voidbluelabtop.sleepinclass.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.estimote.coresdk.common.internal.utils.L;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.R;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 29.
 */


//데이터베이스에서 받아온 값을 기초로 해야함
public class Student_Attand_Adapter extends BaseAdapter {
    Singleton_UserDataController UDC;
    String Major, classroom, distance;
    TextView attand, late, absent;
    int defaultTextColor;
    List datelist;
    List items;
    public Student_Attand_Adapter(String classcode, String strdate){
        List tempItems;
        UDC = Singleton_UserDataController.getInstance();
        UDC.processAttend(classcode);
        tempItems = UDC.getMyAttendant();
        Log.d("", "Student_Attand_Adapter: " + ((HashMap)tempItems.get(0)));
        items = new ArrayList();
        for (int i = 0 ; i < tempItems.size() ; i++){
            if(((HashMap)tempItems.get(i)).get("studentcode").equals(GlobalVariables.userCode)){
                items.add(tempItems.get(i));
            }
        }
        datelist = new ArrayList();
    }


    @Override
    public int getCount() {
        return items.size();
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
            convertView = inflater.inflate(R.layout.item_attand, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;

        HashMap attendant = (HashMap)items.get(position);
        //TODO 아래꺼 세개만 DB에서 받아와 처리하자
        TextView classorder = (TextView) convertView.findViewById(R.id.tv_classorder) ;
        TextView starttime = (TextView) convertView.findViewById(R.id.tv_starttime) ;
        TextView attandtime = (TextView) convertView.findViewById(R.id.tv_attandtime) ;



        attand = (TextView) convertView.findViewById(R.id.tv_attand);
        late = (TextView) convertView.findViewById(R.id.tv_late);
        absent = (TextView) convertView.findViewById(R.id.tv_absent);
        if (attand.getCurrentTextColor() == late.getCurrentTextColor()){
            defaultTextColor = attand.getCurrentTextColor();
        }
        else if (late.getCurrentTextColor() == absent.getCurrentTextColor()){
            defaultTextColor = late.getCurrentTextColor();
        }
        else{
            defaultTextColor = attand.getCurrentTextColor();
        }
        attand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attand.setTextColor(0xffff0000);
                late.setTextColor(defaultTextColor);
                absent.setTextColor(defaultTextColor);
            }
        });

        late.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attand.setTextColor(defaultTextColor);
                late.setTextColor(0xffff0000);
                absent.setTextColor(defaultTextColor);
            }
        });

        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attand.setTextColor(defaultTextColor);
                late.setTextColor(defaultTextColor);
                absent.setTextColor(0xffff0000);
            }
        });

        return convertView;
    }



}
