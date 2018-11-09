package com.capriccioso.groceryapp.model;

import io.realm.RealmObject;

/**
 * Created by dobit on 11/8/2018.
 */


//To access RealmDB, a class must extend to RealmObject
public class Item extends RealmObject{
    private String name;
    private int quantity;
    private String color;
    private int size;
    private String brand;
    private float price;


    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
