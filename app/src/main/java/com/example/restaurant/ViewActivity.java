package com.example.restaurant;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    protected void onResume() {
        super.onResume();
        listViewAdapter.notifyDataSetChanged();
    }

    public void launchRestaurantUpdate (int position){
        Intent intent = new Intent(this, UpdateRestaurant.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.restaurant_view_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().equalsIgnoreCase(getString(R.string.add_label))) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}