package com.example.tinder.Room.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ItemLocation")
public class ItemLocation {
    @NonNull
    public String getStreet() {
        return street;
    }

    public void setStreet(@NonNull String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @PrimaryKey
    @NonNull
    private String street;
    private String city;
    private String state;
    private String zip;



}
