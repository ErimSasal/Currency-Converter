package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        db = new DatabaseHelper(this);
        e1= findViewById(R.id.emailText);
        e2=findViewById(R.id.passwordText);
        e3= findViewById(R.id.passwordText2);
        b1 = findViewById(R.id.registerButton);
        b2 = findViewById(R.id.loginButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register_activity.this,login_page.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if(s1.equals("") || s2.equals("") || s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty!",Toast.LENGTH_SHORT).show();
                }else{
                    if(s2.equals(s3)){
                        Boolean chkemail = db.chkemail(s1);
                        if(chkemail == true ){
                            Boolean insert = db.insert(s1,s2);
                            if(insert == true){
                                Intent i = new Intent(register_activity.this,login_page.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(),"Registered Successfully!",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_SHORT).show();

                        }
                    }
                    Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void registerClicked(View view){

    }
}
