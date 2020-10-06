package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
       private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        prefs = getSharedPreferences("account", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("emailText","");
        String password = prefs.getString("passwordText", "");
        EditText emailEditText=findViewById(R.id.emailField);
        emailEditText.setText(emailAddress);
        EditText passwordEditText=findViewById(R.id.passwordField);

        Button login = findViewById(R.id.RegisterButton);
        Intent goToProfile = new Intent (MainActivity.this, ProfileActivity.class);
        login.setOnClickListener(cick->{
            goToProfile.putExtra("email",emailEditText.getText().toString());
            startActivity(goToProfile);
        });







    }
}