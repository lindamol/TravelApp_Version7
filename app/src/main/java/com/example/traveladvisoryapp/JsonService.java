package com.example.traveladvisoryapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {


    public ArrayList<CountryInfo> parseCountryInfo(String jsonCountryInfo)
    {ArrayList<CountryInfo> CountryInfoFromAPI = new ArrayList<>(0);
    ArrayList<String> vaccnames = new ArrayList<>(0);
    ArrayList<String> vaccmessages = new ArrayList<>(0);
        ArrayList<String> vaccInfoList = new ArrayList<>(0);
        try {
            //To get CountryCode
            JSONObject jsonObject = new JSONObject(jsonCountryInfo);
            JSONObject codeObject = jsonObject.getJSONObject("names");
            String countryCode = codeObject.getString("iso2");
            //To get ElectricityInfo
            JSONObject electricityObject = jsonObject.getJSONObject("electricity");
            String voltage = electricityObject.getString("voltage");
            String frequency = electricityObject.getString("frequency");
            //To get CurrencyInfo
            JSONObject currencyObject = jsonObject.getJSONObject("currency");
            String currencyName = currencyObject.getString("name");
            String currencyCode = currencyObject.getString("code");
            //To get VaccinationInfo
            JSONArray jsonArray = jsonObject.getJSONArray("vaccinations");
            String vaccMessage;
            String vaccName="";
            String vaccInfo;
            if(jsonArray.length()>0){
            for(int i = 0;i<jsonArray.length();i++){
                //   JSONObject weatherObject = weatherArray.getJSONObject(0);
//            String des = weatherObject.getString("description");
                JSONObject vaccobj = jsonArray.getJSONObject(i);
                vaccName = vaccobj.getString("name");//VaccinationInfo
                vaccMessage = vaccobj.getString("message");
                vaccInfo = vaccName +":"+"\n"+vaccMessage;
                vaccInfoList.add(vaccInfo);
                vaccnames.add(vaccName);
                vaccmessages.add(vaccMessage);}
               if(vaccName.length()==0){
                   vaccMessage = "No Updates";
                   vaccName = "No Updates";
                   vaccInfo ="No Information Available."+"\n" + " Please follow regular Guidelines";
                   vaccnames.add(vaccName);
                   vaccmessages.add(vaccMessage);
                   vaccInfoList.add(vaccInfo);
               }
               CountryInfoFromAPI.add(new CountryInfo(countryCode,voltage,frequency,currencyName,currencyCode,
                        vaccnames,vaccmessages,vaccInfoList));

            }else {
                 vaccMessage = "No Updates"; vaccName = "No Updates";
                 vaccInfo ="No Information Available."+"\n" + " Please follow regular Guidelines";
                 countryCode = countryCode;voltage = voltage;frequency =frequency;
                 currencyName = currencyName; currencyCode = currencyCode;
                 vaccnames.add(vaccName);
                 vaccmessages.add(vaccMessage);
                 vaccInfoList.add(vaccInfo);
                CountryInfoFromAPI.add(new CountryInfo(countryCode,voltage,frequency,currencyName,currencyCode,
                        vaccnames,vaccmessages,vaccInfoList));
            }
//            CountryInfoFromAPI.add(new CountryInfo(countryCode,voltage,frequency,currencyName,currencyCode,
//                    vaccnames,vaccmessages,vaccInfoList));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  CountryInfoFromAPI;
    }
public ArrayList<Country> parseCountriesAPIJson(String jsonCountries){
    ArrayList<Country> allCountriesFromAPI = new ArrayList<>(0);
    try {
        JSONArray jsonArray = new JSONArray(jsonCountries);//
        for (int i = 0 ; i< jsonArray.length(); i++){ // Iterate through the whole array
            JSONObject jsonObject = jsonArray.getJSONObject(i);//From inside array I need to get each object
            String countryName = jsonObject.getString("name");//From each object,extract the string using key
            allCountriesFromAPI.add(new Country(countryName)); //Add to the ArrayList
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

    return allCountriesFromAPI;
}
    public ArrayList<AdvisoryInfo> parseAdvisoryInfo(String jsonAdvisoryInfo, String countryCode){
        ArrayList<AdvisoryInfo> advisoryData = new ArrayList<>(0);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonAdvisoryInfo);
            JSONObject dataObject = jsonObject.getJSONObject("data");//Go inside data
            JSONObject codeObject = dataObject.getJSONObject(countryCode);//Go inside code
            JSONObject adviseObject = codeObject.getJSONObject("advisory");//Go inside advisory
            double score = adviseObject.getDouble("score");//Get score
            String message = adviseObject.getString("message"); // Get message
            String date = adviseObject.getString("updated");//Get date
            advisoryData.add(new AdvisoryInfo(score,message,date));//Add to Arraylist
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return  advisoryData;
    }
}

