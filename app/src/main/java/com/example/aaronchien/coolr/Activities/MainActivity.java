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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.util.SparseBooleanArray;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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

        Spinner spinner = (Spinner) findViewById(R.id.sortSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(sortAdapter);

        displayFood(EXP_DATE_SORT);

//        db = new FoodDBHandler(this, null, null, 0);
//
//        lv = (ListView) findViewById(R.id.listView);
//
//        allFood = db.getAllFoodName(EXP_DATE_SORT);
//        allExpDate = db.getAllExpDate(EXP_DATE_SORT);
//        foodArray = new ArrayList<>();
//        for(int i = 0; i < allFood.size(); i++){
//            HashMap<String, String> datum = new HashMap<>();
//            datum.put( "food name", allFood.get(i) );
//            datum.put( "exp date", allExpDate.get(i));
//            foodArray.add(datum);
//        }

//       adapter = new SimpleAdapter(this, foodArray, android.R.layout.simple_list_item_2,
//                new String[] {"food name", "exp date"}, new int[] {android.R.id.text1, android.R.id.text2});


//        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        lv.setAdapter(adapter);


        addButton = (Button) findViewById(R.id.addButton);
        calendarButton = (Button) findViewById(R.id.calendarButton);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                HashMap<String, String> map = (HashMap<String,String>) parent.getItemAtPosition(position);

                Intent myIntent = new Intent(MainActivity.this, FoodNutritionFact.class);
                myIntent.putExtra("name", map.get("food name"));
                startActivity(myIntent);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if(item.equals("Sort by entry")) {
            displayFood(ENTRY_DATE_SORT);
//            adapter.notifyDataSetChanged();
        }
        else{
            displayFood(EXP_DATE_SORT);
//            adapter.notifyDataSetChanged();
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void displayFood(int sortOption) {
        db = new FoodDBHandler(this, null, null, 0);
        lv = (ListView) findViewById(R.id.listView);

        if (sortOption == EXP_DATE_SORT) {
            allFood = db.getAllFoodName(EXP_DATE_SORT);
            allExpDate = db.getAllExpDate(EXP_DATE_SORT);
        }
        else {
            allFood = db.getAllFoodName(ENTRY_DATE_SORT);
            allExpDate = db.getAllExpDate(ENTRY_DATE_SORT);
        }
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
    }

}
