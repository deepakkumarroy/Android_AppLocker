package com.example.user.applocker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Navigation extends AppCompatActivity {
    Button Lockapps,Changepin,Shutdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Lockapps = (Button)findViewById(R.id.lockapps);
        Changepin = (Button)findViewById(R.id.changepin);
        Shutdown = (Button)findViewById(R.id.shutdown);

        SharedPreferences.Editor editor = getSharedPreferences("Start", MODE_PRIVATE).edit();
        editor.putInt("flag",1);
        editor.apply();

        Lockapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent i = new Intent(getApplicationContext(),AppList.class);
                startActivity(i);
            }
        });
        Shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        Changepin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences("Start", MODE_PRIVATE).edit();
                editor.putInt("flag",0);
                editor.apply();

                finish();

                Intent i = new Intent(getApplicationContext(),LockScreen.class);
                startActivity(i);

            }
        });



    }
}
