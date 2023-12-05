package sn.ept.git.dic2.ventevelosandroid.utils;

import android.app.Application;
import android.util.Log;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MyApplication extends Application {

    private static final long WORK_INTERVAL_MINUTES = 15;

    @Override
    public void onCreate() {
        super.onCreate();

    }


}
