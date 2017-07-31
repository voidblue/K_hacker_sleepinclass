package com.example.voidbluelabtop.sleepinclass.DATABASE;

/**
 * Created by voidbluelabtop on 17. 6. 25.
 */

public class Singleton_TempController {
    private static final Singleton_TempController ourInstance = new Singleton_TempController();

    public static Singleton_TempController getInstance() {
        return ourInstance;
    }

    private String classroom;



    private Singleton_TempController() {
    }



    public void setclassroom(String classroom){
        this.classroom = classroom;
    }

    public String getclassromm(){
        return classroom;
    }
}
