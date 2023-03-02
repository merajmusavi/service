package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BoundService boundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this is for foreground service
        /*Intent intent = new Intent(this,MyService.class);
        startService(intent);*/

        // this is for bound service

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(boundService, ""+boundService.FromService(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            boundService = myBinder.getBoundService();

            boundService.FromService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundService = null;

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,serviceConnection, BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}