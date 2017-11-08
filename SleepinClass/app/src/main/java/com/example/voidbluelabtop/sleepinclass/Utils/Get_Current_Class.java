package com.example.voidbluelabtop.sleepinclass.Utils;

import android.util.Log;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 14.
 */

public class Get_Current_Class {
    Date date;
    Split_Date SD;
    Singleton_UserDataController UDC;
    HashMap current_Class;
    public Get_Current_Class(){
        date = new Date();
        SD = new Split_Date();
        UDC = Singleton_UserDataController.getInstance();
    }

    public HashMap getCurrent_Class(){
        int currentDayCode;

        String current_ClassCode = "None";
        String[] spliteddate = date.toString().split(" ");
        String[] currenttime = spliteddate[4].split(":");

        if (spliteddate[0].equals("Mon")){
            currentDayCode = 1;
        }else if (spliteddate[0].equals("Tue")){
            currentDayCode = 2;
        }else if (spliteddate[0].equals("Wen")){
            currentDayCode = 3;
        }else if (spliteddate[0].equals("Thu")){
            currentDayCode = 4;
        }else if (spliteddate[0].equals("Fri")){
            currentDayCode = 5;
        }
        else {
            currentDayCode = 1000000;
        }

        UDC.processProfessorClass(GlobalVariables.userCode);
        List classlist = UDC.getProfessorclasses();
        for (int i = 0 ; i < classlist.size(); i++){
            HashMap inst = (HashMap) classlist.get(i);
            String time = (String) inst.get("time");
            SD.setdate(time);
            for (int j = 0 ; j < SD.getlength() ; j++ ){
                SD.setdateline(j);
                if (SD.getdaycode() == currentDayCode) {
                    int start = SD.getStarthour() * 60 + SD.getStartminute();
                    int end = SD.getEndminute() * 60 + SD.getEndminute();
                    Log.d("", "getCurrent_Class: " + currenttime[0] + "    " + currenttime[1]);
                    int now = Integer.parseInt(currenttime[0])*60 + Integer.parseInt(currenttime[1]);
                    if(now < end && now > start){
                        current_Class = inst;
                        break;
                    }
                    else{
                        current_Class = null;
                    }
                }
            }
        }
        return current_Class;
    }
    public static void main(String args[]){
        Get_Current_Class GCC = new Get_Current_Class();

    }
}
