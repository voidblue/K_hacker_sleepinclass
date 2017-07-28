package com.alamkanak.weekview;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jesse on 6/02/2016.
 */
public class WeekViewUtil {


    /////////////////////////////////////////////////////////////////
    //
    //      Helper methods.
    //
    /////////////////////////////////////////////////////////////////

    /**
     * Checks if two times are on the same day.
     * @param dayOne The first day.
     * @param dayTwo The second day.
     * @return Whether the times are on the same day.
     */
    public static boolean isSameDay(Calendar dayOne, Calendar dayTwo) {
        return dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR) && dayOne.get(Calendar.DAY_OF_YEAR) == dayTwo.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Returns a calendar instance at the start of this day
     * @return the calendar instance
     */
    public static Calendar today(){
        Calendar today = Calendar.getInstance();
        return today;
    }
    //가장 가까운 월요일 찾는 기능 추가 및 월요일을 베이스로 하게 라이브러리 수정
    public static Calendar nearestmonday(){
        Calendar nearestmonday = Calendar.getInstance();
        int i = 0;
        Date date = nearestmonday.getTime();
        while(true) {
            if (date.getDay() == 1) {
                break;
            }
            date.setDate(date.getDate()-1);

        }
        nearestmonday.setTime(date);
//        today.set(Calendar.HOUR_OF_DAY, 0);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//        today.set(Calendar.MILLISECOND, 0);
        return nearestmonday;
    }
}
