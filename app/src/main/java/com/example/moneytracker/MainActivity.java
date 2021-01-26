package com.example.moneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity","onCreate");
    }
    @Override
    protected void onStart () {
        super.onStart();
        Log.e("MainActivity", "onStart");
    }
    @Override
    protected void onStop () {
        super.onStop();
        Log.e("MainActivity", "onStop");
    }

}