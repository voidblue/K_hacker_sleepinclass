package com.example.voidbluelabtop.sleepinclass.Services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.voidbluelabtop.sleepinclass.DATABASE.InsertData;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.FORPROFESSOR.Dialog_datepicker;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Preference;
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
    private Singleton_UserDataController UDC;
    private Preference preference;
    String TAG = "비콘 디텍트 서비스";
    int prevHour;
    final private BeaconRegion ALL_BEACON = new BeaconRegion("regionID", null, null, null);
    @Override
    public void onCreate() {

        hourchanged = true;
        preference = new Preference(this);
        Date time = new Date();
        GCC = new Get_Current_Class();
        CC = new SoundContoller(this);
        SB = Singleton_BeaconList.getInstance();
        bm = new BeaconManager(getApplicationContext());
        UDC = Singleton_UserDataController.getInstance();

        EstimoteSDK.initialize(getApplicationContext(),
                "estimotebeaconsample-ivb", "45f6fc31fd613624b0f24df41b121db6");
        prevHour = -5;
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
                Date date = new Date();
                prevHour = preference.getInt("prevHour");
                Log.d(TAG, "onBeaconsDiscovered: " + "1번째 테스트"+ currentClass);
                if (currentClass != null) {
                    Log.d(TAG, "onBeaconsDiscovered: " + "2번째 테스트" + currentClass + "  asd " + prevHour);
                    String classcode = ((String)currentClass.get("classcode"));
                    int currenthour = date.getHours();
                    if (currenthour != prevHour) {
                        Log.d(TAG, "onBeaconsDiscovered: " + "3번째 테스트");
                        prevHour = currenthour;
                        if (!list.isEmpty()) {
                            Log.d(TAG, "onBeaconsDiscovered: " + "4번째 테스트" + currentClass.get("beaconmajor"));
                            for (int i = 0; i < list.size(); i++) {
                                Beacon B = list.get(i);
                                Log.d(TAG, "onBeaconsDiscovered: " + "5번 테스트" + B.getMajor());
                                if (currentClass.get("beaconmajor").equals(Integer.toString(B.getMajor()))) {
                                    Log.d(TAG, "onBeaconsDiscovered: " + "6번 테스트");

                                    InsertData ID = new InsertData("attendent");
                                    if(date.getMinutes()<10) {
                                        ID.execute(classcode, date.toString(), GlobalVariables.userCode, "1");
                                    }else {
                                        ID.execute(classcode, date.toString(), GlobalVariables.userCode, "2");
                                    }
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
