package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        Button btn = findViewById(R.id.button4);
        btn.setOnClickListener(v ->
                Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG).show());

        Switch aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener( (buttonView, isChecked)->{
            Snackbar.make(aSwitch, R.string.switch_message, Snackbar.LENGTH_INDEFINITE)
                    .setAction( R.string.switch_message1, v ->aSwitch.setChecked(!isChecked))
                    .show();
            });
        }
    }