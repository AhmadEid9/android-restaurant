package com.example.restaurant.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class RestaurantDBHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "restaurant.db";
    public static int SCHEMA_VERSION = 1;

    public RestaurantDBHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "onCreate");
        db.execSQL("create table restaurants (_id INTEGER primary key autoincrement , name text, address text, phone text, website text, type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB", "onUpdate: from version " + oldVersion + " to version "+ newVersion);
//        db.execSQL("alter table test(_id INTEGER primary key autoincrement , nom text)");
    }

    public long insertRestaurant(Restaurant restaurant){
        ContentValues values = new ContentValues();
        values.put("name", restaurant.getName());
        values.put("address", restaurant.getAddress());
        values.put("phone", restaurant.getPhone());
        values.put("website", restaurant.getWebsite());
        values.put("type", restaurant.getType().getDescription());
        return getWritableDatabase().insert("restaurants", null, values);
    }

    public void getRestaurantData(ArrayList<Restaurant> restaurantList){
        Cursor results = getReadableDatabase().rawQuery("SELECT * FROM restaurants", null);
        if (results.moveToFirst()){
            do{
            Log.d("DBase", "getRestaurantData: " + results.toString());

            String type = results.getString(results.getColumnIndex("type"));
                TypeService ts;

            if (TypeService.Table.toString().equals(type))
                 ts = TypeService.Table;
            else if (TypeService.Delivery.toString().equals(type))
                ts = TypeService.Delivery;
            else
                ts = TypeService.TakeAway;

            restaurantList.add(new Restaurant(results.getString(results.getColumnIndex("name")),
                    results.getString(results.getColumnIndex("address")),
                    results.getString(results.getColumnIndex("phone")),
                    results.getString(results.getColumnIndex("website")),
                    ts,
                    results.getInt(results.getColumnIndex("_id"))
                    ));
            } while (results.moveToNext());
        }
        results.close();
    }
    public int updateRestaurant(Restaurant restaurant) {
        ContentValues values = new ContentValues();
        values.put("name", restaurant.getName());
        values.put("address", restaurant.getAddress());
        values.put("phone", restaurant.getPhone());
        values.put("website", restaurant.getWebsite());
        values.put("type", restaurant.getType().getDescription());

        return getWritableDatabase().update("restaurants", values, "_id = ?", new String[]{String.valueOf(restaurant.getId())});
    }
    public int deleteRestaurant(long id) {
        return getWritableDatabase().delete("restaurants", "_id = ?", new String[]{String.valueOf(id)});
    }

}
