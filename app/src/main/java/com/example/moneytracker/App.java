package com.example.moneytracker;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.example.moneytracker.api.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static final String TAG = "App";
    private static final String PREFS_NAME = "shared_prefs1";
    private static final String KEY_TOKEN = "auth_token";
    private Api api;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE
        );

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy.MM.dd HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bkmoneytracker.getsandbox.com/items/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }

    public void saveAuthToken(String token) {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(KEY_TOKEN, token)
                .apply();
    }

    public String getAuthToken() {
        return getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getString(KEY_TOKEN, null);
    }

    public boolean isAuthorized() {
        return !TextUtils.isEmpty(getAuthToken());
    }
}
