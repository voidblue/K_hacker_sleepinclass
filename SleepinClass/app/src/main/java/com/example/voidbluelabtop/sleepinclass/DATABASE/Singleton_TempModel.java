package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.support.constraint.solver.Goal;

import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 26.
 */

public class Singleton_TempModel {
    private static final Singleton_TempModel ourInstance = new Singleton_TempModel();

    public static Singleton_TempModel getInstance() {
        return ourInstance;
    }

    List classtable, studenttable, attandanttable;
    List classelement, student, attandant;
    GetData GD = new GetData();

    private Singleton_TempModel() {
        classtable = new ArrayList();
        studenttable = new ArrayList();
        attandanttable = new ArrayList();





    }

    //로그인 API 제일 중요
    public void addclass(String classname, String time, String classroom, String beaconMajor, String classcode, String password){
//
//        //Select * from class
//        classelement = new ArrayList();
//        classelement.add(classname);
//        classelement.add(time);
//        classelement.add(classroom);
//        classelement.add(beaconMajor);
//        classelement.add(classcode);
////        classelement.add(password);
//        classtable.add(classelement);
    }

    public void addstudent( String studentname, String studentmajor, String studentcode){
        //Select * from student
        student = new ArrayList();
        student.add(studentname);
        student.add(studentmajor);
        student.add(studentcode);
        studenttable.add(student);
    }
    public void attand(String classcode, String date, String studentcode, boolean ischecked){
        attandant = new ArrayList();
        attandant.add(classcode);
        attandant.add(date);
        attandant.add(studentcode);
        attandant.add(ischecked);
        attandanttable.add(attandanttable);
    }

    public void getxxx(){
        //이런 함수들은 컨트롤러가 받아가 처리하고 나서 각 액티비티에 적용시킬것
    }

    public List getclass(){
        return classtable;
    }
    public List getStudenttable(){return studenttable;}


}
