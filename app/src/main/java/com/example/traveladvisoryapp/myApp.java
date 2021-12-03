package com.example.traveladvisoryapp;

import android.app.Application;

public class myApp extends Application {

    private NetworkingService networkingService = new NetworkingService();


    public NetworkingService getNetworkingService() {
        return networkingService;
    }

    //    private JsonService jsonService = new JsonService();
//
//    public JsonService getJsonService() {
//        return jsonService;
//    }
}
