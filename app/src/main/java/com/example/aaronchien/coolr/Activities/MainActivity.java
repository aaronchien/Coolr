package com.example.aaronchien.coolr.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.aaronchien.coolr.Food;
import com.example.aaronchien.coolr.Managers.FoodDBHandler;
import com.example.aaronchien.coolr.R;

import org.joda.time.DateTime;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addButton;

    ListView lv;
    ArrayList<String> allFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.listView);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this);



        FoodDBHandler fh = new FoodDBHandler(this, null, null, 0);
        Food pizza = new Food("monkeybread", System.currentTimeMillis(), System.currentTimeMillis());
        Log.i("MainActivity: ", pizza.getName());
        allFood = fh.getAllFoodName();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allFood);
        lv.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        try {
            Class ourClass = Class.forName("com.example.aaronchien.coolr.AddScreen");
            Intent ourIntent = new Intent(MainActivity.this, ourClass);
            startActivity(ourIntent);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
