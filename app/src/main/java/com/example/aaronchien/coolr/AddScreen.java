package com.example.aaronchien.coolr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Button;

import com.example.aaronchien.coolr.Managers.FoodDBHandler;

import org.joda.time.DateTime;

public class AddScreen extends AppCompatActivity implements View.OnClickListener{

    DatePicker entry;
    DatePicker expiration;
    TextView tv;
    Button addButton;
    FoodDBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new FoodDBHandler(this, null, null, 0);
        tv = (TextView) findViewById(R.id.foodName);
        entry = (DatePicker) findViewById(R.id.dateOfPurchase);
        expiration = (DatePicker) findViewById(R.id.expirationDate);
        addButton = (Button) findViewById(R.id.button2);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        db.addFood(new Food(tv.getText().toString(), new DateTime(entry.getYear(), entry.getMonth(), entry.getDayOfMonth(), 0,0),
                new DateTime(expiration.getYear(), entry.getMonth(), entry.getDayOfMonth(), 0,0)));
    }

}
