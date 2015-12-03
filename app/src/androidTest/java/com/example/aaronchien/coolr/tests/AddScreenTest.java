// Scenario: Given the home screen page, when the user clicks on the "ADD NEW ITEM" button, then they should be directed to the add screen page.

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

public class AddScreenTest extends ActivityInstrumentationTestCase2 {
    private Activity activity;
    private Button addButton;

    public AddScreenTest() {
        super(MainActivity.class);
    }

    // Given the home screen page
    public void setUp() throws Exception {
        activity = getActivity();
        addButton = (Button) activity.findViewById(R.id.addButton);;
    }

    public void testOpenNextActivity() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddScreen.class.getName(), null, false);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // when the user clicks on the "ADD NEW ITEM" button
                addButton.performClick();
            }
        });

        // then they should be directed to the add screen page
        Activity AddScreen = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(AddScreen);
        AddScreen.finish();
    }
}
