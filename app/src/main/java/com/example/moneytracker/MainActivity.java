package com.example.moneytracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity     {
    private ViewPager viewPager;
    private TabLayout tabLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("T", "1");
        setContentView(R.layout.activity_main);

        Log.e("T", "2");
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        MainPagesAdapter adapter = new MainPagesAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }


}