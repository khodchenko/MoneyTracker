package com.example.moneytracker;

public class Item {
    private final int price;
    private final String title;



    public Item(String title, int price) {
        this.title = title;
        this.price = price;

    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }


}
