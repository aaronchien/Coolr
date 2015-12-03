package com.example.aaronchien.coolr.Activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.aaronchien.coolr.R;

public class Splash extends Activity {
	public MediaPlayer mp1;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//		mp1 = MediaPlayer.create(Splash.this, R.raw.idontlikechiefkeef);
//		mp1.start();
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
//                    Intent openGActivity = new Intent("com.example.aaronchien.coolr.Activities.MainActivity");
                    Intent openGActivity = new Intent(Splash.this, MainActivity.class);
                    startActivity(openGActivity);
                }
            }
        };
        timer.start();
    }


}
