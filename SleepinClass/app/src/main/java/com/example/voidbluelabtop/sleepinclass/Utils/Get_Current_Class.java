package com.example.voidbluelabtop.sleepinclass.Utils;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 14.
 */

public class Get_Current_Class {
    Singleton_TempModel ST;
    Date date;
    Split_Date SD;
    public Get_Current_Class(){

        date = new Date();
        SD = new Split_Date();
        ST = Singleton_TempModel.getInstance();
    }

    public String getCurrent_Class(){
        int currentDayCode;
        List currentClass = new ArrayList();
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


        List classlist = ST.getclass();
        for (int i = 0 ; i < classlist.size() ; i++){
            List list = (List)classlist.get(i);
            String time = (String) list.get(2);
            SD.setdate(time);
            for (int j = 0 ; j < SD.getlength() ; j++ ){
                SD.setdateline(j);
                if (SD.getdaycode() == currentDayCode) {
                    int start = SD.getStarthour() * 60 + SD.getStartminute();
                    int end = SD.getEndminute() * 60 + SD.getEndminute();
                    int now = Integer.parseInt(currenttime[0])*60 + Integer.parseInt(currenttime[1]);
                    if(now < end && now > start){
                        current_ClassCode = (String)list.get(4);
                    }
                }
            }
        }
        return current_ClassCode;
    }
    public static void main(String args[]){
        Get_Current_Class GCC = new Get_Current_Class();

    }
}
