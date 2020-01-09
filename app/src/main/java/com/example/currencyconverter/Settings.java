package com.example.currencyconverter;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    Intent intent;
    public TextView chosenLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton turkishButton = findViewById(R.id.turkishButton);
        ImageButton englishButton = findViewById(R.id.englishButton);

        intent = new Intent(this,MainActivity.class);

        turkishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("tr");
            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });

    }


    public void setLocale(final String language) {
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        chosenLang = findViewById(R.id.languageTextView);
        if(chosenLang.getText().equals("Language")) {
            chosenLang.setText("Uygulama dilini seçiniz:");
        } else {
            chosenLang.setText("Choose app language:");
        }
        Snackbar snackbar = Snackbar.make(findViewById(R.id.settings_constraint_layout), getString(R.string.languageChanged), Snackbar.LENGTH_LONG);
        snackbar.setAction("CANCEL", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(language.equals("tr")) {
                    setLocale("en");

                } else {
                    setLocale("tr");
                }
                Toast.makeText(getApplicationContext(), getString(R.string.cancel), Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.show();

        intent.putExtra(MainActivity.LANGUAGE, language);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish(); // close this activity and return to preview activity (if there is any)

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
