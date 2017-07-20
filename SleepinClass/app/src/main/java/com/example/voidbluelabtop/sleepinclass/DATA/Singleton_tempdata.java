package com.example.voidbluelabtop.sleepinclass.DATA;

/**
 * Created by voidbluelabtop on 17. 6. 25.
 */

public class Singleton_tempdata {
    private static final Singleton_tempdata ourInstance = new Singleton_tempdata();

    public static Singleton_tempdata getInstance() {
        return ourInstance;
    }

    private String classroom;



    private Singleton_tempdata() {
    }



    public void setclassroom(String classroom){
        this.classroom = classroom;
    }

    public String getclassromm(){
        return classroom;
    }
}
