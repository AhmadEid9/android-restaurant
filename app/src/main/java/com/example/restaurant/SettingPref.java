package com.example.restaurant;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class SettingPref {
    private static String PREFERENCE_NAME;
    private static String startPage;


    private SettingPref(){}

    public static String getStartPage(Context context){
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString("startPage", context.getString(R.string.adding_page));
    }

    public static void setStartPage(Context context, String start){
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        edit.putString("startPage", start);
        edit.commit();
    }

    public static String getLanguage(Context context){
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString("language", "fr-FR");
    }

    public static void setLanguage(Context context, String lang){
        if (lang.equals("en-US")){
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        edit.putString("startPage", lang);
        edit.commit();
    }
}
