package com.example.currencyconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class profile_page extends Fragment {


    public profile_page() {
        // Required empty public constructor
    }


    TextView pleaseText, welcomeText;
    ImageButton logOutB;
    String userEmail = "NoUser";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);


        Toolbar toolbarProfile = view.findViewById(R.id.fToolbar);
        toolbarProfile.setTitle("ConvertIT");
        toolbarProfile.setSubtitle("Profile Page");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarProfile);
        toolbarProfile.setTitleTextColor(Color.WHITE);
        toolbarProfile.setSubtitleTextColor(Color.WHITE);
        DatabaseHelper dH = new DatabaseHelper(getActivity().getApplicationContext());


        welcomeText = view.findViewById(R.id.welcomeText);
        try {
            userEmail = dH.getAllEmailData().get(0).getEmail();
            welcomeText.setText(getString(R.string.welcomeText1) + userEmail + getString(R.string.welcomeText2));
        }catch (IndexOutOfBoundsException e){
            welcomeText.setText(getString(R.string.pleaseLogin));
        }



        logOutB = view.findViewById(R.id.logOutButton);


        logOutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //user logging out
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE).edit();
                    editor.putString("password", "");
                    editor.putString("email", "");
                    editor.putBoolean("isLoggedIn", false);
                    editor.apply();

                    Intent intent = new Intent(getActivity().getBaseContext(), login_page.class);
                    intent.putExtra("finish", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    Toast.makeText(getActivity().getBaseContext(), "Please Come Back!", Toast.LENGTH_SHORT).show();

                    getActivity().finish();


            }
        });


        return view;
    }


}
