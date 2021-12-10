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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.traveladvisoryapp.NetworkingService;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity implements NetworkingService.NetworkingListener,DataBaseService.DatabaseListener {
    JsonService jsonService;
    NetworkingService networkingService;
    RecyclerView recyclerViewInfo;
    TextView countryName_texview,elect_textview,currency_textView;
    ImageView flagimage;
    Button moreadviseButton;
    String SelectedCountry ="";
    Boolean firstfetch = true;
    String code;
    ToggleButton favouriteTogglebutton;
    InfoAdapter adapter;
    ArrayList<CountryInfo> countryInfo = new ArrayList<CountryInfo>(0);
    ArrayList<String> vaccInfo = new ArrayList<>(0);
    DataBaseService dbService;
    CountryDataBase db;
    Country countryObj;
    ArrayList<Country> listofcountries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        countryName_texview = findViewById(R.id.countrynametexview);
        flagimage = findViewById(R.id.flagimage);
        currency_textView = findViewById(R.id.currency_textView);
        elect_textview = findViewById(R.id.electricitytextView);
        favouriteTogglebutton = findViewById(R.id.toggleFavourite);
        moreadviseButton = findViewById(R.id.moreAdvisory);
        recyclerViewInfo = findViewById(R.id.info_recyclerview);
        countryObj = new Country();
        recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        //DataBase
        db = DataBaseService.getDBInstance(this);
        dbService = ((myApp)getApplication()).getDataBaseService();
        dbService.listener = this;
        SelectedCountry = intent.getStringExtra("SelectedCountry");
        if (savedInstanceState != null) {
            //Restore value of members from saved state
            SelectedCountry = savedInstanceState.getString("currentCountry");
        }
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        networkingService.listener =this;
        networkingService.fetchCountryInfo(SelectedCountry);
        adapter = new InfoAdapter(this,vaccInfo);
        recyclerViewInfo.setAdapter(adapter);

        favouriteTogglebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfavouriteToggle();
            }
        });
        //jsonService.parseCountryInfo()
    }

    void checkfavouriteToggle(){
        if(favouriteTogglebutton.isChecked())
        {
            countryObj.setCountryName(SelectedCountry);
            countryObj.countryCode = code;
            Toast.makeText(this, "Toggle ,True favouriteTogglebutton"+favouriteTogglebutton.isChecked(), Toast.LENGTH_SHORT).show();
           dbService.insertNewCountry(countryObj);

        }else{
           // dbService.deleteCountryname(SelectedCountry);
            dbService.getAllCountries();
          //  System.out.println("These are my countryes in DBBBBB" + countryList);
            Toast.makeText(this, "Toggle ,Fase favouriteTogglebutton"+favouriteTogglebutton.isChecked(), Toast.LENGTH_SHORT).show();
        }

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
            setValues();
        }
    }
    @Override
    public void APINetworkingListerForImage(Bitmap image) {flagimage.setImageBitmap(image); }

    public void moreAdvisoryOnclick(View view) {
        Intent intent = new Intent(this,AdvisoryActivity.class);
        intent.putExtra("Countrycode",code);
        startActivity(intent);
    }
    public void setValues(){
        countryName_texview.setText(SelectedCountry);
        elect_textview.setText(countryInfo.get(0).getVoltage()+" V"+"\n"+countryInfo.get(0).getFrequency()+" Hz");
        currency_textView.setText(countryInfo.get(0).currencyName+" "+countryInfo.get(0).getCurrencyCode());
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("currentCountry",SelectedCountry);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void databaseAllCountriesListener(List<Country> list) {
        listofcountries = new ArrayList<>(list);
        System.out.println("These are my countryes in DBBBBB" + listofcountries.get(2).countryCode);
        System.out.println("These are my code in DBBBBB" + listofcountries.get(0).countryCode);
    }
}