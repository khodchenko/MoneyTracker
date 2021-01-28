package com.example.moneytracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class  ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> data = new ArrayList<>();

//    public ItemsAdapter(){
//        createData();
//    }

    public void setData(List<Item>data){
        this.data= data;
    }

    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
       return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ItemViewHolder holder, int position) {
        Item item = data.get(position);
        holder.applyData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


//    private void createData() {
//        data.add(new Item("Молоко", 35));
//        data.add(new Item("Жизнь", 1));
//        data.add(new Item("Курсы", 50));
//        data.add(new Item("Хлеб", 12));
//        data.add(new Item("Тот самый ужин,который я уплатил за всех, потому что платил картой", 600000));
//        data.add(new Item("", 0));
//        data.add(new Item("Тот самый ужин", 0));
//        data.add(new Item("ракета Falcon Heavy", 1));
//        data.add(new Item("Лего Тысячилетний сокол", 10000000));
//        data.add(new Item("Монитор", 109));
//        data.add(new Item("Макбук", 100));
//        data.add(new Item("Шкаф", 100));
//        data.add(new Item("Шоколадка", 100));
//
//    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }

        public void applyData(Item item) {
            title.setText(String.valueOf(item.getTitle()));
            price.setText(String.valueOf(item.getPrice()));
        }
    }


}
