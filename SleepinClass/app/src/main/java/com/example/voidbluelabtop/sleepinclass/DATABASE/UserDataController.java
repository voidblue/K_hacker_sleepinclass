package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 22.
 */

public class UserDataController {
    private static final UserDataController ourInstance = new UserDataController();

    public static UserDataController getInstance() {
        return ourInstance;
    }
    GetData GD;
    ArrayList<HashMap<String, String>> myClasses;
    public UserDataController() {

    }

    public void processClass(String studentcode) {

        GD = new GetData();
        myClasses = new ArrayList<>();

        ArrayList<String> classCodes = new ArrayList();
        GD.setMode("signedclass");
        GD.execute();
        Log.d("", "getMyClasses: " + GD.getStatus());
        while(GD.mJsonString == null){
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedClassCode = GD.getHashedJson();
        Log.d("", "getMyClasses: "+hashedClassCode.size());
        for (int i = 0; i < hashedClassCode.size() / 2; i++) {
            String recievedStudentCode = hashedClassCode.get("studentcode" + i);
            if (recievedStudentCode.equals(studentcode)) {
                classCodes.add(hashedClassCode.get("classcode" + i));
            }
        }
        Log.d("", "processClass: " + classCodes.size());
        //TODO php파일 만들어야함
        GD = new GetData();
        GD.setMode("class");
        GD.execute();
        while(GD.mJsonString == null) {
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedClasses = GD.getHashedJson();
        for (int i = 0; i < hashedClasses.size() / 5; i++) {
            for (int j = 0; j < classCodes.size(); j++) {
                Log.d("", "processClass: " + hashedClasses.get("classcode"+i) + "  " + classCodes.get(j));
                if (hashedClasses.get("classcode"+i).equals(classCodes.get(j))) {
                    HashMap<String, String> classdata = new HashMap();
                    classdata.put("classname"+j , hashedClasses.get("classname"+i));
                    classdata.put("time"+j , hashedClasses.get("time"+i));
                    classdata.put("classroom"+j , hashedClasses.get("classroom"+i));
                    classdata.put("beaconmajor"+j , hashedClasses.get("beaconmajor"+i));
                    classdata.put("classcode"+j , hashedClasses.get("classcode"+i));
                    myClasses.add(classdata);
                    break;
                }
            }
        }
    }


    public List getMyClasses(){
        return myClasses;
    }
}
