package com.example.user.applocker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Vault extends AppCompatActivity {
    ListView vaultlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        vaultlist = (ListView)findViewById(R.id.vaultlist);

        Database database = new Database(this);

        //showData(database.getallData());

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.

        List<Been> list = new ArrayList<Been>();
        list = database.PinCheck();

        List<String> list1 = new ArrayList<String>();

        for(Been been:list)
        {
            list1.add(been.getPin().toString());
        }

        String[] array1 = new String[list1.size()];
        array1 = list1.toArray(array1);


//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newlist );
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, list1 );

        vaultlist.setAdapter(arrayAdapter);
    }
}
