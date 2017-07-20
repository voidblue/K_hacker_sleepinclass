package com.example.voidbluelabtop.sleepinclass.Beacon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;
import java.util.UUID;

public class BeaconDetect extends Service {
    private BeaconManager bm;
    public BeaconDetect() {
        bm = new BeaconManager(getApplicationContext());
        bm.connect(new BeaconManager.ServiceReadyCallback(){

            @Override
            public void onServiceReady() {
                bm.startMonitoring(new BeaconRegion("416강의실", UUID.fromString(""),0,0));
            }
        });

        bm.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> list) {
                Toast.makeText(getApplicationContext(), "비콘 감지됨", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {

            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
