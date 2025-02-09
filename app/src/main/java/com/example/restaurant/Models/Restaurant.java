package com.example.restaurant.Models;

import androidx.annotation.NonNull;

public class Restaurant {
    long id;
    private String name, address, phone, website;
    private TypeService type;
    public Restaurant(String name, String address, String phone, String website, TypeService type) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.type = type;
    }
    public Restaurant(String name, String address, String phone, String website, TypeService type, long id) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
