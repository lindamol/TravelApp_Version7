package com.example.traveladvisoryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDAO {
    @Insert
  void insertCountry(Country counry);
    @Delete
    void deleteCountry(Country todeletecountry);
    @Update
    void updateCountry(Country toupdatecountry);
    @Query("SELECT * FROM Country")
    List<Country> getAllCountries();
  @Query("SELECT * FROM Country WHERE countryName = :name ")
   List<Country> getallfromcountryname(String name);
}
