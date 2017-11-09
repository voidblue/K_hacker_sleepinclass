package com.example.voidbluelabtop.sleepinclass.Utils;

import android.util.Log;

import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 10. 14.
 */

public class Get_Current_Class {
    Date date;
    Singleton_UserDataController UDC;
    HashMap current_Class;
    public Get_Current_Class(){
        date = new Date();
        UDC = Singleton_UserDataController.getInstance();
    }

    public HashMap getCurrent_Class(){

        UDC.processClass(GlobalVariables.userCode);
        List classlist = UDC.getMyClasses();
        for (int i = 0 ; i < classlist.size(); i++){
            HashMap inst = (HashMap) classlist.get(i);
            String strdate = (String) inst.get("date");
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            int duration = Integer.parseInt((String)inst.get("duration"));
            Date classdate = null;
            try {
                classdate = SDF.parse(strdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (classdate.getDay() == date.getDay()) {
                int start = classdate.getHours() * 60 + classdate.getMinutes();
                int end = (classdate.getHours() + duration) * 60 + classdate.getMinutes();
//                    Log.d("", "getCurrent_Class: " + currenttime[0] + "    " + currenttime[1]);
                int now = date.getHours() * 60 + date.getMinutes();
                if(now < end && now > start){
                    current_Class = inst;
                    break;
                }
                else{
                    current_Class = null;
                }

            }
        }
        return current_Class;
    }
    public static void main(String args[]){
        Get_Current_Class GCC = new Get_Current_Class();

    }
}
