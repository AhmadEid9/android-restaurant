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
import com.example.restaurant.Models.TypeService;
import com.example.restaurant.databinding.ActivityMainBinding;
import com.example.restaurant.databinding.ActivityUpdateRestaurantBinding;

public class UpdateRestaurant extends AppCompatActivity {
    private ActivityUpdateRestaurantBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateRestaurantBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Intent intent = getIntent();

        int position = intent.getIntExtra("position", -1);

        if (position != -1) {
            Restaurant resto = MainActivity.resList.get(position);

            binding.name.setText(resto.getName());
            binding.address.setText(resto.getAddress());
            binding.phone.setText(resto.getPhone());
            binding.website.setText(resto.getWebsite());

            if (resto.getType() == TypeService.Delivery) {
                binding.typeGroup.check(R.id.type_delivery);
            } else if (resto.getType() == TypeService.TakeAway) {
                binding.typeGroup.check(R.id.type_take);
            } else {
                binding.typeGroup.check(R.id.type_table);
            }

            binding.updateButton.setOnClickListener(v -> updateRestaurant(position));
        } else {
            Toast.makeText(this, "Error in getting Data", Toast.LENGTH_LONG).show();
        }

    }

    private void updateRestaurant(int position){
        TypeService type;
        if (binding.typeGroup.getCheckedRadioButtonId() == R.id.type_table) {
            type = TypeService.Table;
        }else if (binding.typeGroup.getCheckedRadioButtonId() == R.id.type_take){
            type = TypeService.TakeAway;
        }else{
            type = TypeService.Delivery;
        }
        Restaurant restaurant = new Restaurant(binding.name.getText().toString(), binding.address.getText().toString(), binding.phone.getText().toString(), binding.website.getText().toString(), type);
        MainActivity.resList.set(position, restaurant);
        finish();
    }
}