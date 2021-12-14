package com.example.traveladvisoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryAdapter.countryClickListner,NetworkingService.NetworkingListener{
    RecyclerView countryrecyclerView;
    CountryAdapter cadapter;
    NetworkingService networkingService;// = new NetworkingService();
    TextView advisorytext ;
    Button countrybutton;
    Button favoriteButtton;
    JsonService jsonService;// = new JsonService();
    ArrayList<Country> countries = new ArrayList<Country>();
    ArrayList<Country> updatecountries = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //advisorytext = findViewById(R.id.textadvisory);
        //countrybutton = findViewById(R.id.countryviewbutton);
//        advisorytext.setVisibility(View.INVISIBLE);
//        countrybutton.setVisibility(View.INVISIBLE);
        jsonService = ( (myApp)getApplication()).getJsonService();
        networkingService = ( (myApp)getApplication()).getNetworkingService();
       // countries.add(new Country("India"));//check
        favoriteButtton = findViewById(R.id.favoriteButton);
        countryrecyclerView = findViewById(R.id.country_recyclerview);
        countryrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cadapter = new CountryAdapter(this,countries);
        countryrecyclerView.setAdapter(cadapter);
        networkingService.listener = this;
        networkingService.fetchCountryData();
        favoriteButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFavoriteActivity();
            }
        });
    }
    void openFavoriteActivity(){
        Intent intent = new Intent(this,FavouriteActivity.class);
        startActivity(intent);
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchViewMenuItem = menu.findItem(R.id.search);
       androidx.appcompat.widget.SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
       searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
     //  String searchText = searchView.getQuery().toString();
//        if(!searchText.isEmpty())//check countryname
//            {searchView.setIconified(false);
//             searchView.setQuery(searchText,false);}
        searchView.setQueryHint("Search country name");
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Log.d("query", query);//
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("query change", newText);
                if(newText.length()>=1){
                    // seach for country
                   cadapter.getFilter().filter(newText);
                   cadapter.countryList = countries;
                   cadapter.notifyDataSetChanged();
                }
                else{
                     cadapter.countryList = new ArrayList<>(0);
                    networkingService.fetchCountryData();
                   // cadapter.countryList = countries;
                    cadapter.notifyDataSetChanged();
                    //Toast.makeText(MainActivity.this, "empty inside else", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

       return true;
    }

    @Override
    public void countryClicked(Country selectedCountry) {
        Intent intent = new Intent(this,InformationActivity.class);
        intent.putExtra("SelectedCountry",selectedCountry.getCountryName());
        startActivity(intent);
    }

    @Override
    public void APINetworkListner(String jsonString) {
        System.out.println("Printing JSON In MAIN THREADDDDDDDDDDDDDDDDDD££££££££££££££££££");
        //U+1F1E8 U+1F1E6
    // Log.d("tag", jsonString);
       countries =  jsonService.parseCountriesAPIJson(jsonString);
       updatecountries =countries;
         cadapter.countryList = countries;
         cadapter.notifyDataSetChanged();

           }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }
}