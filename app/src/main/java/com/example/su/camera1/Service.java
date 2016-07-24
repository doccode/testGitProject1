package com.example.su.camera1;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TimeUtils;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by su on 2016/7/9.
 */
public class Service extends android.app.Service {
    private boolean isRun = true;
    int i = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("XT", "Service.onBind()");

        return mBinder;
    }

    private MyBinder mBinder = new MyBinder();
    class MyBinder extends Binder {
        void binderTest() {
            Log.e("XT", "Service.MyBinder.binderTest()");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("XT", "Service.onCreate()" + Thread.currentThread().getName());

        EventBus.getDefault().register(this);


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    Log.e("XT", "Service.run()");
                    SystemClock.sleep(1000);

                }
            }
        }).start();


    }

    @Subscribe
    public void onEventService(Integer i) {
//        Log.e("XT", "Service.onEventService()");
        EventBus.getDefault().post(new String("收到i+1 = "+i++));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isRun = false;
        Log.e("XT", "Service.onDestroy()");
        EventBus.getDefault().unregister(this);
        ;


    }
}
