package com.example.order.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String itemName;
    private String date;
    private double price;
    private float rating;

    public Order() {
    }

    public Order(int id, String itemName, String date, double price, float rating) {
        this.id = id;
        this.itemName = itemName;
        this.date = date;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
