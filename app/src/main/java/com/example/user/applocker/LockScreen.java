package com.example.user.applocker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LockScreen extends AppCompatActivity {
    List<Been> list = new ArrayList<Been>();
    Been been;
    Database database;
    TextView one,two,three,four,five,six,seven,eight,nine,zero,pin;
    ImageView next,backspace;
    int flag;
    int oldPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        one = (TextView)findViewById(R.id.one);
        two = (TextView)findViewById(R.id.two);
        three = (TextView)findViewById(R.id.three);
        four = (TextView)findViewById(R.id.four);
        five = (TextView)findViewById(R.id.five);
        six = (TextView)findViewById(R.id.six);
        seven = (TextView)findViewById(R.id.seven);
        eight = (TextView)findViewById(R.id.eight);
        nine = (TextView)findViewById(R.id.nine);
        zero = (TextView)findViewById(R.id.zero);
        pin = (TextView)findViewById(R.id.pin);

        next = (ImageView)findViewById(R.id.next);
        backspace = (ImageView)findViewById(R.id.backspace);

        SharedPreferences prefs = getSharedPreferences("Start", MODE_PRIVATE);
         flag = prefs.getInt("flag",0);
//        if (restoredText != null) {
//            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
//            int idName = prefs.getInt("idName", 0); //0 is the default value.
//        }

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

        been = new Been();
        database = new Database(getApplicationContext());

        if(flag == 0)
        {
            pin.setHint("Set up Pin");

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    been.setPin(Integer.parseInt(pin.getText().toString()));

//                    if (database.PinInsert(been))
//                    {
//                        Toast.makeText(getApplicationContext(),"Password set Successfully",Toast.LENGTH_SHORT).show();
//
//                        Intent i = new Intent(getApplicationContext(), Navigation.class);
//                        startActivity(i);
//
//
//                    }
//                    else Toast.makeText(getApplicationContext(),"Data Insertion Failed",Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getSharedPreferences("Start", MODE_PRIVATE).edit();
                    editor.putInt("Pin",Integer.parseInt(pin.getText().toString()));
                    editor.apply();

                    Toast.makeText(getApplicationContext(),"Password set Successfully",Toast.LENGTH_SHORT).show();

                    finish();
//
                        Intent i = new Intent(getApplicationContext(), Navigation.class);
                        startActivity(i);

                }
            });

        }
        else
        {
            pin.setHint("Enter Your Pin");

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    SharedPreferences prefs = getSharedPreferences("Start", MODE_PRIVATE);
                    oldPin = prefs.getInt("Pin",0);

                    if(oldPin==Integer.parseInt(pin.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Pin matched",Toast.LENGTH_SHORT).show();

                        finish();

                        Intent i = new Intent(getApplicationContext(), Navigation.class);
                        startActivity(i);
                    }
                    else
                        {
                            Toast.makeText(getApplicationContext(),"Pin not matched",Toast.LENGTH_SHORT).show();
                            pin.setText("");
                        }



                    //showData(database.PinCheck());

//                    List<Been> list = new ArrayList<Been>();
//                    list = database.PinCheck();
//
//                    List<String> list1 = new ArrayList<String>();
//
//                    for(Been been:list)
//                    {
//                        list1.add(been.getPin().toString());
//                    }
//                    if(list1.contains(pin.getText().toString()))
//                    {
//                        Toast.makeText(getApplicationContext(),"Pin matched",Toast.LENGTH_SHORT).show();
//
//                        Intent i = new Intent(getApplicationContext(), Navigation.class);
//                        startActivity(i);
//                    }
//                    else
//                        {
//                            Toast.makeText(getApplicationContext(),"Pin not matched",Toast.LENGTH_SHORT).show();
//                        }

                }
            });
        }

    }
    public void showData(List<Been> list)
    {
        for(Been been:list)
        {
            Log.d("All Data ",been.getPin().toString());
        }

    }
}
