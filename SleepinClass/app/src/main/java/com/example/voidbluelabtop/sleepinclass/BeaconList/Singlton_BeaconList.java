package com.example.voidbluelabtop.sleepinclass.BeaconList;

import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.recognition.utils.MacAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        beaconlist = new ArrayList<>();
        ///테스트용 코드!!!!!
        byte[] xxx =  new byte[6];
        Beacon testbeacon = new Beacon(UUID.fromString("3F2504E0-4F89-11D3-9A0C-0305E82C3301"), MacAddress.fromBytes(xxx),35353,12345,75,75);
        beaconlist.add(testbeacon);
    }



    public void set_beaconlist(List<Beacon> beaconlist){
        if(!beaconlist.isEmpty())
        this.beaconlist = beaconlist;
    }

    public List get_beaconlist(){
        return beaconlist;
    }

}
