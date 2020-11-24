package com.example.androidlabs;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar tBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tBar);
    //    getSupportActionBar().setDisplayShowTitleEnabled(false);//disappear the title

        //For NavigationDrawer:
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,tBar,
                "open","close");

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




      /*  //add back navigation button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        switch (item.getItemId()) {
            //what to do when the menu item is selected:
            case R.id.MenuItems_overflow:
                //Show the toast immediately:

                message = "You clicked on the overflow menu";
                break;
            case R.id.MenuItems_clear:
                //Show the toast immediately:

                message = "You clicked on item 1";
                break;
            case R.id.MenuItems_edit:

                message = "You clicked on item 2";
                break;
            case R.id.MenuItems_share:

                message = "You clicked on item 3";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        return true;
    }


    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ChatPage:
                startActivity(new Intent(this,ChatRoomActivity.class));
                return true;
            case R.id.GoToWeatherPage:
                startActivity(new Intent(this,WeatherForecast.class));
                return true;
            case R.id.GoBackToLogin:
                setResult(500);
                finish();
                return true;
        }
               return false;
    }
}

