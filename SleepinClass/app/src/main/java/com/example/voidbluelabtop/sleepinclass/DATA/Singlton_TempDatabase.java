package com.example.voidbluelabtop.sleepinclass.DATA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 7. 26.
 */

class Singlton_TempDatabase {
    private static final Singlton_TempDatabase ourInstance = new Singlton_TempDatabase();

    static Singlton_TempDatabase getInstance() {
        return ourInstance;
    }

    List classtable, studenttable, checktable;
    List classelement, student, check;
    private Singlton_TempDatabase() {
        classtable = new ArrayList();
        studenttable = new ArrayList();
        checktable = new ArrayList();
    }

    public void addclass(String classcode, String time, String beaconMajor){
        classelement.add(classcode);
        classelement.add(time);
        classelement.add(beaconMajor);
    }

    public void addstudent(String classcode, String studentcode, String studentname){
        student.add(classcode);
        student.add(studentcode);
        student.add(studentname);
    }

    public void check(String classcode, String date, String studentcode, boolean ischecked){
        check.add(classcode);
        check.add(date);
        check.add(studentcode);
        check.add(ischecked);
    }


}
