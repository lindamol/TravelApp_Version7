package com.example.traveladvisoryapp;

import java.util.ArrayList;

public class CountryInfo {
        String countryCode;
    String Voltage;
    String frequency;
        String currencyName;
        String currencyCode;

       ArrayList<String> vaccName;
        ArrayList<String> vaccMessage;
        ArrayList<String> vaccInfo;

    public CountryInfo(String countryCode, String voltage, String frequency, String currencyName, String currencyCode,
                       ArrayList<String> vaccName, ArrayList<String> vaccMessage,ArrayList<String> vaccInfo) {
        this.countryCode = countryCode;
        Voltage = voltage;
        this.frequency = frequency;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.vaccName = vaccName;
        this.vaccMessage = vaccMessage;
        this.vaccInfo = vaccInfo;
    }

    public ArrayList<String> getVaccInfo() {
        return vaccInfo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getVoltage() {
        return Voltage;
    }

    public String getFrequency() {
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
