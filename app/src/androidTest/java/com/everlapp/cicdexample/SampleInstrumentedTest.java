package com.everlapp.cicdexample;

import android.app.Activity;
import android.app.Instrumentation;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleCallback;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

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
