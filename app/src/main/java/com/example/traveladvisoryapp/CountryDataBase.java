package com.example.traveladvisoryapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {Country.class})//Specify which class is DB
public abstract class CountryDataBase extends RoomDatabase {
    //Created an abstarct method to get access to the DAO
    abstract  public CountryDAO getCountryDAO();//
}
