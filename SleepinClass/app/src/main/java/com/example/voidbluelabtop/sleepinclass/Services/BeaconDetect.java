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

import java.text.SimpleDateFormat;
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
    InsertData ID;
    String TAG = "비콘 디텍트 서비스";
    int prevHour;
    final private BeaconRegion ALL_BEACON = new BeaconRegion("regionID", null, null, null);
    @Override
    public void onCreate() {
        ID = new InsertData("attendant");
        preference = new Preference(this);
        GCC = new Get_Current_Class();
        CC = new SoundContoller(this);
        bm = new BeaconManager(getApplicationContext());
        UDC = Singleton_UserDataController.getInstance();
        SB = Singleton_BeaconList.getInstance();
        EstimoteSDK.initialize(getApplicationContext(),
                "estimotebeaconsample-ivb", "45f6fc31fd613624b0f24df41b121db6");
        prevHour = -5;
        bm.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady() {
                bm.startRanging(ALL_BEACON);
            }
        });
        preference.putData("prevHour", 100);
        bm.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {

                CC.startcontroll();
                SB.set_beaconlist(list);
                HashMap currentClass = GCC.getCurrent_Class();
                Date date = new Date();
                SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strdate = SDF.format(date);
                prevHour = preference.getInt("prevHour");

                if (currentClass != null) {
                    String classcode = ((String)currentClass.get("classcode"));
                    int currenthour = date.getHours();
                    if (currenthour != prevHour) {
                        prevHour = currenthour;
                        if (!list.isEmpty()) {
                            for (int i = 0; i < list.size(); i++) {
                                Beacon B = list.get(i);
                                if (currentClass.get("beaconmajor").equals(Integer.toString(B.getMajor()))) {
                                    Log.d(TAG, "onBeaconsDiscovered: " + "6번 테스트" + strdate);


                                    if(date.getMinutes()<10) {
                                        ID.execute(classcode, strdate, GlobalVariables.userCode, "1");
                                    }else {
                                        ID.execute(classcode, strdate, GlobalVariables.userCode, "2");
                                    }
                                    preference.putData("prevHour", prevHour);
                                    Log.d(TAG, "onBeaconsDiscovered: " + "7번 테스트");
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
