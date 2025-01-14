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
import com.example.restaurant.Models.TypeService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput, addressInput, phoneInput, websiteInput;
    private RadioGroup radioType;
    public static ArrayList<Restaurant> resList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.name);
        addressInput = findViewById(R.id.address);
        phoneInput = findViewById(R.id.phone);
        websiteInput = findViewById(R.id.website);
        radioType = findViewById(R.id.type_group);

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
        findViewById(R.id.submit_button).setOnClickListener(this::addForm);
        findViewById(R.id.clear_button).setOnClickListener(view -> eraseData());

        resList = new ArrayList<>();
        Restaurant mockData1 = new Restaurant("Mcdo", "Tripoli", "06123456", "test.com", TypeService.Table);
        Restaurant mockData2 = new Restaurant("KFC", "Tripoli", "06123456", "test.com", TypeService.Delivery);
        Restaurant mockData3 = new Restaurant("Doner", "Tripoli", "06123456", "test.com", TypeService.TakeAway);
        resList.add(mockData1);
        resList.add(mockData2);
        resList.add(mockData3);
//        RestaurantAdapter resAdapter = new RestaurantAdapter(this, resList);
//        ((ListView)findViewById(R.id.res_list)).setAdapter(resAdapter);
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
        if (item.getTitle().toString().toLowerCase().equals("show")) {
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void addForm(View view){
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