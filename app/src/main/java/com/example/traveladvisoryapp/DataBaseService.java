package com.example.traveladvisoryapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//This class is created to create the db and access it
public class DataBaseService {
    static CountryDataBase db;
    ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());
    public interface DatabaseListener {
        void databaseAllCountriesListener(List<Country> list);
    }
    public DatabaseListener listener;

    // Create only once and connect
    private static void BuildDBInstance (Context context) {
        db = Room.databaseBuilder(context,CountryDataBase.class,"country_db").build();
    }
    //Getter and setter for Database
    public static CountryDataBase getDBInstance(Context context){
        if (db == null){
            BuildDBInstance(context);//Create db
        }
        return db;
    }
 public void insertNewCountry(Country c){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                //Here inserting country object in background thread
                db.getCountryDAO().insertCountry(c);
            }
        });
 }

    public void getAllCountries(){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                //List<Donation> list =  db.getDonationDAO().getAll();
                List<Country> list =  db.getCountryDAO().getAllCountries();
                db_handler.post(new Runnable() {
                    @Override//go to the interface and wait
                    public void run() {
                        listener.databaseAllCountriesListener(list);
                    }
                });

            }
        });

    }

}
