package com.smarttab.google_map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smarttab.google_map.CustomDialog.Custom;
import com.smarttab.google_map.Custom_Navigation_Drawer.Navigation;
import com.smarttab.google_map.Json_datainsert.JsonDataInsert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.btn_map);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });
        Button btn_input=(Button)findViewById(R.id.btn_input);
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Input_Location.class));
            }
        });
        Button btn_notification=(Button)findViewById(R.id.notifcation_main);
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Notification.class));
            }
        });
        Button btn_custom=(Button)findViewById(R.id.customDialog);
        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Custom.class));
            }
        });
        Button btn_navigation=(Button)findViewById(R.id.custom_navigation);
        btn_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Navigation.class));
            }
        });
        Button btn_json=(Button)findViewById(R.id.JSON);
        btn_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JsonDataInsert.class));
            }
        });
    }

}
