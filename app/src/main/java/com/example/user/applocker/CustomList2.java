package com.example.user.applocker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 16-Oct-17.
 */

public class CustomList2 extends BaseAdapter {
    String [] result1;
    Drawable[] result2;
    final ArrayList<Integer> result3 = new ArrayList<Integer>();
    ArrayList<String> result4;
    Been been = new Been();
    Context context;
    Database database;
    private static LayoutInflater inflater=null;
    public CustomList2(Vault vault, String[] Vaultlist, Drawable[] Appicon, ArrayList<String> AppPackagename) {
        // TODO Auto-generated constructor stub
        result1=Vaultlist;
        result2=Appicon;
        result4=AppPackagename;
        context=vault;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result1.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView appname;
        ImageView appicon;
        Switch appswitch;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Log.d("update","update");
        context=parent.getContext();
        database = new Database(context);
        final Holder holder=new Holder();
        //View rowView;
        convertView = inflater.inflate(R.layout.customlist, null);
        holder.appname=(TextView) convertView.findViewById(R.id.appname);
        holder.appicon=(ImageView) convertView.findViewById(R.id.appimage);
        holder.appswitch=(Switch) convertView.findViewById(R.id.appswitch);




        holder.appname.setText(result1[position]);
        holder.appicon.setImageDrawable(result2[position]);

//        holder.appswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                holder.appswitch.setChecked(b);
//            }
//        });



        holder.appswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(holder.appswitch.isChecked())
                {
                    //result3.add(position);
                    been.setAppName(result4.get(position));

                    if (database.AppInsert(been))
                    {
                        Toast.makeText(context,"Data Insertion Successful",Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(context,"Data Insertion Failed",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //result3.remove(result3.indexOf(position));
                    been.setAppName(result4.get(position));

                    if (database.AppRemove(been))
                    {
                        Toast.makeText(context,"Data Removal Successful",Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(context,"Data Removal Failed",Toast.LENGTH_SHORT).show();
                }
                //Log.d("ArrayList",result3.toString());


            }
        });

//        try {
//            if (result3.get(position) == position) {
//                holder.appswitch.setChecked(true);
//            }
//        }
//        catch (Exception e){}


        // holder.appswitch.setChecked(true);

        return convertView;
    }
}
