package com.example.voidbluelabtop.sleepinclass.USERDATA;

import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;

/**
 * Created by voidbluelabtop on 17. 6. 25.
 */

public class Preference{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    public Preference(Activity Act) {
        pref = Act.getSharedPreferences("DATA",0);
        editor = pref.edit();
    }
    public Preference(Service Serv) {
        pref = Serv.getSharedPreferences("DATA",0);
        editor = pref.edit();
    }

    public Boolean getBoolean(String Key){
        Boolean temp;
        temp = pref.getBoolean(Key,true);
        return temp;
    }
    public float getFloat(String Key){
        float temp;
        temp = pref.getFloat(Key, 0);
        return temp;
    }
    public int getInt(String Key){
        int temp;
        temp = pref.getInt(Key, 0);
        return temp;
    }
    public long getLong(String Key){
        long temp;
        temp = pref.getLong(Key, 0);
        return temp;
    }
    public String getString(String Key){
        String temp;
        temp = pref.getString(Key, "");
        return temp;
    }



    public void putData(String Key, boolean data){
        editor.putBoolean(Key, data);
        editor.commit();
    }
    public void putData(String Key, float data){
        editor.putFloat(Key, data);
        editor.commit();
    }
    public void putData(String Key, int data){
        editor.putInt(Key, data);
        editor.commit();
    }
    public void putData(String Key, long data){
        editor.putLong(Key, data);
        editor.commit();
    }
    public void putData(String Key, String data){
        editor.putString(Key, data);
        editor.commit();
    }




}
