package com.smarttab.google_map.Json_datainsert;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by AshwinChauhan on 7/14/2017.
 */

public class HTTPURLConnection {

    String response="";
    URL url;

    public String ServerData(String path, HashMap<String,String> params)
    {
        try {
            url=new URL(path);

            Log.d("URl---Hello", url.toString());
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream outputStream=conn.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            writer.write(getPostDataString(params));
            writer.flush();
            writer.close();
            outputStream.close();

            int respodeCode=conn.getResponseCode();

            if(respodeCode == HttpsURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                    Log.d("OutputLine", line);
                }
            }
            else
            {
                response="";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {

        StringBuilder builder=new StringBuilder();
        boolean first=true;
        for (Map.Entry<String,String>entry : params.entrySet())
        {
            if(first)
            {
                first=false;
            }
            else
            {
                builder.append("&");
                builder.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(),"UTF-8"));

            }
        }

        return builder.toString();
    }
}
