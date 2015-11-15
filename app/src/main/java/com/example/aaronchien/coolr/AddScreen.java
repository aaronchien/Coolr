package com.example.aaronchien.coolr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Button;

import com.example.aaronchien.coolr.Activities.MainActivity;
import com.example.aaronchien.coolr.Managers.FoodDBHandler;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class AddScreen extends AppCompatActivity implements View.OnClickListener{

    DatePicker entry;
    DatePicker expiration;
    TextView tv;
    Button addButton;
    FoodDBHandler db;
    AutoCompleteTextView addfood; 
	TextView showcall; 
 	public String result = ""; 

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
        
        addfood = (AutoCompleteTextView)findViewById(R.id.fooditem);
		showcall = (TextView)findViewById(R.id.showcall);
		addfood.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length() > 2)
				{
					URL url = null;
					String nospace = s.toString().replaceAll("\\s","%20");
			 		try {
			 			url = new URL("https://api.nutritionix.com/v1_1/search/" + nospace +"?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id&appId=dcfa1c97&appKey=4c4f3685e4270a5e4c1cdef54953ede7");
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
			 		List<String> nameItems = new ArrayList<String>();
			 		try {
						JSONObject autocomplete= new JSONObject(totalString);
						JSONArray newarray = autocomplete.getJSONArray("hits");
						for(int i = 0; i < newarray.length(); i++)
						{
							JSONObject currentObject = newarray.getJSONObject(i);
				          	JSONObject innerData = currentObject.getJSONObject("fields");
				          	String name = innerData.getString("item_name");
				          	String brand = innerData.getString("brand_name");
				          	nameItems.add(name + " from " + brand);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 		final String[] strarray = nameItems.toArray(new String[0]);			 		
			 		String concat = "";
			 		for(int k = 0; k < strarray.length; k++)
			 		{
			 			concat = concat + "," + strarray[k];
			 		}
			 		addDropDown(strarray);
				}
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    }

    @Override
    public void onClick(View v) {
        db.addFood(new Food(tv.getText().toString(), new DateTime(entry.getYear(), entry.getMonth(), entry.getDayOfMonth(), 0,0),
                new DateTime(expiration.getYear(), expiration.getMonth() + 1, expiration.getDayOfMonth(), 0,0)));

        finish();

        Intent ourIntent = new Intent(AddScreen.this, MainActivity.class);
        startActivity(ourIntent);
    }

    public void addDropDown(String[] input)
	{
		AutoCompleteTextView curr = (AutoCompleteTextView) findViewById(R.id.fooditem);
		AddScreen<String> adapter1;
		adapter1 = new AddScreen<String>(AddScreen.this,
		            android.R.layout.simple_dropdown_item_1line, input);
		curr.setAdapter(adapter1);
		curr.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					result = parent.getItemAtPosition(position).toString();
					processResult();
				}
			});
	}

	public void processResult() {
	    String newvalue = result;
	    showcall.setText(newvalue);
	   // try {
			//Class ourClass = Class.forName("com.example.coolr.MainActivity");
			//Intent ourIntent = new Intent(Addition.this, ourClass);
			//ourIntent.putExtra("key", newvalue);
			//startActivity(ourIntent);
			//}
			//catch(ClassNotFoundException e)
			//{
			//	e.printStackTrace();
			//}		    //intent.putExtra("key", newvalue);
	    //startActivity(intent);
	}
	
}


