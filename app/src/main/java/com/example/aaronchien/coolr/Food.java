package com.example.aaronchien.coolr;

import android.util.Log;

import java.util.Date;
import org.joda.time.DateTime;

/**
 * Created by JasmineZhao on 10/31/2015.
 */
public class Food {

    private String name = " ";
    private DateTime entryDate;
    private DateTime expDate;

    public Food(String name, DateTime entryDate, DateTime expDate){
        this.name = name;
        this.entryDate = entryDate;
        this.expDate = expDate;
    }

    public Food(String name, long entryMS, long expMS){
        this.name = name;
        entryDate = new DateTime(entryMS);
        expDate = new DateTime(expMS);
    }
    public String getName(){
        return name;
    }

    public DateTime getEntryDate(){
        return entryDate;
    }

    public DateTime getExpDate(){
        return expDate;
    }

    public long getEntryLong(){ return entryDate.getMillis(); }

    public long getExpLong() { return expDate.getMillis(); }

    public void setName(String name) { this.name = name; }

    public void setEntryDate( long ms ) { entryDate = entryDate.withMillis(ms); }

    public void setExpDate( long ms ) { expDate = expDate.withMillis(ms); }
}
