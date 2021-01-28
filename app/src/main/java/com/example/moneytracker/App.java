package com.example.moneytracker;

import android.app.Application;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static final String TAG = "App";

    private Api api;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://android.loftschool.com/basic/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }

}
