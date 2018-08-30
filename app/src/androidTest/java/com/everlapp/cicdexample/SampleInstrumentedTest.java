package com.everlapp.cicdexample;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SampleInstrumentedTest {


    @Test
    public void test1() {
        ActivityLifecycleMonitorRegistry
                .getInstance()
                .addLifecycleCallback(new ActivityLifecycleCallback() {
            @Override
            public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                // Listen activity state

            }
        });

        // Control activity state
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        // instrumentation.callActivityOnCreate();
    }


}
