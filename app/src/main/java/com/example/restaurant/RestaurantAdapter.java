package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.restaurant.Models.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    private ArrayList<Restaurant> restaurantList;
    public RestaurantAdapter(Context context, ArrayList<Restaurant> resList){
        super(context, android.R.layout.simple_list_item_1, resList);
        this.restaurantList = resList;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent);
        }

        Restaurant restaurant = restaurantList.get(position);

        if (restaurant != null) {
            TextView name = convertView.findViewById(R.id.name);
            TextView address = convertView.findViewById(R.id.address);
            TextView phone = convertView.findViewById(R.id.phone);
            TextView website = convertView.findViewById(R.id.website);


            name.setText(restaurant.getName());
            address.setText(restaurant.getAddress());
            phone.setText(restaurant.getPhone());
            website.setText(restaurant.getWebsite());
        }

        return convertView;
    }
}
