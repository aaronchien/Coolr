// Scenario: Given the calendar page, when the user clicks on the "BACK" button, then they should be directed back to the home screen page.

package com.example.aaronchien.coolr.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.example.aaronchien.coolr.Activities.MainActivity;
import com.example.aaronchien.coolr.AddScreen;
import com.example.aaronchien.coolr.R;

public class AddScreenBackTest extends ActivityInstrumentationTestCase2 {
    private Activity activity;
    private Button backButton;

    public AddScreenBackTest() {
        super(AddScreen.class);
    }

    // Given the calendar page
    public void setUp() throws Exception {
        activity = getActivity();
        backButton = (Button) activity.findViewById(R.id.backaddscreen);
    }

    public void testOpenNextActivity() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // When the user clicks the "BACK" button
                backButton.performClick();
            }
        });

        // then the user should be brought back to the home screen page
        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(mainActivity);
        mainActivity.finish();
    }

}
