package com.example.moneytracker;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    private static final String TAG = "App";


    private Api api;

    public Api getApi() {
        return api;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }
}
