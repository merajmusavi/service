package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {
    private MyBinder myBinder = new MyBinder();

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }

    public class MyBinder extends Binder{
        public BoundService getBoundService(){
            return BoundService.this;

        }
    }

    public String FromService(){
        return "this is from bound service dadash";
    }
}