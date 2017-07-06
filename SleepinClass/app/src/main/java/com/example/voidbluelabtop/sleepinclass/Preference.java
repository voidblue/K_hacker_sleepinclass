package com.example.voidbluelabtop.sleepinclass;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;

/**
 * Created by voidbluelabtop on 17. 6. 25.
 */

public class Preference{
    private Activity Act;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    Preference(Activity Act) {
        this.Act = Act;
        pref = this.Act.getSharedPreferences("DATA",0);
        editor = pref.edit();
    }

    public Boolean getBoolean(String Key){
        Boolean temp;
        temp = pref.getBoolean(Key,false);
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
