package com.example.restaurant.Models;

import androidx.annotation.NonNull;

public class Restaurant {
    private String name;
    private String address;
    private String phone;
    private String website;
    private TypeService type;
    public Restaurant(String name, String address, String phone, String website, TypeService type) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.type = type;
    }
    @NonNull
    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", type=" + type +
                '}';
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getWebsite() {
        return website;
    }
    public TypeService getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setType(TypeService type) {
        this.type = type;
    }
}
