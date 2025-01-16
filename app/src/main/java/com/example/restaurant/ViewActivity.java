package com.example.restaurant;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.Models.Restaurant;

public class ViewActivity extends AppCompatActivity {

    public RestaurantAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listViewAdapter = new RestaurantAdapter(this, MainActivity.resList);
        ListView restaurantListView = findViewById(R.id.lv_listview);

        restaurantListView.setAdapter(listViewAdapter);

        restaurantListView.setOnItemClickListener((context, view, position, args) -> launchRestaurantUpdate(position));
    }

    public void launchRestaurantUpdate (int position){
        Intent intent = new Intent(this, UpdateRestaurant.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}