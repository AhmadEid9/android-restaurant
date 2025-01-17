package com.example.restaurant.Models;

public enum TypeService {
    Table("Table"), Delivery("Delivery"), TakeAway("Take Away");
    private String description;

    TypeService(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
