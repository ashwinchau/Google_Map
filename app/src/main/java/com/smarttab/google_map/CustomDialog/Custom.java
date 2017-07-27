package com.smarttab.google_map.CustomDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarttab.google_map.R;

public class Custom extends AppCompatActivity implements View.OnClickListener {

    final Context context=this;
    private Button button,button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        button=(Button)findViewById(R.id.btn_custom);
        button1=(Button)findViewById(R.id.btn_alertDialog);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(Custom.this).create();
                alertDialog.setTitle("Alert Dialog Title");
                alertDialog.setMessage("Here is android alert dialog message");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //custom Dialog

        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialogbox);
        dialog.setTitle("Open Dialog Box");

        TextView textView=(TextView)dialog.findViewById(R.id.cu_text);
        textView.setText("Android custom dialog example!");
        ImageView imageView=(ImageView)dialog.findViewById(R.id.cu_image);
        imageView.setImageResource(R.mipmap.ic_launcher);

        Button cu_dialog=(Button)dialog.findViewById(R.id.cu_dialogButtonOK);
        cu_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
