package com.example.restaurant;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.databinding.ActivitySettingsBinding;


public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;
    RadioGroup pageGroup, langGroup;
    RadioButton engLanguageRB, frLanguageRB, addPageRB, showPageRB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        engLanguageRB = binding.languageEng;//findViewById(R.id.language_eng);
        frLanguageRB = binding.languageFr;//findViewById(R.id.language_fr);

        addPageRB = binding.pageAdd;//findViewById(R.id.page_add);
        showPageRB = binding.pageShow;//findViewById(R.id.page_show);

        pageGroup = binding.pageGroup;//findViewById(R.id.page_group);
        langGroup = binding.languageGroup;//findViewById(R.id.language_group);
        if (SettingPref.getStartPage(this).equals(getString(R.string.showing_page))){
            pageGroup.check(R.id.page_show);
        } else {
            pageGroup.check(R.id.page_add);
        }

        if (SettingPref.getLanguage(this).equals("en-US")){
            langGroup.check(R.id.language_eng);
        } else {
            langGroup.check(R.id.language_fr);
        }

        engLanguageRB.setOnClickListener(v -> setLanguagePref("en-US"));
        frLanguageRB.setOnClickListener(v -> setLanguagePref("fr-FR"));

        addPageRB.setOnClickListener(v -> setStartPage(getString(R.string.adding_page)));
        showPageRB.setOnClickListener(v -> setStartPage(getString(R.string.showing_page)));
    }
    private void setStartPage(String startPage){
        SettingPref.setStartPage(this, startPage);
    }

    private void setLanguagePref(String lang){
        SettingPref.setLanguage(this, lang);
    }
}