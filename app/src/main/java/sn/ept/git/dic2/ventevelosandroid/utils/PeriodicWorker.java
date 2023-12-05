package sn.ept.git.dic2.ventevelosandroid.utils;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import sn.ept.git.dic2.ventevelosandroid.services.LocalisationUpdateService;
import sn.ept.git.dic2.ventevelosandroid.services.StockService;

public class PeriodicWorker extends Worker {

    public PeriodicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context context = getApplicationContext();
        Intent stockServiceIntent = new Intent(context, StockService.class);
        context.startService(stockServiceIntent);
        Intent localisationServiceIntent = new Intent(context, LocalisationUpdateService.class);
        context.startService(localisationServiceIntent);

        return Result.success();
    }
}
