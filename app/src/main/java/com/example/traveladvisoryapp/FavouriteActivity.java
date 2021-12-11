package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements DataBaseService.DatabaseListener{
    DataBaseService dbService;
    CountryDataBase db;
    Country countryObj;
    RecyclerView favorite_Recyclerview;
    ArrayList<Country> listofcountries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Intent intent = getIntent();
        countryObj = new Country();
        db = DataBaseService.getDBInstance(this);
        dbService = ((myApp)getApplication()).getDataBaseService();
        dbService.listener = this;
        favorite_Recyclerview = findViewById(R.id.favorite_recyclerView);
       // dbService.getAllCountries();
        //dbService.deleteCountryname("India");
        // dbService.deleteallRows();
        dbService.getAllCountries();

    }

    @Override
    public void databaseAllCountriesListener(List<Country> list) {
        listofcountries = new ArrayList<>(list);
        // System.out.println("These are my countryes in DBBBBB" + listofcountries.get(2).countryCode);
        System.out.println("These are my DBCOuntryList" + listofcountries);
    }
}