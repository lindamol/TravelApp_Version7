package com.example.traveladvisoryapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//This class is created to create the db and access it
public class DataBaseService {
    static CountryDataBase db;
    static ExecutorService databaseExecuter = Executors.newFixedThreadPool(4);
    Handler db_handler = new Handler(Looper.getMainLooper());
//To create  DB and connect if already exists
    private static void BuildInstance(Context context){
    db = Room.databaseBuilder(context,CountryDataBase.class,"country_db").build();
}
//To access Instance
 public static CountryDataBase getdbInstance(Context context){
    if(db== null)
         { //Here we created the object for manager and access the db object with the manager object
             //create Db only if the db is null.Here we cannot access the db directly,only with the help of db manager
//             DataBaseManager m = new DataBaseManager(context) ;
//            db = m.db;
             //db = new DataBaseManager(context).db;
            BuildInstance(context);//Instead of constructor we can create a method and access
    }
   return db;
 }
 public static void insertNewCountry(Country c){
        databaseExecuter.execute(new Runnable() {
            @Override
            public void run() {
                //Here inserting country object in background thread
                db.getCountryDAO().insertCountry(c);
            }
        });
 }

}
