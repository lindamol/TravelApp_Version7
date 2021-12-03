package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryAdapter.countryClickListner,NetworkingService.NetworkingListener{
    RecyclerView countryrecyclerView;
    CountryAdapter cadapter;
    NetworkingService networkingService = new NetworkingService();
    JsonService jsonService = new JsonService();
    ArrayList<Country> countries = new ArrayList<Country>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // countries.add(new Country("India"));//check
        countryrecyclerView = findViewById(R.id.country_recyclerview);
        countryrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cadapter = new CountryAdapter(this,countries);
        countryrecyclerView.setAdapter(cadapter);
       // networkingService = ( (myApp)getApplication()).getNetworkingService();
        //jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService.listener = this;
        networkingService.fetchCountryData();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchViewMenuItem = menu.findItem(R.id.search);
       androidx.appcompat.widget.SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
       String searchText = searchView.getQuery().toString();
        if(!searchText.isEmpty())//check countryname
            {searchView.setIconified(false);
             searchView.setQuery(searchText,false);}
        searchView.setQueryHint("Search country name");
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);//
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("query change", newText);
                if(newText.length()>=3){
                    // seach for country
                   networkingService.fetchCountryData();
                    System.out.println("Going to fetchdata");
                   // cadapter.countryList = countries;
                    cadapter.notifyDataSetChanged();
                }
                else{
                    cadapter.countryList = new ArrayList<>(0);
                    cadapter.notifyDataSetChanged();
                }
                return false;
            }
        });

       return true;
    }*/

    @Override
    public void countryClicked(Country selectedCountry) {
//        Intent intent = new Intent(this,WeatherActivity.class);
//        intent.putExtra("SelectedCity",selectedCity.getCityName());
//        startActivity(intent);
    }

    @Override
    public void APINetworkListner(String jsonString) {
        //String jsonData = jsonString;
        System.out.println("Printing JSON In MAIN THREADDDDDDDDDDDDDDDDDD££££££££££££££££££");
    // Log.d("tag", jsonString);
       countries =  jsonService.parseCountriesAPIJson(jsonString);

         cadapter.countryList = countries;
         cadapter.notifyDataSetChanged();

        //jsonService.parseCountriesAPIJson(jsonString);
    }
}