package com.example.moneytracker;

public class Record {
    private final int price;
    private final String title;
    private String comment;


    public Record(String title, int price) {
        this.title = title;
        this.price = price;

    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }
}
