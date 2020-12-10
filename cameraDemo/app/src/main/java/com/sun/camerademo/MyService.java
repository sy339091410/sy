package com.sun.camerademo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        new MyServiceInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
        };
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("start service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("destroy service");
    }

    public void printThings(){
        System.out.println("hello");
    }


}
