package com.example.restaurant.Models;

public class Restaurant {
    private String name;
    private String address;
    private TypeService type;
    public Restaurant(String name, String address, TypeService type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TypeService getType() {
        return type;
    }

    public void setType(TypeService type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type.getDescription() +
                '}';
    }
}
