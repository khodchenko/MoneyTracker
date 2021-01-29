package com.example.moneytracker;

import com.google.gson.annotations.SerializedName;

public class Item {

    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_INCOMES = "incomes";
    public static final String TYPE_EXPENSES = "expenses";
    //public static final String TYPE_BALANCE = "balance";

    public int id;

    public String name;
    public int price;
    public String type;

    public Item(String title, int price, String type) {
        this.name = title;
        this.price = price;
        this.type = type;
    }
}
