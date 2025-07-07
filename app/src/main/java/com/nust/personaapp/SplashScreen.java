package com.nust.personaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;


public class SplashScreen extends AppCompatActivity implements Runnable{

    public static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(this, SPLASH_TIME_OUT);
    }

    // Run the LoginPageActivity after the splash screen
    @Override
    public void run() {
        Intent intent = new Intent(SplashScreen.this, LoginPageActivity.class);
        startActivity(intent);
        finish();
    }
}