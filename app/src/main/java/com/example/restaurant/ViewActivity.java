package com.example.restaurant;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    public RestaurantAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listViewAdapter = new RestaurantAdapter(this, MainActivity.resList);
        ListView restaurantListView = findViewById(R.id.lv_listview);

        restaurantListView.setAdapter(listViewAdapter);

        restaurantListView.setOnItemClickListener((context, view, position, args) -> {
            Toast.makeText(this, "position is " + position, Toast.LENGTH_LONG ).show();
            launchRestaurantUpdate(position);
        });

    }

    public void launchRestaurantUpdate (int position){
        Intent intent = new Intent(this, UpdateRestaurant.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}