package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.traveladvisoryapp.NetworkingService;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {
    JsonService jsonService;
    NetworkingService networkingService;
    RecyclerView recyclerViewInfo;
  TextView countryName_texview;
  ImageView flagimage;
  Button moreadviseButton;
  String SelectedCountry ="";
    Boolean firstfetch = true;
    String code;
    InfoAdapter adapter;
    ArrayList<CountryInfo> countryInfo = new ArrayList<CountryInfo>(0);
    ArrayList<String> vaccInfo = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        countryName_texview = findViewById(R.id.countrynametexview);
        flagimage = findViewById(R.id.flagimage);
        moreadviseButton = findViewById(R.id.moreAdvisory);
        recyclerViewInfo = findViewById(R.id.info_recyclerview);
        recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        SelectedCountry = intent.getStringExtra("SelectedCountry");
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        networkingService.listener =this;
        networkingService.fetchCountryInfo(SelectedCountry);
        adapter = new InfoAdapter(this,vaccInfo);
       recyclerViewInfo.setAdapter(adapter);

       //jsonService.parseCountryInfo()

    }

    @Override
    public void APINetworkListner(String jsonString) {
              {
        countryInfo =  jsonService.parseCountryInfo(jsonString);
//        String vaccinename =countryInfo.get(0).vaccName.get(0);
        code = (countryInfo.get(0).countryCode).toLowerCase();
             vaccInfo = countryInfo.get(0).getVaccInfo();
             networkingService.listener = this;
        networkingService.fetchFlagImage(code);
        adapter.countryInfoList= vaccInfo;
        adapter.notifyDataSetChanged();
         }
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

        flagimage.setImageBitmap(image);
    }

    public void moreAdvisoryOnclick(View view) {
        Intent intent = new Intent(this,AdvisoryActivity.class);
        intent.putExtra("Countrycode",code);
        startActivity(intent);
    }
}