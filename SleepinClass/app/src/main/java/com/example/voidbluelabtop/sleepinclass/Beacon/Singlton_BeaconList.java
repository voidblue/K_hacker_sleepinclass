package com.example.voidbluelabtop.sleepinclass.Beacon;

import com.estimote.coresdk.recognition.packets.Beacon;

import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 26.
 */

public class Singlton_BeaconList {
    private static final Singlton_BeaconList ourInstance = new Singlton_BeaconList();

    public static Singlton_BeaconList getInstance() {
        return ourInstance;
    }

    private List<Beacon> beaconlist;
    private Singlton_BeaconList() {
    }



    public void set_beaconlist(List<Beacon> beaconlist){
        this.beaconlist = beaconlist;
    }

    public List get_beaconlist(){
        return beaconlist;
    }

}
