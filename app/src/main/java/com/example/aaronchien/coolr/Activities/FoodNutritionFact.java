package com.example.aaronchien.coolr.Activities;

import android.content.Intent;
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
import com.example.aaronchien.coolr.R;

public class FoodNutritionFact extends AppCompatActivity {

    TextView foodName;
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

        Log.i("NAME OF FOOD", name);

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

        foodName.setText(name);


    }

}
