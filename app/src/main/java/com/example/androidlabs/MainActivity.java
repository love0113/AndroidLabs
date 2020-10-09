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
         EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profileactivity);

        Button nextButton = (Button)findViewById(R.id.btnGotoChat);
        emailField = (EditText)findViewById(R.id.typeEmalprofle);
        prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String saveEmail = prefs.getString("emailText","");
        emailField.setText(saveEmail);
        nextButton.setOnClickListener( b -> {

            //Give directions to go from this page, to SecondActivity
            Intent nextPage = new Intent(MainActivity.this, ProfileActivity.class);

            //   EditText et =(EditText)findViewById(R.id.)
            nextPage.putExtra("emailType", emailField.getText().toString());
            //Now make the transition:
            startActivityForResult(nextPage, 345);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = prefs.edit();

        String typeEmail  = emailField.getText().toString();
        editor.putString("ReserveName", typeEmail);
        editor.commit();
    }
}