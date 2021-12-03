package com.example.traveladvisoryapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {
//    String  weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=";
//    String weatherURL2 = "&appid=071c3ffca10be01d334505630d2c1a9c";
//
//    String iconURL1 = "https://openweathermap.org/img/wn/";
//    String iconURL2 = "@2x.png";
    //https://travelbriefing.org/countries.json

   String url = "https://travelbriefing.org/countries.json";
    //https://travelbriefing.org/Canada?format=json
//      String url1 = "https://travelbriefing.org/";
//      String url2 = "Canada";
//           //String url3 = "?format=json";
//      String url3 = "?format=json";
//      String url = url1 + url2+url3;
    //https://restcountries.com/v3.1/name/can
  // String url = "https://restcountries.com/v3.1/name/";


    public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
    static Handler networkHander = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void APINetworkListner(String jsonString);
       // void APINetworkingListerForImage(Bitmap image);
    }

    NetworkingListener listener;


    public void fetchCountryData(){
        String completeURL = url;
        System.out.println("My url" + url);
        connect(completeURL);
    }

//    public void fetchWeatherData(String cityName){
//        String completeURL = weatherURL+cityName+weatherURL2;
//        connect(completeURL);
//    }

//    public void getImageData(String icon){
//        String completeURL = iconURL1 + icon + iconURL2;
//        networkingExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL urlObj = new URL(completeURL);
//                    InputStream in = ((InputStream)urlObj.getContent());
//                    Bitmap imageData = BitmapFactory.decodeStream(in);
//                    networkHander.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            listener.APINetworkingListerForImage(imageData);
//                        }
//                    });
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    // tor
    private void connect(String url){
        networkingExecutor.execute(new Runnable() {
            String jsonString = "";
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                try {
                    URL urlObject = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    int statues = httpURLConnection.getResponseCode();

                    if ((statues >= 200) && (statues <= 299)) {
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                        int read = 0;
                        while ((read = inputStreamReader.read()) != -1) {// json integers ASCII
                            char c = (char) read;
                            jsonString += c;
                        }// jsonString = ["Torbert, LA, United States","Torch, OH, United States","Toreboda, VG, Sweden","Torino, PI, Italy","Tornado, WV, United States","Tornillo, TX, United States","Tornio, LP, Finland","Toronto, KS, United States","Toronto, OH, United States","Toronto, ON, Canada","Toronto, SD, United States","Torquay, QL, Australia","Torrance, CA, United States","Torrance, PA, United States","torre del greco, CM, Italy","Torre Pellice, PI, Italy","Torrelles de Llobregat, CT, Spain","TORRENS CREEK, QL, Australia","Torreon, CA, Mexico","Torreon, NM, United States"]
                        // dataTask in ios
                        final String finalJson = jsonString;
//                        System.out.println("From Networkinnnnnnnnnnnn:"+finalJson);
//                      Log.d("tag", finalJson);
                        networkHander.post(new Runnable() {
                            @Override
                            public void run() {
                                //send data to main thread
                                listener.APINetworkListner(finalJson);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }

}
