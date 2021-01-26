package com.example.moneytracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Record> data = new ArrayList<>();

    public ItemsAdapter(){
        createData();
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {
        Record record = data.get(position);
        holder.applyData(record);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private void createData() {
        data.add(new Record("Молоко", 35));
        data.add(new Record("Жизнь", 1));
        data.add(new Record("Курсы", 50));
        data.add(new Record("Хлеб", 12));
        data.add(new Record("Тот самый ужин,который я уплатил за всех, потому что платил картой", 600000));
        data.add(new Record("", 0));
        data.add(new Record("Тот самый ужин", 0));
        data.add(new Record("ракета Falcon Heavy", 1));
        data.add(new Record("Лего Тысячилетний сокол", 10000000));
        data.add(new Record("Монитор", 109));
        data.add(new Record("Макбук", 100));
        data.add(new Record("Шкаф", 100));
        data.add(new Record("Шоколадка", 100));

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public ItemViewHolder(View itemView) {
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
