package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AdvisoryActivity extends AppCompatActivity {
    JsonService jsonService;
    NetworkingService networkingService;
   String countryCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisory);
        Intent intent = getIntent();
        countryCode = intent.getStringExtra("Countrycode");
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        //networkingService.listener =this;
    }
}