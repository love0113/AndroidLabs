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

public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//disappear the title

   /*     //For NavigationDrawer:
        DrawerLayout drawer = findviewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer ,tBar,"Open","Close"); */


        //add back navigation button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                message = "You clicked on the overflow menu";
                break;
            case R.id.MenuItems_clear:
                //Show the toast immediately:
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                message = "You clicked on item 1";
                break;
            case R.id.MenuItems_edit:
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                message = "You clicked on item 2";
                break;
            case R.id.MenuItems_share:
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                message = "You clicked on item 3";
                break;
            case R.id.ChatPage:
                Toast.makeText(this, "Chat started", Toast.LENGTH_SHORT).show();
                //Give directions to go from this page, to SecondActivity
                Intent nextPage = new Intent(TestToolbar.this, ChatRoomActivity.class);
                //Now make the transition:
                startActivityForResult(nextPage, 345);
                break;
            case R.id.GoToWeatherPage:
                Toast.makeText(this, "Weather app started", Toast.LENGTH_SHORT).show();
                Intent goToMenuPage = new Intent(TestToolbar.this, WeatherForecast.class);

                startActivityForResult(goToMenuPage, 234);
                break;
            case R.id.GoBackToLogin:
                Toast.makeText(this, "Go back to Login", Toast.LENGTH_SHORT).show();
                Intent goBackToLogin = new Intent(TestToolbar.this, ProfileActivity.class);
                startActivityForResult(goBackToLogin,500);
                break;
        }

        return true;
    }

     /*     goToChat.setOnClickListener( b -> {

            //Give directions to go from this page, to SecondActivity
            Intent nextPage = new Intent(TestToolbar.this, ChatRoomActivity.class);
            //Now make the transition:
            startActivityForResult(nextPage, 345);
        }); */

          /*   goToWeatherBtn = (Button)findViewById(R.id.GoToWeatherPage);
        goToWeatherBtn.setOnClickListener(c -> {
            Intent goToMenuPage = new Intent(TestToolbar.this, WeatherForecast.class);

            startActivityForResult(goToMenuPage, 234);

        });  */

  /*  public boolean onNavigationItemSelected(MenuItem item) {

    }*/
}

