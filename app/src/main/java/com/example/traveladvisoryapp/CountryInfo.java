package com.example.traveladvisoryapp;

import java.util.ArrayList;

public class CountryInfo {
        String countryCode;
        int Voltage;
        int frequency;
        String currencyName;
        String currencyCode;

       ArrayList<String> vaccName;
        ArrayList<String> vaccMessage;

    public CountryInfo(String countryCode, int voltage, int frequency, String currencyName, String currencyCode, ArrayList<String> vaccName, ArrayList<String> vaccMessage) {
        this.countryCode = countryCode;
        Voltage = voltage;
        this.frequency = frequency;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.vaccName = vaccName;
        this.vaccMessage = vaccMessage;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getVoltage() {
        return Voltage;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public ArrayList<String> getVaccName() {
        return vaccName;
    }

    public ArrayList<String> getVaccMessage() {
        return vaccMessage;
    }
}
