package com.example.aaronchien.coolr.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aaronchien.coolr.Managers.FoodDBHandler;
import com.example.aaronchien.coolr.Nutrition;
import com.example.aaronchien.coolr.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class FoodNutritionFact extends AppCompatActivity {

    TextView foodName;
    TextView calories;
    TextView calfromfat;
    TextView sodium;
    TextView cholcurrent;
    Button back;
    Button delete;
    FoodDBHandler db = new FoodDBHandler(this, null, null, 0);
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_nutrition_fact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");

        back = (Button) findViewById(R.id.backButtonFoodNutritionFact);
        delete = (Button) findViewById(R.id.deleteButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db.deleteFood(name);
                finish();

                Intent ourIntent = new Intent(FoodNutritionFact.this, MainActivity.class);
                startActivity(ourIntent);
            }
        });


        foodName = (TextView) findViewById(R.id.foodName);
        calories = (TextView) findViewById(R.id.cals);
        calfromfat = (TextView) findViewById(R.id.fat);
        sodium = (TextView) findViewById(R.id.sodium);
        cholcurrent = (TextView) findViewById(R.id.chol);


        foodName.setText(name);
        String nospace = name.toString().replaceAll("\\s", "%20");

        URL url = null;
        try {
            url = new URL("https://api.nutritionix.com/v1_1/search/" + nospace + "?results=0%3A20&cal_min=0&cal_max=50000&fields=*&appId=dcfa1c97&appKey=4c4f3685e4270a5e4c1cdef54953ede7");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AsyncTask<URL, Integer, String> theResult = new Nutrition().execute(url);
        String totalString = "";
        try {
            totalString = theResult.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            JSONObject autocomplete= new JSONObject(totalString);
            JSONArray newarray = autocomplete.getJSONArray("hits");
            JSONObject currentObject = newarray.getJSONObject(0);
            JSONObject innerData = currentObject.getJSONObject("fields");
            String caloriesdata = innerData.getString("nf_calories");
            String caloriesFromFat = innerData.getString("nf_calories_from_fat");
            String chlor = innerData.getString("nf_cholesterol");
            String sodiumcurr = innerData.getString("nf_sodium");
            calories.setText(caloriesdata);
            calfromfat.setText(caloriesFromFat);
            sodium.setText(sodiumcurr +" mg");
            cholcurrent.setText(chlor +" mg");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}