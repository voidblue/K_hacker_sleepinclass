package com.example.voidbluelabtop.sleepinclass.USERDATA;

/**
 * Created by voidbluelabtop on 17. 8. 8.
 */

public class Singleton_Tempdata {
    private static final Singleton_Tempdata ourInstance = new Singleton_Tempdata();

    public static Singleton_Tempdata getInstance() {
        return ourInstance;
    }
    
    int managemode;
    //관리모드 0  item_managestudent
    //관리모드 1  item_attandent_forprofessor
    int sound;
    int classlistpos, studentlistpos;
    
    private Singleton_Tempdata() {
    }
    
    public void setmanagemode(int mode){
        managemode = mode;
    }
    
    
    public int getmanagemode(){
        return managemode;
    }

    public void setSound(int sound){
        this.sound = sound;

    }
    public int getSound(){
        return sound;
    }

    public void setClasslistpos(int pos){
        classlistpos = pos;
    }
    public int getClasslistpos(){
        return classlistpos;
    }

    public void setStudentlistpos(int pos){studentlistpos = pos;}
    public int getStudentlistpos(){return  classlistpos;}

}
