package com.example.voidbluelabtop.sleepinclass.Services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;


import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.voidbluelabtop.sleepinclass.DATABASE.InsertData;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_BeaconList;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.DeviceController.SoundContoller;
import com.example.voidbluelabtop.sleepinclass.Utils.Get_Current_Class;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BeaconDetect extends Service {
    boolean hourchanged;
    public AudioManager AM;
    private BeaconManager bm;
    private Singleton_BeaconList SB;
    private SoundContoller CC;
    private Get_Current_Class GCC;
    int prevHour;
    final private BeaconRegion ALL_BEACON = new BeaconRegion("regionID", null, null, null);
    @Override
    public void onCreate() {
        hourchanged = true;
        Date time = new Date();
        GCC = new Get_Current_Class();
        CC = new SoundContoller(this);
        SB = Singleton_BeaconList.getInstance();
        bm = new BeaconManager(getApplicationContext());
        EstimoteSDK.initialize(getApplicationContext(),
                "estimotebeaconsample-ivb", "45f6fc31fd613624b0f24df41b121db6");
        prevHour = time.getHours();
        bm.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady() {
                bm.startRanging(ALL_BEACON);
            }
        });
        bm.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {
                CC.startcontroll();
                SB.set_beaconlist(list);
                HashMap currentClass = GCC.getCurrent_Class();
                //TODO 클래스 코드는 가져왔음.. 이대로 출석체크했다고 DB로 전달만 하면 끝
                Date date = new Date();
                int currenthour = date.getHours();
                if (currenthour != prevHour){
                    prevHour = currenthour;
                    hourchanged = true;
                }
                if(hourchanged) {
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            Beacon B = list.get(i);
                            if (currentClass.get("classcode" + i).equals(Integer.toString(B.getMajor()))) {
                                InsertData ID = new InsertData("attendent");
                                String day;
                                if (date.getDay() == 0){
                                    if(date.getDay() == 0 ){
                                        day = "일";
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
                                    }else{
                                        day = "토";
                                    }
                                }
                                String now = date.getYear()+ "년 " + date.getMonth()+ "월 " + date.getDate() + "일 " + date.getDay()+ "요일" + "hour" + date.getHours();
                                if (date.getMinutes() < 10) {
                                    ID.execute((String) currentClass.get("classcode" + i), now, GlobalVariables.userCode, "2");
                                    hourchanged = false;
                                } else {
                                    ID.execute((String) currentClass.get("classcode" + i), now, GlobalVariables.userCode, "1");
                                    hourchanged = false;
                                }
                            }
                        }
                    }
                }


            }
        });
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
