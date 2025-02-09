package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.Models.Restaurant;
import com.example.restaurant.Models.RestaurantDBHelper;
import com.example.restaurant.Models.TypeService;
import com.example.restaurant.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText nameInput, addressInput, phoneInput, websiteInput;
    private RadioGroup radioType;
    public static ArrayList<Restaurant> resList;

    public static RestaurantDBHelper restaurantDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        restaurantDBHelper = new RestaurantDBHelper(this);

        setContentView(binding.getRoot());

        resList = new ArrayList<Restaurant>();
        restaurantDBHelper.getRestaurantData(resList);

        if (SettingPref.getStartPage(this).equals(getString(R.string.showing_page))){
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        }

        nameInput = binding.name;
        addressInput = binding.address;
        phoneInput = binding.phone;
        websiteInput = binding.website;
        radioType = binding.typeGroup;

        if (savedInstanceState != null) {
            // Restore state
            nameInput.setText(savedInstanceState.getString("name"));
            addressInput.setText(savedInstanceState.getString("address"));
            phoneInput.setText(savedInstanceState.getString("phone"));
            websiteInput.setText(savedInstanceState.getString("website"));

            if (savedInstanceState.getString("type").equals(TypeService.Table.getDescription())){
                radioType.check(R.id.type_table);
            } else if (savedInstanceState.getString("type").equals(TypeService.TakeAway.getDescription())) {
                radioType.check(R.id.type_take);
            } else {
                radioType.check(R.id.type_delivery);
            }

        }
        binding.submitButton.setOnClickListener(this::addForm);
        binding.clearButton.setOnClickListener(view -> eraseData());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", nameInput.getText().toString());
        outState.putString("address", addressInput.getText().toString());
        outState.putString("phone", phoneInput.getText().toString());
        outState.putString("name", nameInput.getText().toString());
        String type;
        if (radioType.getCheckedRadioButtonId() == R.id.type_table) {
            type = TypeService.Table.getDescription();
        }else if (radioType.getCheckedRadioButtonId() == R.id.type_take){
            type = TypeService.TakeAway.getDescription();
        }else{
            type = TypeService.Delivery.getDescription();
        }
        outState.putString("type", type);

    }


    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.restaurant_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().equalsIgnoreCase(getString(R.string.actionbar_show))) {
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        }
        if (item.getTitle().toString().equalsIgnoreCase(getString(R.string.actionbar_settings))) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void addForm(View view){
        long id;

        String name = nameInput.getText().toString();
        String address = addressInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String website = websiteInput.getText().toString();
        TypeService type;
        if (radioType.getCheckedRadioButtonId() == R.id.type_table) {
            type = TypeService.Table;
        }else if (radioType.getCheckedRadioButtonId() == R.id.type_take){
            type = TypeService.TakeAway;
        }else{
            type = TypeService.Delivery;
        }
        Restaurant res = new Restaurant(name, address, phone, website, type);

        id = restaurantDBHelper.insertRestaurant(res);
        res.setId(id);
        resList.add(res);
        eraseData();
        Toast.makeText(this,"Restaurant Added to the list", Toast.LENGTH_LONG).show();
    }
    public void eraseData(){
        nameInput.setText("");
        nameInput.requestFocus();
        addressInput.setText("");
        phoneInput.setText("");
        websiteInput.setText("");
        radioType.check(R.id.type_take);
    }

}