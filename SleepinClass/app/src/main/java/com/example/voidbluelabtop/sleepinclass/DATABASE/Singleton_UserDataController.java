package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 22.
 */

public class Singleton_UserDataController {
    private static final Singleton_UserDataController ourInstance = new Singleton_UserDataController();

    public static Singleton_UserDataController getInstance() {
        return ourInstance;
    }
    GetData GD;
    ArrayList<HashMap<String, String>> myClasses;
    ArrayList<HashMap<String, String>> myAttendant;
    ArrayList<HashMap<String, String>> professorClasses;
    ArrayList<HashMap<String, String>> myClassStudents;



    public Singleton_UserDataController() {

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
        GD.cancel(true);
        Log.d("", "getMyClasses: "+hashedClassCode.size());
        for (int i = 0; i < hashedClassCode.size() / 2; i++) {
            String recievedStudentCode = hashedClassCode.get("studentcode" + i);
            if (recievedStudentCode.equals(studentcode)) {
                classCodes.add(hashedClassCode.get("classcode" + i));
            }
        }
        Log.d("", "processClass: " + classCodes.size());
        GD = new GetData();
        GD.setMode("class");
        GD.execute();
        while(GD.mJsonString == null) {
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedClasses = GD.getHashedJson();
        GD.cancel(true);
        for (int i = 0; i < hashedClasses.size() / 6; i++) {
            for (int j = 0; j < classCodes.size(); j++) {
                Log.d("", "processClass: " + hashedClasses.get("classcode"+i) + "  " + classCodes.get(j));
                if (hashedClasses.get("classcode"+i).equals(classCodes.get(j))) {
                    HashMap<String, String> classdata = new HashMap();
                    classdata.put("classname" , hashedClasses.get("classname"+i));
                    classdata.put("time" , hashedClasses.get("time"+i));
                    classdata.put("classroom" , hashedClasses.get("classroom"+i));
                    classdata.put("beaconmajor" , hashedClasses.get("beaconmajor"+i));
                    classdata.put("classcode" , hashedClasses.get("classcode"+i));
                    myClasses.add(classdata);
                    break;
                }
            }
        }
    }

    public void processProfessorClass(String professorCode){
        GD = new GetData();
        GD.setMode("class");
        GD.execute();
        while(GD.mJsonString == null) {
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedClasses = GD.getHashedJson();
        GD.cancel(true);
        professorClasses = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < hashedClasses.size() / 6; i++) {
            if (hashedClasses.get("professorcode"+i).equals(professorCode)) {
                HashMap<String, String> classdata = new HashMap();
                classdata.put("classname" , hashedClasses.get("classname"+i));
                classdata.put("time" , hashedClasses.get("time"+i));
                classdata.put("classroom" , hashedClasses.get("classroom"+i));
                classdata.put("beaconmajor" , hashedClasses.get("beaconmajor"+i));
                classdata.put("classcode" , hashedClasses.get("classcode"+i));
                professorClasses.add(classdata);
                j++;
            }

        }
    }

    public void processAttend(String classcode){
        GD = new GetData();
        GD.setMode("attnedant");
        myAttendant = new ArrayList();
        while(GD.mJsonString == null) {
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedAttendant = GD.getHashedJson();
        int j = 0;
        for (int i = 0 ; i < hashedAttendant.size() ; i++){
            if(hashedAttendant.get("classcode"+i).equals("classcode")){
                HashMap<String, String> attendant = new HashMap();
                attendant.put("date",hashedAttendant.get("date"+i));
                attendant.put("date",hashedAttendant.get("ischecked"+i));
                myAttendant.add(attendant);

            }
        }
    }

    public List processmyClassStudent(String classCode){
        GD = new GetData();
        ArrayList<String> classCodes = new ArrayList();
        GD.setMode("signedclass");
        GD.execute();
        while(GD.mJsonString == null){
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedClassCode = GD.getHashedJson();
        GD.cancel(true);
        Log.d("", "getMyClasses: "+hashedClassCode.size());
        for (int i = 0; i < hashedClassCode.size() / 2; i++) {
            String recievedClassCode = hashedClassCode.get("classcode" + i);

            if (recievedClassCode.equals(classCode)) {
                classCodes.add(hashedClassCode.get("studentcode" + i));
            }
        }
        GD = new GetData();
        GD.setMode("student");
        myClassStudents = new ArrayList();
        while(GD.mJsonString == null) {
            Log.d("", "getMyClasses: " + GD.getStatus());
        }
        GD.process();
        HashMap<String, String> hashedmyClassStudnet = GD.getHashedJson();
        int j = 0 ;
        for (int i = 0 ; i < hashedmyClassStudnet.size() ; i++){
            if (hashedmyClassStudnet.get("classcode"+i).equals(classCode)){
                HashMap<String, String> student = new HashMap<>();
                student.put("studentname", hashedmyClassStudnet.get("studentname"+i));
                student.put("studentmajor", hashedmyClassStudnet.get("studentmajor"+i));
                student.put("studentcode", hashedmyClassStudnet.get("studentcode"+i));
            }
        }
        return new ArrayList();
    }

    public List getMyClasses(){
        return myClasses;
    }

    public List getMyAttendant(){
        return myAttendant;
    }

    public List getProfessorclasses(){
        return professorClasses;
    }
}
