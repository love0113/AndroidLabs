package com.example.androidlabs;


import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import android.os.Bundle;

public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//disappear the title


        //add back navigation button
        if (getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.MenuItems_overflow:
                //Show the toast immediately:
                message ="You clicked on the overflow menu";
                break;
            case R.id.MenuItems_clear:
                //Show the toast immediately:
                message ="You clicked on item 1";
                break;
            case R.id.MenuItems_edit:
                message ="You clicked on item 2";
                break;
            case R.id.MenuItems_share:
                message ="You clicked on item 3";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

    public boolean onNavigationItemSelected(MenuItem item){

    }

}