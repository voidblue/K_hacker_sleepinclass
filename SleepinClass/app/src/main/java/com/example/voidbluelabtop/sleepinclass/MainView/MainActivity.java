package com.example.voidbluelabtop.sleepinclass.MainView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.alamkanak.weekview.WeekViewUtil;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_UserDataController;
import com.example.voidbluelabtop.sleepinclass.FORSTUDENT.Attendant;
import com.example.voidbluelabtop.sleepinclass.Services.BeaconDetect;
import com.example.voidbluelabtop.sleepinclass.DATABASE.Singleton_TempModel;
import com.example.voidbluelabtop.sleepinclass.FORPROFESSOR.CreateClass;
import com.example.voidbluelabtop.sleepinclass.FORPROFESSOR.MyClasslist;
import com.example.voidbluelabtop.sleepinclass.FORSTUDENT.Enroll_class;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;
import com.example.voidbluelabtop.sleepinclass.USERDATA.Singleton_Tempdata;
import com.example.voidbluelabtop.sleepinclass.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends WeekView_BASE implements NavigationView.OnNavigationItemSelectedListener {
    private boolean refresh = false;
    private Singleton_Tempdata tempdata;
    public static Toolbar toolbar;
    private Bundle state;
    private Singleton_UserDataController UDC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainview);
        state = savedInstanceState;
        tempdata = Singleton_Tempdata.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Time Table");
        toolbar.setTitleTextColor(0x99000000);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        Menu xxx = navigationView.getMenu();
//        int idcode = 0;
//
//        if (idcode == 0) {
//            xxx.add("강의 개설");
//            xxx.add("강사메뉴2");
//            xxx.add("강사메뉴3");
//
//            xxx.getItem(0).setIcon(R.drawable.ic_menu_camera);
//        }

        Intent beacondetect = new Intent(getApplicationContext(), BeaconDetect.class);
        startService(beacondetect);



        //Weekview와 관련된 초기설정 부분
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        mWeekView.setNumberOfVisibleDays(5);
        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        int y = mWeekView.getHourHeight() + mWeekView.getColumnGap();
        y = y*12 + mWeekView.getHeaderColumnPadding() + mWeekView.getHeaderRowPadding();

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        if(height < y){
            mWeekView.setHourHeight((height - mWeekView.getHeaderRowPadding() - mWeekView.getHeaderColumnPadding())
                    /12 - mWeekView.getColumnGap());
        }
        //스크롤 불가능하게 막아두기
        mWeekView.setVerticalFlingEnabled(false);
        mWeekView.setScrollListener(null);


        setupDateTimeInterpreter(false);

        //             앱이켜지면 비콘 탐지 서비스시작
        //


        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION},32);
        Intent beacondetectservice = new Intent(getApplicationContext(), BeaconDetect.class);
        startService(beacondetectservice);


        Singleton_TempModel STM = Singleton_TempModel.getInstance();
        if(STM.getStudenttable().size() == 0) {
            STM.addstudent("학생1", "컴공", "학번ㅌㅌㅌ");
            STM.addstudent("학생2", "건공", "학번ㅊㅊㅊ");
            STM.addstudent("학생3", "식공", "학번ㅍㅍㅍ");
        }
        refresh = true;


    }
    @Override
    public void onRestart(){
        super.onRestart();
        recreate();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 32: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    //폰 고유번호 가져오기
    public void precess() {
        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String xxx = manager.getDeviceId(); }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.d("", "onBackPressed: " + drawer.getChildAt(0));
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.item_createclass) {
            Intent i = new Intent(getApplicationContext(), CreateClass.class);
            startActivity(i);
        }
        else if (id == R.id.item_managestudent) {
            Intent i = new Intent(getApplicationContext(), MyClasslist.class);
            tempdata.setmanagemode(0);
            startActivity(i);
        } else if (id == R.id.item_attandent_forprofessor) {
            Intent i = new Intent(getApplicationContext(), MyClasslist.class);
            tempdata.setmanagemode(1);
            startActivity(i);
        } else if (id == R.id.item_enrollclass) {
            Enroll_class EC = new Enroll_class(this);
            EC.show();
        } else if (id == R.id.item_attandent_forstudent){
            Intent i = new Intent(getApplicationContext(), Attendant.class);
            startActivity(i);
        } else if(id == R.id.item_licence){
            Intent i = new Intent(getApplicationContext(), NoticeLicense.class);
            startActivity(i);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    //////////////////////////////////////////////////////////
    //                                                      //
    //         타임테이블에 시간표 넣는 부분                    //
    //                                                      //
    //////////////////////////////////////////////////////////
    //TODO 데이터베이스에서 받아온값을 넣을수 있도록 해보자
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        UDC = Singleton_UserDataController.getInstance();
        List myClasses = new ArrayList();
        if (GlobalVariables.userauth == 0){
            UDC.processClass(GlobalVariables.userCode);
            myClasses = UDC.getMyClasses();
        }
        else if (GlobalVariables.userauth == 1){
            UDC.processProfessorClass(GlobalVariables.userCode);
            myClasses = UDC.getProfessorclasses();
        }


//        Singleton_TempModel ST = Singleton_TempModel.getInstance();
        WeekViewEvent event;
        Log.d("", "onMonthChange: " + myClasses.size());
        for(int i = 0 ; i < myClasses.size() ; i++){

            HashMap obj = (HashMap)myClasses.get(i);
            String strdate = (String)obj.get("date");
            int duration = Integer.parseInt((String)obj.get("duration"));
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            try {
                date = SDF.parse(strdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String TAG = "time";

//            Toast.makeText(this, date.toString(),Toast.LENGTH_LONG).show();


            Calendar startTime = Calendar.getInstance();
            startTime.setTime(WeekViewUtil.nearestmonday().getTime());
            startTime.set(Calendar.HOUR_OF_DAY, date.getHours() + 15);
//            Log.d(TAG, "onMonthChange: " + Calendar.HOUR_OF_DAY + "" + SD.getStarthour());
            startTime.set(Calendar.DAY_OF_WEEK, date.getDay());
            startTime.set(Calendar.MINUTE, date.getMinutes());
            startTime.set(Calendar.MONTH, newMonth - 1);
            startTime.set(Calendar.YEAR, newYear);
            Calendar endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.DAY_OF_WEEK, date.getDay());
            endTime.set(Calendar.HOUR, date.getHours() + duration +15);
//            Log.d(TAG, "onMonthChange: " + Calendar.HOUR + "" + date.getEndhour());
            endTime.set(Calendar.MINUTE, date.getMinutes());
            endTime.set(Calendar.MONTH, newMonth - 1);
            event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
            event.setName((String)obj.get("classname") +"\n" + (String)obj.get("classroom"));
            event.setColor(getResources().getColor(R.color.event_color_01));
            events.add(event);

        }
        return events;
    }
}
