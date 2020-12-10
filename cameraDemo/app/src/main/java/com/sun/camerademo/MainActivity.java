package com.sun.camerademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this,MyService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this,MyService.class));
    }
}