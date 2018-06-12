package com.example.user.applocker;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppList extends AppCompatActivity {
    ListView applist;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        applist = (ListView)findViewById(R.id.applist);
        button = (Button)findViewById(R.id.addtovault);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Navigation.class);
                startActivity(i);

                Toast.makeText(getApplicationContext(),"Selections are Updated",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        ArrayList<String> packagenameArray = new ArrayList<String>();
        ArrayList<String> appnameArray = new ArrayList<String>();
        ArrayList<Drawable> iconArray = new ArrayList<Drawable>();

        PackageManager packageManager = getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

//        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);
//        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(packageManager));
        List<ApplicationInfo> packs = packageManager.getInstalledApplications(0);
        Collections.sort(packs, new ApplicationInfo.DisplayNameComparator(packageManager));
        for(int i=0; i < packs.size(); i++) {
            ApplicationInfo p = packs.get(i);
            //ApplicationInfo a = p.applicationInfo;
//            if((p.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
//                continue;
//            }

            packagenameArray.add(p.packageName);
            appnameArray.add(p.loadLabel(getPackageManager()).toString());
            iconArray.add(p.loadIcon(getPackageManager()));

        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, appnameArray);
        //ArrayAdapter<Drawable> arrayAdapter = new ArrayAdapter<Drawable>(this, android.R.layout.simple_list_item_1, iconArray);
        //applist.setAdapter(arrayAdapter);

        String[] Stringarray = appnameArray.toArray(new String[appnameArray.size()]);
        String[] Stringarray1 = packagenameArray.toArray(new String[packagenameArray.size()]);
        Drawable[] Drawablearray = iconArray.toArray(new Drawable[iconArray.size()]);
        applist.setAdapter(new CustomList(this, Stringarray,Drawablearray,packagenameArray));

//        ArrayList<String> appnameArray1 = new ArrayList<String>();
//        ArrayList<String> packagenameArray1 = new ArrayList<String>();
//        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
//
//        ArrayList<AppInfo> res = new ArrayList<AppInfo>();
//        for(int i=0;i<apps.size();i++) {
//            PackageInfo p = apps.get(i);
//
//            AppInfo newInfo = new AppInfo();
//            newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
//            newInfo.pname = p.packageName;
//            newInfo.versionName = p.versionName;
//            newInfo.versionCode = p.versionCode;
//            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
//
//            //appnameArray1.add(newInfo.appname);
//            //packagenameArray1.add(newInfo.pname);
//
//            res.add(newInfo);
//        }
//
////        for(int i=0;i<res.size();i++)
////        {
////            appnameArray1.add(res.get(i).appname);
////        }
//
//
//
//
//        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res.get(1).appname);
//        applist.setAdapter(arrayAdapter1);
      }
}

