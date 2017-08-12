package com.example.voidbluelabtop.sleepinclass.Sound;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.example.voidbluelabtop.sleepinclass.BeaconList.BeaconDetect;
import com.example.voidbluelabtop.sleepinclass.BeaconList.Singleton_BeaconList;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.Time.Split_Date;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Preference;

import java.util.Date;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 8. 8.
 */

public class SoundContoller {
    AudioManager AM;
    Singleton_TempModel ST;
    List classtable;
    BeaconDetect BD;
    Singleton_BeaconList SB;
    public SoundContoller(BeaconDetect BeaconDetect){
        ST = Singleton_TempModel.getInstance();
        classtable = ST.getclass();
        this.BD = BeaconDetect;
        SB = Singleton_BeaconList.getInstance();
    }


    public void startcontroll(){
        boolean serviceflag = false;
        Intent service = new Intent(BD.getApplicationContext(), SoundControlService.class);
        for(Beacon beacon : SB.get_beaconlist()) {
            for (int i = 0; i < classtable.size(); i++) {
                String time = (String) ((List) (classtable.get(i))).get(1);
                int beaconmajor = Integer.parseInt((String) ((List) (classtable.get(i))).get(3));
                if (beacon.getMajor() != beaconmajor){
                    break;
                }
                Split_Date SD = new Split_Date();
                SD.setdate(time);
                Date date = new Date();

                if (date.getDay() != SD.getdaycode()) {
                    if (SD.getStarthour() * 60 + SD.getStartminute() < date.getHours() * 60 + date.getMinutes()
                            || date.getHours() * 60 + date.getMinutes() < SD.getEndhour() * 60 + SD.getEndminute()) {
                        Log.d("???", "startcontroll: ????");
                        BD.startService(service); // 왜안되고있지???
                        excute();//임시코드
                        serviceflag = true;
                        break;
                    }
                }
            }
        }
        if(serviceflag == false){
            BD.AM = (AudioManager)BD.getSystemService(Context.AUDIO_SERVICE);
            BD.AM.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
    }

    public void excute(){
        Log.d("볼륨조절서비스", "onCreate: 되고있니");
        BD.AM.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }




}
