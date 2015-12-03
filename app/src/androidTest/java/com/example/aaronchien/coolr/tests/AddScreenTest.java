package com.example.aaronchien.coolr.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.example.aaronchien.coolr.Activities.MainActivity;
import com.example.aaronchien.coolr.AddScreen;
import com.example.aaronchien.coolr.R;

/**
 * Created by jeremyliu on 12/2/15.
 */

public class AddScreenTest extends ActivityInstrumentationTestCase2 {//ActivityUnitTestCase<MainActivity> {
    private Activity activity;
    private Button addButton;

    public AddScreenTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        activity = getActivity();
        addButton = (Button) activity.findViewById(R.id.addButton);;
    }

    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddScreen.class.getName(), null, false);

        // open current activity.
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                addButton.performClick();
            }
        });

        // next activity is opened and captured.
        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }
}
