package com.example.aaronchien.coolr.tests;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;
import android.widget.Button;

import com.example.aaronchien.coolr.Activities.MainActivity;
import com.example.aaronchien.coolr.AddScreen;
import com.example.aaronchien.coolr.R;

/**
 * Created by jeremyliu on 12/2/15.
 */

public class AddScreenTest extends ActivityUnitTestCase<MainActivity> {
//    private MainActivity activity;
//    private Button addButton;
//
////    @Before
//    public void setUp() throws Exception
//    {
//        activity = new MainActivity();
//        activity.onCreate(null);
//        addButton = (Button) activity.findViewById(R.id.addButton);;
//    }
//
//    public void testOpenNextActivity() {
//        // register next activity that need to be monitored.
//        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddScreen.class.getName(), null, false);
//
//        // open current activity.
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // click button and open next activity.
//                addButton.performClick();
//            }
//        });
//
//        //Watch for the timeout
//        //example values 5000 if in ms, or 5 if it's in seconds.
//        Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
//        // next activity is opened and captured.
//        assertNotNull(nextActivity);
//        nextActivity .finish();
//    }


    private Intent mMainIntent;


    public AddScreenTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainIntent = new Intent(Intent.ACTION_MAIN);
    }

//    @MediumTest
    public void testButtonActivityA () {
        MainActivity activity = startActivity(mMainIntent, null, null);
        Button addButton = (Button) activity.findViewById(com.project.R.id.button_activity_a);
        addButton.performClick();
        Intent i = getStartedActivityIntent();
        assertNotNull(i);
        assertTrue(isFinishCalled());
    }
}
