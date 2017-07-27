package com.smarttab.google_map.Custom_Navigation_Drawer.DrawerItemCustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarttab.google_map.Custom_Navigation_Drawer.DataModel.DataModel;
import com.smarttab.google_map.R;

/**
 * Created by AshwinChauhan on 7/11/2017.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[]=null;

    public DrawerItemCustomAdapter(Context mContext,int layoutResourceId,DataModel[] data)
    {
        super(mContext,layoutResourceId,data);
        this.mContext=mContext;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView=convertView;

        LayoutInflater inflaterCompat=((Activity)mContext).getLayoutInflater();
        listView=inflaterCompat.inflate(layoutResourceId,parent,false);

        ImageView imageView=(ImageView)listView.findViewById(R.id.imageViewIcon);
        TextView textView=(TextView)listView.findViewById(R.id.textViewName);

        DataModel folder=data[position];

        imageView.setImageResource(folder.icon);
        textView.setText(folder.name);

        return listView;
        //return super.getView(position, convertView, parent);
    }
}
