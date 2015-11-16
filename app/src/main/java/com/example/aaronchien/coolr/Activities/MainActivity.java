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
import android.util.SparseBooleanArray;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final static int EXP_DATE_SORT = 0;
    final static int ENTRY_DATE_SORT = 1;

    Button addButton;
    Button deleteButton;
    Button calendarButton;

    ListView lv;
    ArrayList<String> allFood;
    ArrayList<String> allExpDate;
    ArrayList<HashMap<String,String>> foodArray;
    SimpleAdapter adapter;

    FoodDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new FoodDBHandler(this, null, null, 0);

        lv = (ListView) findViewById(R.id.listView);

        allFood = db.getAllFoodName(EXP_DATE_SORT);
        allExpDate = db.getAllExpDate(EXP_DATE_SORT);
        foodArray = new ArrayList<>();
        for(int i = 0; i < allFood.size(); i++){
            HashMap<String, String> datum = new HashMap<>();
            datum.put( "food name", allFood.get(i) );
            datum.put( "exp date", allExpDate.get(i));
            foodArray.add(datum);
        }

        adapter = new SimpleAdapter(this, foodArray, android.R.layout.simple_list_item_2,
                new String[] {"food name", "exp date"}, new int[] {android.R.id.text1, android.R.id.text2});


        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);


        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        calendarButton = (Button) findViewById(R.id.calendarButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                SparseBooleanArray checkedItemPositions = lv.getCheckedItemPositions();

                if(checkedItemPositions.size()>0) {

                    int itemCount = lv.getCount();

                    for (int i = itemCount - 1; i >= 0; i--) {
                        if (checkedItemPositions.valueAt(i)) {
                            db.deleteFood(allFood.get(i));
                            foodArray.remove(i);
                        }
                    }
                    checkedItemPositions.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                try {
                    Class ourClass = Class.forName("com.example.aaronchien.coolr.AddScreen");
                    Intent ourIntent = new Intent(MainActivity.this, ourClass);

                    startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class ourClass = Class.forName("com.example.aaronchien.coolr.Activities.CalendarScreen");
                    Intent ourIntent = new Intent(MainActivity.this, ourClass);

                    startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



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

    }
}
