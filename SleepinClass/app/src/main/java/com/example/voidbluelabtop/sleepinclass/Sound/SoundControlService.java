package com.example.voidbluelabtop.sleepinclass.Sound;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.voidbluelabtop.sleepinclass.USERDATA.Preference;

/**
 * Created by voidbluelabtop on 17. 7. 28.
 */

public class SoundControlService extends Service {
    AudioManager AM;
    Preference pf;
    public SoundControlService() {
        Log.d("볼륨조절서비스", "onCreate: 되고있니");
    }

    @Override
    public void onCreate(){
        Log.d("볼륨조절서비스", "onCreate: 되고있니");
        AM = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        pf = new Preference(this);

//        int system = AM.getStreamVolume(AudioManager.STREAM_SYSTEM);
//        if (system != 0 ){
//            pf.putData("System", system);
//        }
//        int bell = AM.getStreamVolume(AudioManager.STREAM_RING);
//        if (bell != 0 ){
//            pf.putData("bell", bell);
//        }
//        int music = AM.getStreamVolume(AudioManager.STREAM_MUSIC);
//        if (music != 0 ){
//            pf.putData("music", music);
//        }
//        int alarm = AM.getStreamVolume(AudioManager.STREAM_ALARM);
//        if (alarm != 0 ){
//            pf.putData("alarm", alarm);
//        }
//        int notify = AM.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
//        if (notify != 0 ){
//            pf.putData("notify", notify);
//        }
        if (AM.getMode() == AudioManager.RINGER_MODE_NORMAL){
            AM.setMode(AudioManager.RINGER_MODE_VIBRATE);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy(){
        AM.setMode(AudioManager.MODE_NORMAL);

        Log.d("볼륨조절서비스", "onCreate: 되고있니");
    }

}
