package com.example.su.camera1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private Button bt;
    private TextView tv;
    int i = 0;
    EventBus bus;
    Intent intent;
    private Service.MyBinder mMyBinder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (Service.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("XT", "onRestoreInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("XT", "onSaveInstanceState");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        bt = (Button) findViewById(R.id.bt);
        tv = (TextView) findViewById(R.id.tv);

        intent = new Intent(MainActivity.this, Service.class);

        EventBus.getDefault().register(this);

        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
//                startService(intent);
//                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

//        startService(intent);
        tv.setText("");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                while (true) {
////                    Log.e("XT", "MainActivity.run()");
//                    SystemClock.sleep(1000);
////                    tv.append("发送 i = " + i);
//                    EventBus.getDefault().post(new Integer(i++));
//                }
//            }
//        }).start();

        Log.e("XT", "activity onCreate   " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(String e) {
//        Log.e("XT", "MainActivity.onEventMainThread()");
        tv.append(e);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("XT", "onConfigurationChanged");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        stopService(intent);

        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("XT", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("XT", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("XT", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("XT", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("XT", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("XT", "activity.onDestroy");

        EventBus.getDefault().unregister(this);
    }



}
