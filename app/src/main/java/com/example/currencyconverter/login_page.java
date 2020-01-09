package com.example.currencyconverter;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class login_page extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    DatabaseHelper db;
    private int PERMISSION_CODE = 1;
    private static final int STORAGE_PERMISSION_CODE = 101;

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

                /*if (ContextCompat.checkSelfPermission(login_page.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(login_page.this, R.string.alreadyGranted,
                            Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }*/
                checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_PERMISSION_CODE);

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

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(login_page.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(login_page.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(login_page.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(login_page.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(login_page.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
/*
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle(R.string.permissionNeeded)
                    .setMessage(R.string.neededBecause)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(login_page.this,
                                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
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
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.denied, Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
