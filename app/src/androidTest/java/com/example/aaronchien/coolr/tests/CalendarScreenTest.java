// Scenario: Given the home screen page, when the user clicks on the "CALENDAR" button, then they should be directed to the calendar screen page.

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

    // Given the home screen page
    public void setUp() throws Exception {
        activity = getActivity();
        calendarButton = (Button) activity.findViewById(R.id.calendarButton);;
    }

    public void testOpenNextActivity() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CalendarScreen.class.getName(), null, false);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // when the user clicks on the "CALENDAR" button
                calendarButton.performClick();
            }
        });

        //  then they should be directed to the calendar screen page
        Activity CalendarScreen = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(CalendarScreen);
        CalendarScreen.finish();
    }
}
