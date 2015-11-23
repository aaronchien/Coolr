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
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import com.example.aaronchien.coolr.Managers.FoodDBHandler;
import com.example.aaronchien.coolr.R;

import java.util.ArrayList;

public class CalendarScreen extends AppCompatActivity implements View.OnClickListener{

    Button backButton;
    FoodDBHandler db = new FoodDBHandler(this, null, null, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                if (db.getFoodByExpDate(year, month+1, dayOfMonth).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nothing's expiring today",
                            Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
                } else {
                    Toast.makeText(getApplicationContext(), "" + db.getFoodByExpDate(year, month + 1, dayOfMonth),
                            Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

    }


}
