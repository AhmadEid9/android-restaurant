package com.example.restaurant;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.Models.Restaurant;

public class ViewActivity extends AppCompatActivity {

    public RestaurantAdapter listeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listeViewAdapter = new RestaurantAdapter(this, MainActivity.resList);
        ((ListView)findViewById(R.id.lv_listview)).setAdapter(listeViewAdapter);
    }
}