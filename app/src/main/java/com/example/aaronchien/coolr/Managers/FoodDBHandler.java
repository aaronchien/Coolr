package com.example.aaronchien.coolr.Managers;

/**
 * Created by JasmineZhao on 10/26/2015.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import java.util.ArrayList;

import com.example.aaronchien.coolr.Food;

import java.util.List;
import java.util.Vector;

public class FoodDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "foodDB.db";
    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FOODNAME = "foodname";
    public static final String COLUMN_ENTRY_DATE = "entrydate";
    public static final String COLUMN_EXP_DATE = "expdate";
    public static final String COLUMN_EXP_MONTH = "expmonth";
    public static final String COLUMN_EXP_DAY = "expday";
    public static final String COLUMN_EXP_YEAR = "expyear";

    //We need to pass database information along to superclass
    public FoodDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FOOD + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOODNAME + " TEXT, " +
                COLUMN_ENTRY_DATE + " INTEGER, " +
                COLUMN_EXP_DATE + " INTEGER, " +
                COLUMN_EXP_YEAR + " INTEGER, " +
                COLUMN_EXP_MONTH + " INTEGER, " +
                COLUMN_EXP_DAY + " INTEGER" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(db);
    }

    public void addFood(Food food){
        SQLiteDatabase db = this.getWritableDatabase();
        int expYear = food.getExpDate().getYearOfEra();
        int expMonth = food.getExpDate().getMonthOfYear();
        int expDay = food.getExpDate().getDayOfMonth();
        Log.i("addFood:", food.getName());
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOODNAME, food.getName());
        values.put(COLUMN_ENTRY_DATE, food.getEntryLong()/1000);
        values.put(COLUMN_EXP_DATE, food.getExpLong()/1000);
        values.put(COLUMN_EXP_YEAR, expYear);
        values.put(COLUMN_EXP_MONTH, expMonth);
        values.put(COLUMN_EXP_DAY, expDay);
        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public Vector<Food> searchFood(String food){
        Vector<Food> sameFood = new Vector<Food>();
        String query = "Select * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODNAME + " = \"" + food + "\"";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                sameFood.add(new Food(c.getString(1), c.getInt(2)*1000,
                        c.getInt(3)*1000));
            }while(c.moveToNext());
            return sameFood;
        }
        return null;
    }

    public ArrayList<Food> getAllFood(){
        ArrayList<Food> allFood = new ArrayList<Food>();
        String query = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                allFood.add(new Food(cursor.getString(1), cursor.getInt(2)*1000,
                        cursor.getInt(3)*1000));
            }while(cursor.moveToNext());
        }

        //debug
        for(int i = 0; i < allFood.size(); i++) {
            Log.i("getAllFood()", allFood.get(i).getName() + " " + allFood.get(i).getEntryDate());
        }

        return allFood;
    }

    public ArrayList<String> getAllFoodName(){
        ArrayList<String> allFood = new ArrayList<String>();
        String query = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                allFood.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }

        //debug
        for(int i = 0; i < allFood.size(); i++) {
            Log.i("getAllFood()", allFood.get(i));
        }

        return allFood;
    }

    //Delete a food from the database
    public void deleteFood(String foodName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOODNAME + "=\"" + foodName + "\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("foodname")) != null) {
                dbString += c.getString(c.getColumnIndex("foodname"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}