package com.example.aaronchien.coolr.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.example.aaronchien.coolr.Activities.CalendarScreen;
import com.example.aaronchien.coolr.Activities.MainActivity;
import com.example.aaronchien.coolr.R;

/**
 * Created by jeremyliu on 12/2/15.
 */

public class CalendarScreenTest extends ActivityInstrumentationTestCase2 {
    private Activity activity;
    private Button calendarButton;

    public CalendarScreenTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        activity = getActivity();
        calendarButton = (Button) activity.findViewById(R.id.calendarButton);;
    }

    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CalendarScreen.class.getName(), null, false);

        // open current activity.
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                calendarButton.performClick();
            }
        });

        // next activity is opened and captured.
        Activity CalendarScreen = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(CalendarScreen);
        CalendarScreen.finish();
    }
}
