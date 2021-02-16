package com.example.moneytracker.api;

import com.example.moneytracker.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/items")
    Call<List<Item>> getItems(@Query("type") String type);


    @GET("/auth")
    Call<AuthResult> auth(@Query("social_user_id") String userId);
}
