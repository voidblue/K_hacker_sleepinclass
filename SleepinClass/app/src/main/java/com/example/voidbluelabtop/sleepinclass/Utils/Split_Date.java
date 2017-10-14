package com.example.voidbluelabtop.sleepinclass.Utils;

import android.util.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by voidbluelabtop on 17. 8. 9.
 */

public class Split_Date {
    private String[] dates;
    private String date, day, time, starttime, endtime;
    private int starthour, endhour, startminute, endminute, daycode;
//    public Split_Date(String date){
//        this.date = date;
//    }
    public void setdate(String date){
        dates = date.split("\n");
        this.date = dates[0];

    }
    public int getlength(){
        return dates.length;
    }
    public void setdateline(int i){
        this.date = dates[i];
        process();
    }


    private void process() {
        if (date.charAt(0) == '월'){
            day = "월";
            daycode = 1;

        }else if(date.charAt(0) == '화'){
            day = "화";
            daycode = 2;

        }else if(date.charAt(0) == '수'){
            day = "수";
            daycode = 3;

        }else if(date.charAt(0) == '목'){
            day = "목";
            daycode = 4;
        }else if(date.charAt(0) == '금'){
            day = "금";
            daycode = 5;
        }
        time = date.split(day + " ")[1];
        Log.d("", "process: " + time);
        starttime = time.split(" ~ ")[0];
        endtime = time.split(" ~ ")[1];
        starthour = Integer.parseInt(starttime.split(" : ")[0]);
        endhour = Integer.parseInt(endtime.split(" : ")[0]);
        startminute = Integer.parseInt(starttime.split(" : ")[1]);
        endminute = Integer.parseInt(endtime.split(" : ")[1]);


    }

    public int getdaycode(){
        return daycode;
    }
    public int getStarthour(){
        return starthour;
    }
    public int getEndhour(){
        return endhour;
    }
    public int getStartminute(){
        return startminute;
    }
    public int getEndminute(){
        return endminute;
    }
}
