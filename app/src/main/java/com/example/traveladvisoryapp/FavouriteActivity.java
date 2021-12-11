package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements DataBaseService.DatabaseListener,FavouriteAdapter.favcountryClickListner{
    DataBaseService dbService;
    CountryDataBase db;
    Country countryObj;
    RecyclerView favorite_Recyclerview;
    ArrayList<Country> listofcountries= new ArrayList<>(0);
    FavouriteAdapter favadapter;
    ArrayList<Country> dbCountrylist = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Intent intent = getIntent();
        countryObj = new Country();
        db = DataBaseService.getDBInstance(this);
        dbService = ((myApp)getApplication()).getDataBaseService();
        dbService.listener = this;
        dbService.getAllCountries();
        favorite_Recyclerview = findViewById(R.id.favorite_recyclerView);
        favorite_Recyclerview.setLayoutManager(new LinearLayoutManager(this));
        favadapter = new FavouriteAdapter(this,listofcountries);
        favorite_Recyclerview.setAdapter(favadapter);
         dbService.getAllCountries();
        //dbService.deleteCountryname("India");
        //dbService.deleteallRows();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(favorite_Recyclerview);



    }

    @Override
    public void databaseAllCountriesListener(List<Country> list) {
        listofcountries = new ArrayList<>(list);
        dbCountrylist = new ArrayList<>(list);
        favadapter.countryList = listofcountries;
        favadapter.notifyDataSetChanged();
        // System.out.println("These are my countryes in DBBBBB" + listofcountries.get(2).countryCode);
        System.out.println("These are my DBCOuntryList" + listofcountries);
    }

    @Override
    public void favcountryClicked(Country favselectedCountry) {
//        dbService.deleteCountryname(favselectedCountry.countryName);
//        favadapter.notifyDataSetChanged();
    }

    @Override
    public void codeforFavCountry(String favcountryCode) {
        System.out.println("From Fav Activityyyyy" + favcountryCode);
    }
    //Swipe to delete
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.DOWN, ItemTouchHelper.LEFT |
            ItemTouchHelper.RIGHT |
            ItemTouchHelper.DOWN |
            ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Toast.makeText(FavouriteActivity.this, "Item Moveing", Toast.LENGTH_SHORT).show();
            return false;
        }
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();
              dbService.deleteCountryname(dbCountrylist.get(position).getCountryName());
            favadapter.countryList.remove(position);
        }
    };

}