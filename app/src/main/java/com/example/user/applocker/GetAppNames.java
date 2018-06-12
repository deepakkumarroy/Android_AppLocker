package com.example.user.applocker;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;


public class GetAppNames extends IntentService {

    public GetAppNames() {
        super("GetAppNames");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("text","Handle");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        try {
            final long period = 300;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    Log.d("Appname",getForegroundApp());

                }
            }, 0, period);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public String getForegroundApp() {
        String currentApp = "NULL";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usm = (UsageStatsManager) this.getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            UsageEvents usageEvents = usm.queryEvents(time - 1000 * 30, System.currentTimeMillis() + (10 * 1000));
            UsageEvents.Event event = new UsageEvents.Event();
            while (usageEvents.hasNextEvent()) {
                usageEvents.getNextEvent(event);
            }
            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 1000, time);

            if (appList != null && appList.size() > 0 )
            {
                TreeMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : appList) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        } else {
            ActivityManager am = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
            currentApp = tasks.get(0).processName;
        }

        return currentApp;
    }


}
