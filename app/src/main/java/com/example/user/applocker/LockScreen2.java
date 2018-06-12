package com.example.user.applocker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreen2 extends AppCompatActivity {

    TextView one,two,three,four,five,six,seven,eight,nine,zero,pin;
    ImageView next,backspace;
    int oldPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        one = (TextView)findViewById(R.id.one2);
        two = (TextView)findViewById(R.id.two2);
        three = (TextView)findViewById(R.id.three2);
        four = (TextView)findViewById(R.id.four2);
        five = (TextView)findViewById(R.id.five2);
        six = (TextView)findViewById(R.id.six2);
        seven = (TextView)findViewById(R.id.seven2);
        eight = (TextView)findViewById(R.id.eight2);
        nine = (TextView)findViewById(R.id.nine2);
        zero = (TextView)findViewById(R.id.zero2);
        pin = (TextView)findViewById(R.id.pin2);

        next = (ImageView)findViewById(R.id.next2);
        backspace = (ImageView)findViewById(R.id.backspace2);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText(pin.getText().toString()+"0");
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = pin.getText().toString();
                try {
                    s = s.substring(0, s.length() - 1);
                    pin.setText(s);
                }
                catch (Exception e)
                {

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences("Start", MODE_PRIVATE);
                oldPin = prefs.getInt("Pin",0);

                if(oldPin==Integer.parseInt(pin.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Pin matched",Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("Start", MODE_PRIVATE).edit();
                    editor.putInt("appflag",1);

                    editor.apply();
                    finish();


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Pin not matched",Toast.LENGTH_SHORT).show();
                    pin.setText("");

                    finish();

                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(startMain);




                }


            }
        });

    }




}
