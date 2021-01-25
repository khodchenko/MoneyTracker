package com.example.moneytracker;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<Record> mData = new ArrayList<>();
    private ItemsAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        createData();
        mRecycleView = findViewById(R.id.list);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemsAdapter(this);
        mRecycleView.setAdapter(mAdapter);

    }

    private void createData() {
        mData.add(new Record("Молоко", 35));
        mData.add(new Record("Жизнь", 1));
        mData.add(new Record("Курсы", 50));
        mData.add(new Record("Хлеб", 12));
        mData.add(new Record("Тот самый ужин,который я уплатил за всех, потому что платил картой", 600000));
        mData.add(new Record("", 0));
        mData.add(new Record("Тот самый ужин", 0));
        mData.add(new Record("ракета Falcon Heavy", 1));
        mData.add(new Record("Лего Тысячилетний сокол", 10000000));
        mData.add(new Record("Монитор", 109));
        mData.add(new Record("Макбук", 100));
        mData.add(new Record("Шкаф", 100));
        mData.add(new Record("Шоколадка", 100));

    }


}
