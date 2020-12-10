package com.sun.twoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {


    private Intent serviceIntent;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.sun.camerademo","com.sun.camerademo.MyService"));
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_bind).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                startService(serviceIntent);
                break;
            case R.id.btn_stop:
                stopService(serviceIntent);
                break;
            case R.id.btn_bind:
                isBound = bindService(serviceIntent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                if (isBound) {
                    unbindService(this);
                    isBound = false;
                }
                break;

        }
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        System.out.println("Bind Service");
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        System.out.println("Unbind Service");
    }
}
