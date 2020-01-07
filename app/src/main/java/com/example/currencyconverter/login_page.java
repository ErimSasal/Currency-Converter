package com.example.currencyconverter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class login_page extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    DatabaseHelper db;
    private int INTERNET_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        db = new DatabaseHelper(this);
        e1 = findViewById(R.id.emailText);
        e2 = findViewById(R.id.passwordText);
        b2 = findViewById(R.id.regButton);
        b1 = findViewById(R.id.logButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email,password);

                SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putBoolean("isLoggedIn", Chkemailpass);
                editor.apply();
                SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
                boolean isLoggedIn= prefs.getBoolean("isLoggedIn", false);

                if (ContextCompat.checkSelfPermission(login_page.this,
                        Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(login_page.this, R.string.alreadyGranted,
                            Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }

                if(isLoggedIn){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_main);
                            finish();

                }else{
                    Toast.makeText(getApplicationContext(),"Wrong email or password! Try again!",Toast.LENGTH_SHORT).show();
                }





            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_page.this,register_activity.class);
                startActivity(i);
            }
        });
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_NETWORK_STATE)) {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.permissionNeeded)
                    .setMessage(R.string.neededBecause)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(login_page.this,
                                    new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, INTERNET_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, INTERNET_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == INTERNET_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.denied, Toast.LENGTH_SHORT).show();
            }
        }
    }
}