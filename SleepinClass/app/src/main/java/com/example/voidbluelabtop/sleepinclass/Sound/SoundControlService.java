package com.example.voidbluelabtop.sleepinclass.Sound;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.voidbluelabtop.sleepinclass.DATA.Preference;

/**
 * Created by voidbluelabtop on 17. 7. 28.
 */

public class SoundControlService extends Service {
    AudioManager AM;
    Preference pf;
    public SoundControlService() {
        getSystemService(Context.AUDIO_SERVICE);
        pf = new Preference(this);

        int system = AM.getStreamVolume(AudioManager.STREAM_SYSTEM);
        if (system != 0 ){
            pf.putData("System", system);
        }
        int bell = AM.getStreamVolume(AudioManager.STREAM_RING);
        if (bell != 0 ){
            pf.putData("bell", bell);
        }
        int music = AM.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (music != 0 ){
            pf.putData("music", music);
        }
        int alarm = AM.getStreamVolume(AudioManager.STREAM_ALARM);
        if (alarm != 0 ){
            pf.putData("alarm", alarm);
        }
        int notify = AM.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        if (notify != 0 ){
            pf.putData("notify", notify);
        }
    }

    @Override
    public void onCreate(){
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
