// Scenario: Given the calendar page, when the user clicks on the "BACK" button, then they should be directed back to the home screen page.

package com.example.aaronchien.coolr.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.example.aaronchien.coolr.Activities.CalendarScreen;
import com.example.aaronchien.coolr.Activities.MainActivity;
import com.example.aaronchien.coolr.R;
/**
 * Created by Alex on 12/2/15.
 */
public class CalendarBackTest extends ActivityInstrumentationTestCase2 {
    private Activity activity;
    private Button backButton;

    public CalendarBackTest() {
        super(CalendarScreen.class);
    }

    // Given the calendar page
    public void setUp() throws Exception {
        activity = getActivity();
        backButton = (Button) activity.findViewById(R.id.backButton);
    }

    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        // open current activity.
        activity.runOnUiThread(new Runnable() {

            // When the user clicks the "BACK" button
            @Override
            public void run() {
                backButton.performClick();
            }
        });

        // then the user should be brought back to the home screen page
        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(mainActivity);
        mainActivity.finish();
    }

}
