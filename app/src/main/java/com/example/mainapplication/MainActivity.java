package com.example.mainapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainapplication.Models.Restaurant;
import com.example.mainapplication.Models.TypeService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText addressInput;
    private RadioGroup radioType;
    private ArrayList<Restaurant> resList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.name);
        addressInput = findViewById(R.id.address);
        radioType = findViewById(R.id.type_group);

        findViewById(R.id.submit_button).setOnClickListener(this::addForm);
        findViewById(R.id.clear_button).setOnClickListener(view -> eraseData());

        resList = new ArrayList<>();
    }

    public void addForm(View view){
        String name = nameInput.getText().toString();
        String address = addressInput.getText().toString();
        TypeService type;
        if (radioType.getCheckedRadioButtonId() == R.id.type_table) {
            type = TypeService.Table;
        }else if (radioType.getCheckedRadioButtonId() == R.id.type_take){
            type = TypeService.TakeAway;
        }else{
            type = TypeService.Delivery;
        }
        Restaurant res = new Restaurant(name, address, type);
        resList.add(res);
        eraseData();
        Toast.makeText(this, "The list has " + resList.size() + " restaurants", Toast.LENGTH_LONG).show();
    }
    public void eraseData(){
        nameInput.setText("");
        nameInput.requestFocus();
        addressInput.setText("");
        radioType.check(R.id.type_take);
    }

}