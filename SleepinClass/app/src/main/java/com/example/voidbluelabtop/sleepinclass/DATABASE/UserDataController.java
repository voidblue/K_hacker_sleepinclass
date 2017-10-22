package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.os.AsyncTask;
import android.util.Log;

import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 22.
 */

public class UserDataController {
    GetData GD;

    public UserDataController() {
        GD = new GetData();
    }

    public List getMyClasses(String studentcode) {


        ArrayList<HashMap<String, String>> myclasses = new ArrayList<>();

        ArrayList<String> classCodes = new ArrayList();
        GD.setMode("signedclass");
        GD.execute();
        Log.d("", "getMyClasses: " + GD.getStatus());
        while(GD.mJsonString == null){
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedClassCode = GD.getHashedJson();
        Log.d("", "getMyClasses: "+hashedClassCode.isEmpty());
        for (int i = 0; i < hashedClassCode.size() / 2; i++) {
            String recievedStudentCode = hashedClassCode.get("studentcode" + i);
            if (recievedStudentCode.equals(studentcode)) {
                classCodes.add(hashedClassCode.get("classcode" + i));
            }

        }
        //TODO php파일 만들어야함
        GD = new GetData();
        GD.setMode("class");
        GD.execute();
//        while(GD.mJsonString == null) {
//            Log.d("", "getMyClasses: " + GD.getStatus());
//        }
        GD.process();
        HashMap<String, String> hashedClasses = GD.getHashedJson();
        for (int i = 0; i < hashedClasses.size() / 5; i++) {
            for (int j = 0; j < classCodes.size(); j++) {
                if (hashedClasses.get("classcode"+i).equals(classCodes.get(j))) {
                    HashMap<String, String> classdata = new HashMap();
                    classdata.put("classname"+j , hashedClasses.get("classname"+i));
                    classdata.put("time"+j , hashedClasses.get("time"+i));
                    classdata.put("classroom"+j , hashedClasses.get("classroom"+i));
                    classdata.put("beaconmajor"+j , hashedClasses.get("beaconmajor"+i));
                    classdata.put("classcode"+j , hashedClasses.get("classcode"+i));
                    myclasses.add(classdata);
                    break;
                }
            }
        }
        return myclasses;
    }
}
