package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;

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
  TextView countryName_texview;
  TextView message_textview;
  ImageView flagimage;
  Button moreadviseButton;
  String SelectedCountry ="";
    Boolean firstfetch = true;
    String code;
    ArrayList<CountryInfo> countryInfo = new ArrayList<CountryInfo>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        countryName_texview = findViewById(R.id.countrynametexview);
        flagimage = findViewById(R.id.flagimage);
        message_textview = findViewById(R.id.textmessage);
        moreadviseButton = findViewById(R.id.moreAdvisory);
        Intent intent = getIntent();
       SelectedCountry = intent.getStringExtra("SelectedCountry");
       jsonService = ( (myApp)getApplication()).getJsonService();
       networkingService = ( (myApp)getApplication()).getNetworkingService();
       networkingService.listener =this;
       networkingService.fetchCountryInfo(SelectedCountry);

       //jsonService.parseCountryInfo()

    }

    @Override
    public void APINetworkListner(String jsonString) {
       // String countryInformation = jsonString;

         {
        countryInfo =  jsonService.parseCountryInfo(jsonString);
        String vaccinename =countryInfo.get(0).vaccName.get(0);
        code = (countryInfo.get(0).countryCode).toLowerCase();
        networkingService.listener = this;
        networkingService.fetchFlagImage(code);
         }
       if(countryInfo.get(0).vaccName.get(0)==""){
            System.out.println("ERORRRRRRRRRRRRRRR");
            message_textview.setText("No val");
        }
        else {
        message_textview.setText(countryInfo.get(0).vaccName.get(0)+":"+"\n"+countryInfo.get(0).vaccMessage.get(0));}
        System.out.println("Printing JSON In InformationActivity"+"country code is" +code);
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