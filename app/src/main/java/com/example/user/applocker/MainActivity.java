package com.example.user.applocker;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);

        if (!isAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Permission is required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, AppLockService.class);
            startService(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Permission is required", Toast.LENGTH_SHORT).show();

        }
        else
        {
            finish();
            Intent intent = new Intent(this, AppLockService.class);
            startService(intent);

            Intent i = new Intent(this,LockScreen.class);
            startActivity(i);
        }

    }

    private boolean isAccessGranted() {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName);
            }
            return (mode == AppOpsManager.MODE_ALLOWED);

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }

}
