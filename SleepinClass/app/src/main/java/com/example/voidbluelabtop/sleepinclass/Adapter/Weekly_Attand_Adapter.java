package com.example.voidbluelabtop.sleepinclass.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.R;

/**
 * Created by voidbluelabtop on 17. 9. 19.
 */

public class Weekly_Attand_Adapter extends BaseAdapter {
    //mode==0 학생관리, 1이면 출결내역
    public Weekly_Attand_Adapter(){
    }

    @Override
    public int getCount() {
        return 15;
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
            convertView = inflater.inflate(R.layout.item_weekly_attendant, parent, false);
        }

        // 화면에 표시될 View(Layouto이 inflate된)으로부터 위젯에 대한 참조 획득

        TextView tv_week = (TextView)convertView.findViewById(R.id.tv_week);
        TextView tv_mon = (TextView)convertView.findViewById(R.id.tv_mon_attendant);
        TextView tv_tue = (TextView)convertView.findViewById(R.id.tv_tue_attendant);
        TextView tv_wen = (TextView)convertView.findViewById(R.id.tv_wen_attendant);
        TextView tv_thu = (TextView)convertView.findViewById(R.id.tv_thu_attendant);
        TextView tv_fri = (TextView)convertView.findViewById(R.id.tv_fri_attendant);

        tv_week.setText(pos + "주차");

        return convertView;
    }
}
