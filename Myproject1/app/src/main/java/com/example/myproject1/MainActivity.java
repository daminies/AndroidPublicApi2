package com.example.myproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;



public class MainActivity extends AppCompatActivity {
    String authApiKey = "updbj%2BlRNiUy4C0QAPWo4RgnWH%2Bwfh3qm6lalt87wpSBQoiofuQl00IIn87lQ8hGmE6SFEKG%2FF9Z9c8kGlNazw%3D%3D";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.enableDefaults();

        TextView status1 = (TextView) findViewById(R.id.result);
        BufferedReader br = null;
        try{
            String urlstr = "http://openapi.jeonju.go.kr/rest/streetlamp/getStreetlamp?ServiceKey=" + authApiKey;
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while((line = br.readLine()) != null) {
                result = result + line +"\n";
            }
            status1.setText(result);
            System.out.println(result);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }


    }
}