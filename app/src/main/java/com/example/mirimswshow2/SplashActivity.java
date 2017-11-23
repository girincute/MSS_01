package com.example.mirimswshow2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getWindow().setStatusBarColor(Color.parseColor("BLACK"));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, 1700);// 3 초
    }

}
