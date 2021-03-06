package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
    ArrayList myClassStudents;



    public Singleton_UserDataController() {

    }

    public void processClass(String studentcode) {

        GD = new GetData();
        myClasses = new ArrayList<>();

        ArrayList<String> classCodes = new ArrayList();
        GD.setMode("signedclass");
        GD.execute();
        while(GD.mJsonString == null){
        }
        GD.process();
        HashMap<String, String> hashedClassCode = GD.getHashedJson();
        GD.cancel(true);
        for (int i = 0; i < hashedClassCode.size() / 2; i++) {
            String recievedStudentCode = hashedClassCode.get("studentcode" + i);
            if (recievedStudentCode.equals(studentcode)) {
                classCodes.add(hashedClassCode.get("classcode" + i));
            }
        }
        GD = new GetData();
        GD.setMode("class");
        GD.execute();
        while(GD.mJsonString == null) {
        }
        GD.process();
        HashMap<String, String> hashedClasses = GD.getHashedJson();
        GD.cancel(true);
        for (int i = 0; i < hashedClasses.size() / 6; i++) {
            for (int j = 0; j < classCodes.size(); j++) {
                if (hashedClasses.get("classcode"+i).equals(classCodes.get(j))) {
                    HashMap<String, String> classdata = new HashMap();
                    classdata.put("classname" , hashedClasses.get("classname"+i));
                    classdata.put("date" , hashedClasses.get("date"+i));
                    classdata.put("duration", hashedClasses.get("duration" + i));
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
                classdata.put("date" , hashedClasses.get("date"+i));

                classdata.put("duration", hashedClasses.get("duration" + i));
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
        GD.setMode("attendant");
        myAttendant = new ArrayList();
        GD.execute();
        while(GD.mJsonString == null) {
        }
        GD.process();
        HashMap<String, String> hashedAttendant = GD.getHashedJson();
        int j = 0;
        for (int i = 0 ; i < hashedAttendant.size()/4 ; i++){
            Log.d("", "processAttend: " + hashedAttendant.get("classcode" + i));
            if(hashedAttendant.get("classcode"+i).equals(classcode)){
                HashMap<String, String> attendant = new HashMap();
                attendant.put("studentcode", hashedAttendant.get("studentcode"+i));
                attendant.put("date",hashedAttendant.get("date"+i));
                attendant.put("ischecked",hashedAttendant.get("ischecked"+i));
                myAttendant.add(attendant);

            }
        }
    }

    public void processmyClassStudent(String classCode){
        GD = new GetData();
        ArrayList studentCodes = new ArrayList();
        GD.setMode("signedclass");
        GD.execute();
        while(GD.mJsonString == null){
        }
        GD.process();
        HashMap<String, String> hashedClassCode = GD.getHashedJson();
        GD.cancel(true);



        for (int i = 0; i < hashedClassCode.size() / 2; i++) {
            String recievedClassCode = hashedClassCode.get("classcode"+i);
            if (recievedClassCode.equals(classCode)) {
                studentCodes.add(hashedClassCode.get("studentcode"+i));
            }
        }
        GD = new GetData();
        GD.setMode("student");
        GD.execute();
        myClassStudents = new ArrayList();
        while(GD.mJsonString == null) {
        }
        GD.process();
        HashMap<String, String> hashedmyClassStudnet = GD.getHashedJson();
//        Log.d("", "processmyClassStudent: " + hashedmyClassStudnet.get("studentcode"+0));
//        Log.d("", "processmyClassStudent: " + studentCodes.get(0));
        for (int i = 0 ; i < hashedmyClassStudnet.size()/4 ; i++){
            for(int j  = 0 ; j < studentCodes.size() ; j++){
                if (hashedmyClassStudnet.get("studentcode"+i).equals(studentCodes.get(j))) {
                    HashMap<String, String> student = new HashMap<>();
                    student.put("studentname", hashedmyClassStudnet.get("studentname" + i));
                    student.put("studentmajor", hashedmyClassStudnet.get("studentmajor" + i));
                    student.put("studentcode", hashedmyClassStudnet.get("studentcode" + i));
                    myClassStudents.add(student);
                }
            }
        }
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

    public List getMyClassStudents(){
        return myClassStudents;
    }
}
