package com.example.aaronchien.coolr;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Nutrition extends AsyncTask<URL, Integer, String> {
    protected String doInBackground(URL... urls) {
    	String query = "";
    	String charset = "UTF-8";
    	URL currUrl = urls[0];
    	URL url = null;
		try {
			url = new URL(currUrl + URLEncoder.encode(query, charset));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
			url.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String str = "";
    	StringBuilder total = new StringBuilder();
    	try {
			while ((str = in.readLine()) != null) {
				
				total.append("\n" +  str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return total.toString();
    }

}
