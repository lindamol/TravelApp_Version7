package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.traveladvisoryapp.NetworkingService;

public class InformationActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {
    JsonService jsonService;
    NetworkingService networkingService;
  TextView countryName_texview;
  String SelectedCountry ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        countryName_texview = findViewById(R.id.countrynametexview);
        Intent intent = getIntent();
       SelectedCountry = intent.getStringExtra("SelectedCountry");
       jsonService = ( (myApp)getApplication()).getJsonService();
       networkingService = ( (myApp)getApplication()).getNetworkingService();
       networkingService.listener =this;
       networkingService.fetchCountryInfo(SelectedCountry);

    }

    @Override
    public void APINetworkListner(String jsonString) {
        String mystring = jsonString;
        System.out.println("Printing JSON In InformationActivity");
    }
}