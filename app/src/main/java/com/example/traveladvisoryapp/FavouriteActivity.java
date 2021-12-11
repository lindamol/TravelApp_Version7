package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements DataBaseService.DatabaseListener,
        FavouriteAdapter.favcountryClickListner,NetworkingService.NetworkingListener{
    DataBaseService dbService;
    CountryDataBase db;
    Country countryObj;
    String countryCode;
    JsonService jsonService;
    NetworkingService networkingService;
    RecyclerView favorite_Recyclerview;
    ArrayList<Country> listofcountries= new ArrayList<>(0);
    FavouriteAdapter favadapter;
    ArrayList<Country> dbCountrylist = new ArrayList<>(0);
    ArrayList<AdvisoryInfo> advisoryInfo = new ArrayList<AdvisoryInfo>(0);
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
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
        networkingService.listener =this;
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
//        Intent intent = new Intent(this,FavouriteActivity.class);
//        intent.putExtra("SelectedCountry",favselectedCountry.getCountryName());
//        startActivity(intent);
        String code = favselectedCountry.getCountryCode().toUpperCase();
        networkingService.fetchAdvisoryInfo(favselectedCountry.getCountryCode().toUpperCase());
        countryCode = favselectedCountry.getCountryCode().toUpperCase();

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

    @Override
    public void APINetworkListner(String jsonString) {
        String code = countryCode;
        advisoryInfo = jsonService.parseAdvisoryInfo(jsonString,code);
        String message = advisoryInfo.get(0).getAdvisoryMessage();
        System.out.println("This is my message from final Actvity:" +message);
//        advisory_textview.setText(advisoryInfo.get(0).getAdvisoryMessage()+"\n"+"Updated on:"+"\n"+advisoryInfo.get(0).getDate());
//        risk_textview.setText("Risk Score: " +advisoryInfo.get(0).getRiskScore()+"");
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }
}