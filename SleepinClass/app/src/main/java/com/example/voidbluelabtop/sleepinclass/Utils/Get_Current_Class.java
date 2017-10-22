package com.example.voidbluelabtop.sleepinclass.Utils;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.DATABASE.UserDataController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 14.
 */

public class Get_Current_Class {
    Date date;
    Split_Date SD;
    UserDataController UDC;
    HashMap current_Class;
    public Get_Current_Class(){
        date = new Date();
        SD = new Split_Date();
        UDC = UserDataController.getInstance();
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


        List classlist = UDC.getMyClasses();
        for (int i = 0 ; i < classlist.size() ; i++){
            HashMap inst = (HashMap) classlist.get(i);
            String time = (String) inst.get("time"+i);
            SD.setdate(time);
            for (int j = 0 ; j < SD.getlength() ; j++ ){
                SD.setdateline(j);
                if (SD.getdaycode() == currentDayCode) {
                    int start = SD.getStarthour() * 60 + SD.getStartminute();
                    int end = SD.getEndminute() * 60 + SD.getEndminute();
                    int now = Integer.parseInt(currenttime[0])*60 + Integer.parseInt(currenttime[1]);
                    if(now < end && now > start){
                        current_Class = inst;
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
