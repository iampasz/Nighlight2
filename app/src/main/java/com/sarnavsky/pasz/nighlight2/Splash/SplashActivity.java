package com.sarnavsky.pasz.nighlight2.Splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.sarnavsky.pasz.nighlight2.MainActivity;



public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  ((AnimationDrawable) getWindow().getDecorView().getBackground()).start();


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}