package com.example.traveladvisoryapp;

public class CountryInfo {
        String countryCode;
        int Voltage;
        int frequency;
        String currencyName;
        String currencyCode;

    public CountryInfo(String countryCode, int voltage, int frequency, String currencyName, String currencyCode) {
        this.countryCode = countryCode;
        Voltage = voltage;
        this.frequency = frequency;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
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
}
