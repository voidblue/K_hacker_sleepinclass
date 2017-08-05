package com.example.voidbluelabtop.sleepinclass.MainView;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.voidbluelabtop.sleepinclass.R;

public class NoticeLicense extends AppCompatActivity {

    private Handler handler;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            Intent intent = new Intent(NoticeLicense.this, MainActivity.class);
//            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_license);
        init();

        handler.postDelayed(runnable, 2000);
    }

    public void init() {
        handler = new Handler();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}
