package com.everlapp.cicdexample;


import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.Assert.assertThat;


@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleServiceTest {

    private static final int MAX_ITERATION = 100;
    private ServiceForTest mTestService = null;

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();


    @Before
    public void setUp() throws TimeoutException {
        IBinder binder;
        int it = 0;

        while((binder = mServiceRule.bindService(
                new Intent(InstrumentationRegistry.getTargetContext(),
                        ServiceForTest.class))) == null && it < MAX_ITERATION){
            it++;
        }

        mTestService = ((ServiceForTest.LocalBinder) binder).getService();
    }


    @Test
    public void testWithStartedService() throws TimeoutException {
        mServiceRule.startService(new Intent(InstrumentationRegistry.getTargetContext(), ServiceForTest.class));

        // Add you test code
    }



    @Test
    public void testWithBoundService() throws TimeoutException {
        // Create the service intent
        Intent serviceIntent = new Intent(InstrumentationRegistry.getContext(), ServiceForTest.class);

        // Data can be passed to the service via intent
        serviceIntent.putExtra(ServiceForTest.SEED_KEY, 42L);

        // Bind the service and grab a reference to the binder
        IBinder binder = mServiceRule.bindService(serviceIntent);

        // Get the reference to the service, or you can call
        // public methods on the binder directly.
        // TODO: 24.08.18 https://developer.android.com/training/testing/integration-testing/service-testing - BUG (use solve https://issuetracker.google.com/issues/37054210)
        //ServiceForTest service = ((ServiceForTest.LocalBinder) binder).getService();

        // Verify the service working correctly
        assertThat(mTestService.getRandomInt(), is(any(Integer.class)));
    }


}
