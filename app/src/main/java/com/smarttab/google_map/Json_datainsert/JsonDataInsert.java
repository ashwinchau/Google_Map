package com.smarttab.google_map.Json_datainsert;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarttab.google_map.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonDataInsert extends AppCompatActivity {

    EditText edt_mobile,edt_name;
    Button btn_insert,btn_insertMy;

    ProgressDialog progressDialog;
    JSONObject jsonObject;
    int success=0;

    HTTPURLConnection connection;

    String strname="",strMobile="";

    String path="http://192.168.2.103/Home/myDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data_insert);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_mobile=(EditText)findViewById(R.id.edt_mobile);
        btn_insert=(Button)findViewById(R.id.btn_insert);
        btn_insertMy=(Button)findViewById(R.id.btn_insertMy);


        btn_insertMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_name.getText().toString().equals("") && !edt_mobile.getText().toString().equals("") )
                {
                    strname=edt_name.getText().toString();
                    strMobile=edt_mobile.getText().toString();

                    new PostDataMyJson().execute();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Enter all fields", Toast.LENGTH_LONG).show();
                }

            }
        });
        connection=new HTTPURLConnection();
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_name.getText().toString().equals("") && !edt_mobile.getText().toString().equals("") )
                {
                    strname=edt_name.getText().toString();
                    strMobile=edt_mobile.getText().toString();

                    new PostDataTOServer().execute();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Enter all fields", Toast.LENGTH_LONG).show();
                }
            
            }
        });
    }

    private class PostDataTOServer extends AsyncTask<Void,Void,Void> {

        String response="";

        HashMap<String,String> postDataParams;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  Toast.makeText(JsonDataInsert.this, "onPreExecute", Toast.LENGTH_SHORT).show();
            progressDialog=new ProgressDialog(JsonDataInsert.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            postDataParams=new HashMap<String, String>();
            postDataParams.put("name",strname);
            postDataParams.put("mobile",strMobile);

            response=connection.ServerData(path,postDataParams);
            try {
                jsonObject=new JSONObject(response);
                jsonObject.getInt("success");
                //Toast.makeText(JsonDataInsert.this, "OnDoinBackground"+response, Toast.LENGTH_SHORT).show();
                success=jsonObject.getInt("success");
                Toast.makeText(JsonDataInsert.this, ""+success, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog.isShowing())
                progressDialog.dismiss();
           // Toast.makeText(JsonDataInsert.this, "Success"+success, Toast.LENGTH_SHORT).show();
            if(success==1) {
                Toast.makeText(getApplicationContext(), "Employee Added successfully..!", Toast.LENGTH_LONG).show();
            }
        }
    }


    private class PostDataMyJson extends AsyncTask {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(JsonDataInsert.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Object doInBackground(Object[] params) {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, config.insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object=new JSONObject(response);
                        Toast.makeText(JsonDataInsert.this, ""+object, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>stringMap=new HashMap<String, String>();

                    stringMap.put(config.Name,strname);
                    stringMap.put(config.Mobile,strMobile);
                    return stringMap;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(JsonDataInsert.this);
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }
}
