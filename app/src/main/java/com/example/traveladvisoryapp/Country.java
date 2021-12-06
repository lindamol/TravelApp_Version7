package com.example.traveladvisoryapp;

import android.graphics.Bitmap;

public class Country {
    private String countryName;

    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

      public String getCountryName() {
        return countryName;
    }

     public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                '}';
    }

//    private String countryCode;
//    private String currency;
//    private String Vaccination;
}
