package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.ArrayList;

public class AdvisoryActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {
    JsonService jsonService;
    NetworkingService networkingService;
   String countryCode;
    ArrayList<AdvisoryInfo> advisoryInfo = new ArrayList<AdvisoryInfo>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisory);
        Intent intent = getIntent();
        countryCode = intent.getStringExtra("Countrycode");
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        networkingService.listener =this;
        networkingService.fetchAdvisoryInfo(countryCode);
    }

    @Override
    public void APINetworkListner(String jsonString) {
        String code = countryCode.toUpperCase();
        advisoryInfo = jsonService.parseAdvisoryInfo(jsonString,code);
        String message = advisoryInfo.get(0).getAdvisoryMessage();
        System.out.println("This is my message from final Actvity:" +message);
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }
}