package com.example.voidbluelabtop.sleepinclass.STUDENTLIST;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.R;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 29.
 */


//데이터베이스에서 받아온 값을 기초로 해야함
public class Studentlist_adapter extends BaseAdapter {
    List<Beacon> Students_list;
    String Major, classroom, distance;
    int code;
    public Studentlist_adapter(int code){
        //0이면 수강생관리
        //1이면 출결관리
        this.code = code;
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
            convertView = inflater.inflate(R.layout.item_studentlist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        TextView beaconimage = (TextView) convertView.findViewById(R.id.) ;


        TextView studentname = (TextView) convertView.findViewById(R.id.TV_studentname) ;
        TextView studentmajor = (TextView) convertView.findViewById(R.id.TV_studentmajor) ;
        TextView studentcode = (TextView) convertView.findViewById(R.id.TV_studentcode) ;
        Button btn_function = (Button) convertView.findViewById(R.id.btn_function);


        // 아이템 내 각 위젯에 데이터 반영
        //TODO 데아터베이스 이용해볼것
        String classroom = "";
        studentname.setText("이름");
        studentmajor.setText("학과");
        studentcode.setText("학번");

        //수강생 관리면 또는 출결관리면
        if(code == 0){
            btn_function.setText("삭제");
            btn_function.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO 삭제 확인 다이얼로그 띄우기
                }
            });
        }
        else if(code == 1){
            //DB에서 출석 정보 따올것
            btn_function.setText("출석정보");
            btn_function.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //출석정보 바꿀수 있도록 하기
                }
            });
        }

        return convertView;
    }

}
