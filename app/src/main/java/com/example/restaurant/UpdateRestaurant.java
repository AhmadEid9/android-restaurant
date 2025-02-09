package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurant.Models.Restaurant;
import com.example.restaurant.Models.RestaurantDBHelper;
import com.example.restaurant.Models.TypeService;
import com.example.restaurant.databinding.ActivityMainBinding;
import com.example.restaurant.databinding.ActivityUpdateRestaurantBinding;

public class UpdateRestaurant extends AppCompatActivity {
    private ActivityUpdateRestaurantBinding binding;
    private RestaurantDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateRestaurantBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        this.dbHelper = new RestaurantDBHelper(this);

        Intent intent = getIntent();

        int position = intent.getIntExtra("position", -1);

        if (position != -1) {
            Restaurant restaurant = MainActivity.resList.get(position);

            binding.name.setText(restaurant.getName());
            binding.address.setText(restaurant.getAddress());
            binding.phone.setText(restaurant.getPhone());
            binding.website.setText(restaurant.getWebsite());

            if (restaurant.getType() == TypeService.Delivery) {
                binding.typeGroup.check(R.id.type_delivery);
            } else if (restaurant.getType() == TypeService.TakeAway) {
                binding.typeGroup.check(R.id.type_take);
            } else {
                binding.typeGroup.check(R.id.type_table);
            }

            binding.updateButton.setOnClickListener(v -> updateRestaurant(position, dbHelper));
        } else {
            Toast.makeText(this, "Error in getting Data", Toast.LENGTH_LONG).show();
        }

    }

    private void updateRestaurant(int position, RestaurantDBHelper db){
        TypeService type;
        if (binding.typeGroup.getCheckedRadioButtonId() == R.id.type_table) {
            type = TypeService.Table;
        }else if (binding.typeGroup.getCheckedRadioButtonId() == R.id.type_take){
            type = TypeService.TakeAway;
        }else{
            type = TypeService.Delivery;
        }

        Restaurant restaurant = new Restaurant(binding.name.getText().toString(), binding.address.getText().toString(), binding.phone.getText().toString(), binding.website.getText().toString(), type, MainActivity.resList.get(position).getId()) ;
        db.updateRestaurant(restaurant);
        MainActivity.resList.set(position, restaurant);
        finish();
    }
}