package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        Button btn = findViewById(R.id.button4);
        btn.setOnClickListener(v ->
                Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG).show());

        Switch aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Snackbar.make(aSwitch, R.string.switch_message,   Snackbar.LENGTH_INDEFINITE)
                            .setAction( R.string.switch_message2, v ->aSwitch.setChecked(isChecked))
                            .show();
                } else {
                    Snackbar.make(aSwitch, R.string.switch_message1, Snackbar.LENGTH_INDEFINITE)
                            .setAction( R.string.switch_message2, v ->aSwitch.setChecked(!isChecked))
                            .show();
                }

            }

        });
    }
}