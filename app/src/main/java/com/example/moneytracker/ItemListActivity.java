package com.example.moneytracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private ItemListAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        createData();
        mRecycleView = findViewById(R.id.list);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemListAdapter();
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

    private class ItemListAdapter extends RecyclerView.Adapter<RecordViewHolder> {


        @Override
        public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecordViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_record, parent, false));
        }

        @Override
        public void onBindViewHolder(RecordViewHolder holder, int position) {
            Record record = mData.get(position);
            holder.applyData(record);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }

        public void applyData(Record record) {
            title.setText(String.valueOf(record.getTitle()));
            price.setText(String.valueOf(record.getPrice()));
        }
    }
}
