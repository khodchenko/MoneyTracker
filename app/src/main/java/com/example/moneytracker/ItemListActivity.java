package com.example.moneytracker;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ItemListActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ItemsAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        adapter = new ItemsAdapter();

        recycler = findViewById(R.id.list);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        recycler.setAdapter(adapter);

    }



}
