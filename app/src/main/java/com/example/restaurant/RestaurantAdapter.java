package com.example.restaurant;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.restaurant, parent, false);
        }

        Restaurant restaurant = restaurantList.get(position);

        if (restaurant != null) {
            TextView name = convertView.findViewById(R.id.name);
            TextView address = convertView.findViewById(R.id.address);
            TextView phone = convertView.findViewById(R.id.phone);
            TextView website = convertView.findViewById(R.id.website);

            ImageView tableIcon = convertView.findViewById(R.id.table_icon);
            ImageView takeawayIcon = convertView.findViewById(R.id.takeaway_icon);
            ImageView deliveryIcon = convertView.findViewById(R.id.delivery_icon);

            Button callButton = convertView.findViewById(R.id.callButton);
            Button deleteButton = convertView.findViewById(R.id.deleteButton);

            if (restaurant.getType().toString().equals("Table")){
                tableIcon.setImageResource(R.drawable.table_checked);
            } else if (restaurant.getType().toString().equals("Delivery")){
                deliveryIcon.setImageResource(R.drawable.delivery_checked);
            } else {
                takeawayIcon.setImageResource(R.drawable.takeaway_checked);
            }

            name.setText(restaurant.getName());
            address.setText(restaurant.getAddress());
            phone.setText(restaurant.getPhone());
            website.setText(restaurant.getWebsite());

            callButton.setOnClickListener(v -> makePhoneCall(restaurant.getPhone()));

            deleteButton.setOnClickListener(v -> deleteElement(position));
        }

        return convertView;
    }

    private void makePhoneCall (String phoneNumber){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            RestaurantAdapter.super.getContext().startActivity(callIntent);
        } else {
            ActivityCompat.requestPermissions( (Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

    private void deleteElement(int position){
        new AlertDialog.Builder(getContext())
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this phone number?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    restaurantList.remove(position);
                    notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }


}

